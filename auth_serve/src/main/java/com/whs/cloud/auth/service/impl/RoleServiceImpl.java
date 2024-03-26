package com.whs.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.User;
import com.whs.cloud.auth.bean.UserRole;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.request.role.RoleBindUserRequest;
import com.whs.cloud.auth.bean.response.page.PageResponse;
import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;
import com.whs.cloud.auth.exception.RoleException;
import com.whs.cloud.auth.mapper.RoleMapper;
import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.auth.service.UserRoleService;
import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.basic.result.Pager;
import com.whs.cloud.basic.result.constant.TimeOrderSet;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86157
 * @description 针对表【cloud_auth_role】的数据库操作Service实现
 * @createDate 2024-03-16 17:58:38
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public PageResponse<Role> page(PageRequest request) {

        Integer current = request.getCurrent();
        Integer size = request.getSize();

        Pager<Role> pager = new Pager<>();
        pager.setCurrent(current).setSize(size);
        pager.setOrders(TimeOrderSet.CREAT_TIME_DESC);

        Pager<Role> rolePager = page(pager);

        if (rolePager.getSize() <= 0) {
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

        if (ObjectUtils.isEmpty(role)) {
            throw new RoleException("role not exists");
        }

        return role;
    }

    @Override
    public Boolean update(Role role) {

        Role existsRole = lambdaQuery().eq(Role::getId, role.getId()).eq(Role::getIsDelete, 0).one();

        if (ObjectUtils.isEmpty(existsRole)) {
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

        if (ObjectUtils.isEmpty(existsRole)) {
            throw new RoleException("role name has been exists");
        }

        int insert = this.getBaseMapper().insert(role);

        if (insert <= 0) {
            throw new RoleException("add role fail");
        }

        return true;
    }


    @Override
    public List<RoleAndResourceVo> roleAndResources(String ids) {
        List<Long> idList
                = Arrays.stream(ids.split(","))
                .distinct().map(i -> Long.parseLong(i)).collect(Collectors.toList());

        Integer count = lambdaQuery().eq(Role::getIsDelete, 0).in(Role::getId, idList).count();

        if (count != idList.size()) {
            throw new RoleException("one ore more role not exists");
        }

        List<RoleAndResourceVo> roleAndResources = this.baseMapper.getRoleAndResources(idList);

        return roleAndResources;

    }

    @Override
    public Boolean roleBindUser(RoleBindUserRequest request) {

        List<UserRole> userRoles = request.getUserRoles();

        List<Long> userIds = userRoles.stream().map(i -> i.getUserId()).collect(Collectors.toList());
        List<Long> roleIds = userRoles.stream().map(i -> i.getRoleId()).collect(Collectors.toList());

        Integer roleCnt = lambdaQuery().in(Role::getId, roleIds).eq(Role::getIsDelete, 0).count();
        Integer userCnt = userService.lambdaQuery().in(User::getId, userIds).eq(User::getIsDelete, 0).count();

        if (roleCnt != userIds.size() || userCnt != userIds.size()) {
            throw new RoleException("some role or user not exists");
        }

        boolean saveBatch = userRoleService.saveBatch(userRoles);

        if (!saveBatch) {
            throw new RoleException("insert batch userRole fail");
        }

        return true;
    }

}




