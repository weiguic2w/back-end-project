package com.wcw.course.exception;

import com.wcw.course.result.ResultEnum;
import lombok.Data;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22  15:47;
 */
@Data
public class GlobalException extends RuntimeException {
    /**
     * code 状态码
     * message  异常信息
     */
    private Integer code;
    private String message;

    public GlobalException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public GlobalException(ResultEnum resultEnum) {
        this(resultEnum.getMessage(), resultEnum.getCode());
    }

    public GlobalException(String message) {
        this.code = ResultEnum.ERROR.getCode();
        this.message = message;
    }

    /**
     * @param message 错误消息
     * @param code 错误码
     * @param cause 原始异常对象
     */
    public GlobalException(String message, Integer code, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    /**
     * @param resultEnum 接收枚举类型
     * @param cause 原始异常对象
     */
    public GlobalException(ResultEnum resultEnum, Throwable cause) {
        super(cause);
        this.message = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }

}
