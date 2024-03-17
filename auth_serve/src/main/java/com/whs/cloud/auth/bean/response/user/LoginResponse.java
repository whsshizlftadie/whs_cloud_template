package com.whs.cloud.auth.bean.response.user;


public class LoginResponse {

    private Long userId;

    private String username;

    private String token;

    private String avatar;


    public Long getUserId() {
        return userId;
    }

    public LoginResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public LoginResponse setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
