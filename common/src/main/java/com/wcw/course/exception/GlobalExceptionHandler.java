package com.wcw.course.exception;

import com.wcw.course.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22  15:51;
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);

        if(e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return R.error()
                    .code(ge.getCode())
                    .message(ge.getMessage());
        }

        return R.error();
    }
}
