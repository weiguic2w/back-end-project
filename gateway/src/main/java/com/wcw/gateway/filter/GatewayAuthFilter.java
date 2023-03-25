package com.wcw.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.wcw.gateway.util.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 鉴权过滤器
 * @author ChuangWeiwei;
 * @create 2023.03.25  22:01;
 */
@Component
@Slf4j
public class GatewayAuthFilter implements GlobalFilter, Ordered {
    @Autowired
    StringRedisTemplate redisTemplate;

    // 加载白名单
    private static List<String> whitelist = null;
    static {
        try (InputStream inputStream = new ClassPathResource("writelist.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Set<String> strings = properties.stringPropertyNames();
            whitelist= new ArrayList<>(strings);
        } catch (Exception e) {
            log.error("加载/security-whitelist.properties出错:{}",e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 网关鉴权 & token校验刷新
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();

        AntPathMatcher pathMatcher = new AntPathMatcher();
        //白名单放行
        for (String url : whitelist) {
            if (pathMatcher.match(url, requestUrl)) {
                return chain.filter(exchange);
            }
        }

        //检查token是否存在
        String token = StpUtil.getTokenValue();
        if (StringUtils.isBlank(token)) {
            return buildReturnMono("没有认证",exchange);
        }
        //判断是否是有效的token
        try {
            final long timeout = StpUtil.getTokenTimeout();
            if(timeout <= 0) {
                return buildReturnMono("认证令牌已过期",exchange);
            } else if(timeout <= 600) {
                refreshToken(token);
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            log.info("认证令牌无效: {}", token);
            return buildReturnMono("认证令牌无效",exchange);
        }

    }

    private Mono<Void> buildReturnMono(String error, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        String jsonString = JSON.toJSONString(new RestErrorResponse(error));
        byte[] bits = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    private void refreshToken(String token) {
        StpUtil.renewTimeout(token, 86400);
        redisTemplate.expire( token, 86400, TimeUnit.SECONDS);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
