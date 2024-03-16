package com.whs.cloud.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.cloud.auth.bean.UserRole;
import com.whs.cloud.auth.mapper.UserRoleMapper;
import com.whs.cloud.auth.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 86157
* @description 针对表【cloud_auth_user_role】的数据库操作Service实现
* @createDate 2024-03-16 17:58:38
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}




