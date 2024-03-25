package com.whs.cloud.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.response.page.PageResponse;

/**
* @author 86157
* @description 针对表【cloud_auth_role】的数据库操作Service
* @createDate 2024-03-16 17:58:38
*/
public interface RoleService extends IService<Role> {

    PageResponse<Role> page(PageRequest request);

    Role getById(Long id);

    Boolean update(Role role);

    Boolean del(Long id);

    Boolean add(Role role);
}
