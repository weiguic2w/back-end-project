package com.wcw.course.utl;

import com.wcw.course.exception.GlobalException;
import com.wcw.course.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22;
 */
@Slf4j
public abstract class Assert {

    /**
     * 断言对象不为空
     * 如果对象obj为空，则抛出异常
     * @param obj 待判断对象
     */
    public static void notEmpty(Object obj, ResultEnum resultEnum) {
        if (obj == null) {
            log.info("obj is null...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void notEmpty(Object obj, String msg) {
        if (ObjectUtils.isEmpty(obj)) {
            log.info("obj is null...............");
            throw new GlobalException(msg);
        }
    }

    public static void isEmpty(Object object, ResultEnum resultEnum) {
        if (!ObjectUtils.isEmpty(object)) {
            log.info("obj is not null......");
            throw new GlobalException(resultEnum);
        }
    }
    public static void isTrue(boolean expression, ResultEnum resultEnum) {
        if (!expression) {
            log.info("fail...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void notEquals(Object o1, Object o2,  ResultEnum resultEnum) {
        if (ObjectUtils.nullSafeEquals(o1,o2)) {
            log.info("equals...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void equals(Object o1, Object o2,  ResultEnum resultEnum) {
        if (!ObjectUtils.nullSafeEquals(o1,o2)) {
            log.info("not equals...............");
            throw new GlobalException(resultEnum);
        }
    }
}

