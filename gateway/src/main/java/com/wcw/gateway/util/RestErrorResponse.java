package com.wcw.gateway.util;

import java.io.Serializable;

/**
 * 错误响应参数包装
 * @author ChuangWeiwei;
 * @create 2023.03.25  22:15;
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
