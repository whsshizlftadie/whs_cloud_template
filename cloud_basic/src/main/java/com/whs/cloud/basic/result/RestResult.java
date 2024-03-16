package com.whs.cloud.basic.result;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public final class RestResult<T> implements Serializable {

    private static final String SUCCESS_MESSAGE = "操作成功！";
    private static final String FAILURE_MESSAGE = "操作失败！";

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    private Long total;

    public Long getTotal() {
        return total;
    }

    public RestResult<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public static String getSuccessMessage() {
        return SUCCESS_MESSAGE;
    }

    public static String getFailureMessage() {
        return FAILURE_MESSAGE;
    }

    public int getCode() {
        return code;
    }

    public RestResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> RestResult<T> success() {
        RestResult<T> result = new RestResult<T>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(SUCCESS_MESSAGE);
        return result;
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> success(String message, T data) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> failure() {
        RestResult<T> result = new RestResult<T>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(FAILURE_MESSAGE);
        return result;
    }

    public static <T> RestResult<T> failure(String message) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(message);
        return result;
    }

    public static <T> RestResult<T> failure(int code, String message) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> RestResult<T> response(int code, String message, T data) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> response(boolean success) {
        RestResult<T> result = new RestResult<T>();
        if (success) {
            result.setCode(HttpStatus.OK.value());
            result.setMessage(SUCCESS_MESSAGE);
        } else {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(FAILURE_MESSAGE);
        }
        return result;
    }

    public static <T> RestResult<T> response(boolean success, T data) {
        RestResult<T> result = new RestResult<T>();
        if (success) {
            result.setCode(HttpStatus.OK.value());
            result.setMessage(SUCCESS_MESSAGE);
            result.setData(data);
        } else {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(FAILURE_MESSAGE);
        }
        return result;
    }

    public static <T> RestResult page(Pager<T> page) {
        RestResult<List<T>> result = new RestResult<>();
        result.setCode(HttpStatus.OK.value());
        result.setData(page.getRecords());
        result.setMessage(SUCCESS_MESSAGE);
        result.setTotal(page.getTotal());
        return result;
    }

}