package com.whs.cloud.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nimbusds.jose.JOSEException;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.User;
import com.whs.cloud.auth.bean.UserRole;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.bean.request.user.LoginRequest;
import com.whs.cloud.auth.bean.request.user.RegisterRequest;
import com.whs.cloud.auth.bean.response.page.PageResponse;
import com.whs.cloud.auth.bean.response.user.LoginResponse;
import com.whs.cloud.auth.bean.response.user.RegisterResponse;
import com.whs.cloud.auth.bean.vo.UserAndRoleVo;
import com.whs.cloud.auth.exception.UserException;
import com.whs.cloud.auth.mapper.UserMapper;
import com.whs.cloud.auth.mapper.UserRoleMapper;
import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.auth.service.UserRoleService;
import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.basic.contextUsage.UserContext;
import com.whs.cloud.basic.result.Pager;
import com.whs.cloud.basic.result.constant.TimeOrderSet;
import com.whs.cloud.basic.utils.JWTUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86157
 * @description 针对表【cloud_auth_user】的数据库操作Service实现
 * @createDate 2024-03-16 17:58:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = lambdaQuery().eq(User::getUsername, request.getUsername()).one();

        if (ObjectUtils.isEmpty(user)) {
            throw new UserException("用户不存在");
        }

        String password = request.getPassword();

        String encoder = user.getPassword();

        if (!passwordEncoder.matches(password, encoder)) {
            throw new UserException("密码错误");
        }

        List<String> roleNames = userRoleMapper.loadRoleNameByUsername(request.getUsername());

        LoginResponse response = new LoginResponse();

        try {
            String token = jwtUtils.buildToken(request.getUsername(), roleNames, 3600 * 24000l);
            response.setToken(token).setUsername(user.getUsername()).setAvatar(user.getAvatarUrl()).setUserId(user.getId());
        } catch (JOSEException e) {
            throw new UserException("token create fail");
        }

        return response;
    }

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest request) {

        User user = lambdaQuery().eq(User::getUsername, request.getUsername()).one();

        if (!ObjectUtils.isEmpty(user)) {
            throw new UserException("user has been exists");
        }

        String encode = passwordEncoder.encode(request.getPassword());

        user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(encode);

        boolean saveUser = save(user);

        if (!saveUser) {
            throw new UserException("save user info fail");
        }


        List<String> roles = new ArrayList<>();
        if (!(request.getRoles() != null && !request.getRoles().isEmpty())) {

            roles.add("common");

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());

            Long roleId = roleService.lambdaQuery().eq(Role::getRoleName, "common").one().getId();

            userRole.setRoleId(roleId);

            int insert = userRoleMapper.insert(userRole);

            if (insert != 1) {
                throw new UserException("insert userRole rel fail!");
            }

        } else {
            List<UserRole> userRoleList = new ArrayList<>();
            List<Role> roleList = roleService.lambdaQuery().in(Role::getRoleName, request.getRoles()).list();
            for (Role role : roleList) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                roles.add(role.getRoleName());
                userRoleList.add(userRole);
            }

            boolean saveBatch = userRoleService.saveBatch(userRoleList, userRoleList.size());

            if (!saveBatch) {
                throw new UserException("insert batch userRole rel fail!");
            }
        }

        RegisterResponse registerResponse = new RegisterResponse();

        String token = null;
        try {
            token = jwtUtils.buildToken(user.getUsername(), roles, 3600L * 24000);
        } catch (JOSEException e) {
            throw new UserException("create token fail");
        }

        if (StringUtils.isEmpty(token)) {
            throw new UserException("create token fail");
        }

        registerResponse.setUsername(user.getUsername()).setUserId(user.getId()).setMsg("common注册").setToken(token);

        return registerResponse;
    }

    @Override
    public Boolean delete(Long id) {

        boolean update = lambdaUpdate().eq(User::getId, id).set(User::getIsDelete, 1).update();

        if (!update) {
            throw new UserException("delete user fail");
        }

        return update;
    }

    @Transactional
    @Override
    public Boolean update(User user) {

        String token = UserContext.getTokenFromHttpServletHeader();

        String username = null;
        try {
            JWTUtils.msgVerifyUser msgVerifyUser = jwtUtils.verifyToken(token);
            username = msgVerifyUser.getUsername();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        if (StringUtils.isEmpty(username)) {
            throw new UserException("verify token can not get username");
        }

        if (!username.equals(user.getUsername())) {
            throw new UserException("has not access to update this useInfo");
        }

        User userFromDB = lambdaQuery().eq(User::getUsername, username).one();

        if (!userFromDB.getId().equals(user.getId())) {
            throw new UserException("has not access to update this useInfo");
        }

        boolean update = updateById(user);

        if (!update) {
            throw new UserException("update user info fail IN DB");
        }

        return true;
    }

    @Override
    public PageResponse<User> page(PageRequest request) {

        Pager<User> userPager = new Pager<>();
        userPager.setCurrent(request.getCurrent()).setSize(request.getSize());
        userPager.setOrders(TimeOrderSet.CREAT_TIME_DESC);
        Pager<User> page = page(userPager, lambdaQuery().eq(User::getIsDelete, 0).getWrapper());

        PageResponse<User> userPageResponse = new PageResponse<>();

        userPageResponse.setRecorders(page.getRecords()).setSize(page.getSize())
                .setTotal(page.getTotal()).setCurrent(page.getCurrent());

        return userPageResponse;
    }

    @Override
    public List<UserAndRoleVo> getRolesByUserId(String ids) {

        List<String> idsList = Arrays.stream(ids.split(",")).distinct().collect(Collectors.toList());

        List<User> userList = lambdaQuery().in(User::getId, idsList).eq(User::getIsDelete, 0).list();

        if (userList.size() != idsList.size()) {
            throw new UserException("one or more userInfo not exists");
        }

        List<Long> idsRealList = idsList.stream().map(i -> Long.parseLong(i)).collect(Collectors.toList());

        List<UserAndRoleVo> roleByUserId = this.baseMapper.getRoleByUserId(idsRealList);

        return roleByUserId;
    }
}




