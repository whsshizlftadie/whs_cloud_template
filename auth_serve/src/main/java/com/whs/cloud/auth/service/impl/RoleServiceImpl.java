package com.whs.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.response.page.PageResponse;
import com.whs.cloud.auth.exception.RoleException;
import com.whs.cloud.auth.mapper.RoleMapper;

import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.basic.result.Pager;
import com.whs.cloud.basic.result.RestResult;
import com.whs.cloud.basic.result.constant.TimeOrderSet;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * @author 86157
 * @description 针对表【cloud_auth_role】的数据库操作Service实现
 * @createDate 2024-03-16 17:58:38
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public PageResponse<Role> page(PageRequest request) {

        Integer current = request.getCurrent();
        Integer size = request.getSize();

        Pager<Role> pager = new Pager<>();
        pager.setCurrent(current).setSize(size);
        pager.setOrders(TimeOrderSet.CREAT_TIME_DESC);

        Pager<Role> rolePager = page(pager);

        if (rolePager.getSize()<=0){
            throw new RoleException("no more role info");
        }

        PageResponse<Role> rolePageResponse = new PageResponse<>();
        rolePageResponse.setRecorders(rolePager.getRecords()).setCurrent(rolePager.getCurrent())
                .setSize(rolePager.getSize()).setTotal(rolePager.getTotal());

        return rolePageResponse;
    }

    @Override
    public Role getById(Long id) {

        Role role = lambdaQuery().eq(Role::getId, id).eq(Role::getIsDelete, 0).one();

        if(ObjectUtils.isEmpty(role)){
            throw new RoleException("role not exists");
        }

        return role;
    }

    @Override
    public Boolean update(Role role) {

        Role existsRole = lambdaQuery().eq(Role::getId, role.getId()).eq(Role::getIsDelete, 0).one();

        if (ObjectUtils.isEmpty(existsRole)){
            throw new RoleException("role info not exists");
        }

        boolean updateById = updateById(role);

        return updateById;

    }

    @Override
    public Boolean del(Long id) {

        boolean update = lambdaUpdate().eq(Role::getId, id).set(Role::getIsDelete, 1).update();

        return update;

    }

    @Override
    public Boolean add(Role role) {

        Role existsRole = lambdaQuery().eq(Role::getRoleName, role.getRoleName()).one();

        if (ObjectUtils.isEmpty(existsRole)){
            throw new RoleException("role name has been exists");
        }

        int insert = this.getBaseMapper().insert(role);

        if (insert<=0){
            throw new RoleException("add role fail");
        }

        return true;
    }

}




