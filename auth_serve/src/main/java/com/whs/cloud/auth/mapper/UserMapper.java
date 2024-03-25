package com.whs.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whs.cloud.auth.bean.User;
import com.whs.cloud.auth.bean.vo.UserAndRoleVo;

import java.util.List;

/**
 * @author 86157
 * @description 针对表【cloud_auth_user】的数据库操作Mapper
 * @createDate 2024-03-16 17:58:38
 * @Entity generator.baen.User
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserAndRoleVo> getRoleByUserId(List<Long> ids);
}




