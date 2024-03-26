package com.whs.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;

import java.util.List;

/**
 * @author 86157
 * @description 针对表【cloud_auth_role】的数据库操作Mapper
 * @createDate 2024-03-16 17:58:38
 * @Entity generator.baen.Role
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleAndResourceVo> getRoleAndResources(List<Long> ids);
}




