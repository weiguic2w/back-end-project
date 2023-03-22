package com.wcw.course.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22;
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    SUCCESS(2000, "success"),
    ERROR(5000, "error"),
    ARGS_EMPTY(5001, "参数为空"),
    ARGS_ERR(5002, "参数错误"),

    ;

    private Integer code;
    private String message;
}
