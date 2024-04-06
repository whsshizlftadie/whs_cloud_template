package com.whs.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.cloud.auth.bean.Resource;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.RoleResource;
import com.whs.cloud.auth.bean.request.resource.ResourceBindRoleRequest;
import com.whs.cloud.auth.exception.ResourceException;
import com.whs.cloud.auth.mapper.ResourceMapper;
import com.whs.cloud.auth.service.ResourceService;
import com.whs.cloud.auth.service.RoleResourceService;
import com.whs.cloud.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86157
 * @description 针对表【cloud_auth_resource】的数据库操作Service实现
 * @createDate 2024-03-16 17:58:38
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
        implements ResourceService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Override
    public List<Resource> list() {

        List<Resource> resourceList = lambdaQuery().eq(Resource::getIsDelete, 0).list();

        return resourceList;
    }

    @Override
    public Boolean delete(Long id) {

        Integer count = lambdaQuery().eq(Resource::getId, id).eq(Resource::getIsDelete, 0).count();

        if (count < 0) {
            throw new ResourceException("resource not exists");
        }

        boolean update = lambdaUpdate().set(Resource::getIsDelete, 1).eq(Resource::getId, id).update();

        if (!update) {
            throw new ResourceException("resource not exists");
        }

        return true;
    }

    @Override
    public Boolean add(Resource resource) {

        Integer count = lambdaQuery().eq(Resource::getResourceName, resource.getResourceName()).count();

        if (count > 0) {
            throw new ResourceException("name has bean exists");
        }

        boolean save = save(resource);

        if (!save) {
            throw new ResourceException("save info fail -resource");
        }

        return true;
    }

    @Override
    public Boolean update(Resource resource) {

        Integer count = lambdaQuery().eq(Resource::getId, resource.getId()).eq(Resource::getIsDelete, 0).count();

        if (count <= 0) {
            throw new ResourceException("resource not exists");
        }

        boolean update = updateById(resource);

        if (!update) {
            throw new ResourceException("resource update fail");
        }

        return update;
    }

    @Override
    public Boolean bindToRole(ResourceBindRoleRequest request) {

        List<RoleResource> roleResources = request.getRoleResources();

        List<Long> resourceIds
                = roleResources.stream().map(i -> i.getReourceId()).distinct().collect(Collectors.toList());

        List<Long> roleIds
                = roleResources.stream().map(i -> i.getRoleId()).distinct().collect(Collectors.toList());

        Integer resourceCnt
                = lambdaQuery().eq(Resource::getIsDelete, 0).in(Resource::getId, resourceIds).count();

        Integer roleCnt
                = roleService.lambdaQuery().eq(Role::getIsDelete, 0).in(Role::getId, roleIds).count();

        if (roleCnt != roleIds.size() || resourceCnt != resourceIds.size()) {
            throw new ResourceException("resource or role not exists");
        }

        boolean saveBatch = roleResourceService.saveBatch(roleResources);

        if (!saveBatch) {
            throw new ResourceException("resource role sav batch  faile");
        }

        return true;
    }
}




