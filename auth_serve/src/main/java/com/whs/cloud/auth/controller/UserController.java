package com.whs.cloud.auth.controller;

import com.whs.cloud.auth.bean.User;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.request.user.LoginRequest;
import com.whs.cloud.auth.bean.request.user.RegisterRequest;
import com.whs.cloud.auth.bean.response.user.LoginResponse;
import com.whs.cloud.auth.bean.response.user.RegisterResponse;
import com.whs.cloud.auth.exception.UserException;
import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.basic.result.RestResult;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/count")
    public RestResult count(){
        return RestResult.success(userService.count());
    }

    @PostMapping("/login")
    public RestResult login(@RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        return RestResult.success(response);
    }

    @PostMapping("/register")
    public RestResult register(@RequestBody RegisterRequest request){
        RegisterResponse response = userService.register(request);
        return RestResult.success(response);
    }

    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Long id){
        return RestResult.success(userService.delete(id));
    }

    @PutMapping("/update")
    public RestResult update(@RequestBody User user){
        Boolean update = userService.update(user);
        return RestResult.success(update);
    }

    @GetMapping("/{id}")
    public RestResult getUserById(@PathVariable Long id){
        User user = userService.getById(id);

        if (ObjectUtils.isEmpty(user)){
            throw new UserException("this user id not exists");
        }

        if(user.getIsDelete().equals(1)){
            throw new UserException("user has been delete");
        }

        return RestResult.success(user);
    }

    @PostMapping("/page")
    public RestResult page(@RequestBody PageRequest request){
        return RestResult.success(userService.page(request));
    }
}
