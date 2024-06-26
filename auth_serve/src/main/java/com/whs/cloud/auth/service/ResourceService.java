package com.whs.cloud.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.cloud.auth.bean.Resource;
import com.whs.cloud.auth.bean.request.resource.ResourceBindRoleRequest;

import java.util.List;

/**
 * @author 86157
 * @description 针对表【cloud_auth_resource】的数据库操作Service
 * @createDate 2024-03-16 17:58:38
 */
public interface ResourceService extends IService<Resource> {

    List<Resource> list();

    Boolean delete(Long id);

    Boolean add(Resource resource);

    Boolean update(Resource resource);

    Boolean bindToRole(ResourceBindRoleRequest request);
}
