package com.wcw.course.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22;
 */
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data;

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R data(String key, Object val) {
        this.data.put(key, val);
        return this;
    }

    public R data(Map<String,Object> data) {
        this.data = data;
        return this;
    }

    public R(Integer code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static R fetchR(ResultEnum resultEnum) {
        return new R(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    public static R ok() {
        return fetchR(ResultEnum.SUCCESS);
    }

    public static R error() {
        return fetchR(ResultEnum.ERROR);
    }
}
