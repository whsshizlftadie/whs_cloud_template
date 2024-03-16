package com.whs.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.mapper.RoleMapper;

import com.whs.cloud.auth.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author 86157
 * @description 针对表【cloud_auth_role】的数据库操作Service实现
 * @createDate 2024-03-16 17:58:38
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




