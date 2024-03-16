package com.whs.cloud.auth.controller;

import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/count")
    public RestResult count(){
        return RestResult.success(userService.count());
    }
}
