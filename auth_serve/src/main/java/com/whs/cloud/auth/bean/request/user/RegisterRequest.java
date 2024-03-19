package com.whs.cloud.auth.bean.request.user;

import java.util.List;
import java.util.StringJoiner;

public class RegisterRequest extends LoginRequest {


    private String code;

    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public RegisterRequest setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RegisterRequest setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + new StringJoiner(", ", RegisterRequest.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("roles='" + roles + "'")
                .toString();
    }
}
