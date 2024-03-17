package com.whs.cloud.auth.bean.response.user;

import java.util.StringJoiner;

public class RegisterResponse {

    private String msg;

    private String token;

    private String username;

    private Long userId;

    @Override
    public String toString() {
        return new StringJoiner(", ", RegisterResponse.class.getSimpleName() + "[", "]")
                .add("msg='" + msg + "'")
                .add("token='" + token + "'")
                .add("username='" + username + "'")
                .add("userId='" + userId + "'")
                .toString();
    }

    public String getMsg() {
        return msg;
    }

    public RegisterResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getToken() {
        return token;
    }

    public RegisterResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RegisterResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
