package com.whs.cloud.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.cloud.auth.bean.User;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.request.user.LoginRequest;
import com.whs.cloud.auth.bean.request.user.RegisterRequest;
import com.whs.cloud.auth.bean.response.page.PageResponse;
import com.whs.cloud.auth.bean.response.user.LoginResponse;
import com.whs.cloud.auth.bean.response.user.RegisterResponse;

/**
* @author 86157
* @description 针对表【cloud_auth_user】的数据库操作Service
* @createDate 2024-03-16 17:58:38
*/
public interface UserService extends IService<User> {

    LoginResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);

    Boolean delete(Long id);

    Boolean update(User user);

    PageResponse<User> page(PageRequest request);


}
