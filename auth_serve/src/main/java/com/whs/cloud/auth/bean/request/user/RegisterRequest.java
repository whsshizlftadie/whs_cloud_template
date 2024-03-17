package com.whs.cloud.auth.bean.request.user;

import java.util.StringJoiner;

public class RegisterRequest extends LoginRequest{



    private String code;



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
                .toString();
    }
}
