package com.wcw.course.utl;

import com.wcw.course.exception.GlobalException;
import com.wcw.course.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public static void notNull(Object obj, ResultEnum resultEnum) {
        if (obj == null) {
            log.info("obj is null...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            log.info("obj is null...............");
            throw new GlobalException(msg);
        }
    }

    public static void isNull(Object object, ResultEnum resultEnum) {
        if (object != null) {
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

    public static void notEquals(Object m1, Object m2,  ResultEnum resultEnum) {
        if (m1.equals(m2)) {
            log.info("equals...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void equals(Object m1, Object m2,  ResultEnum resultEnum) {
        if (!m1.equals(m2)) {
            log.info("not equals...............");
            throw new GlobalException(resultEnum);
        }
    }

    public static void notEmpty(String s) {
        if (StringUtils.isEmpty(s)) {
            log.info("is empty...............");
            throw new GlobalException(ResultEnum.ERROR);
        }
    }
    //public static boolean isEmpty(@Nullable Collection<?> collection) {
    public static void notEmpty(Collection<?> collection) {
        if(CollectionUtils.isEmpty(collection)) {
            log.info("empty collection");
            throw new GlobalException(ResultEnum.ERROR);
        }
    }
}

