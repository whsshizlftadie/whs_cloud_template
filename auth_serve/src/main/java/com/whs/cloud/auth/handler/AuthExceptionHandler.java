package com.whs.cloud.auth.handler;

import com.whs.cloud.basic.result.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResult exceptionHandler(Exception ex) {
        return RestResult.failure(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public RestResult runTimeExceptionHandler(RuntimeException ex) {
        return RestResult.failure(ex.getMessage());
    }
}
