package com.whs.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whs.cloud.auth.bean.RoleResource;
import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;

import java.util.List;

/**
 * @author 86157
 * @description 针对表【cloud_auth_role_resource】的数据库操作Mapper
 * @createDate 2024-03-16 17:58:38
 * @Entity generator.baen.RoleResource
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    List<RoleAndResourceVo> loadResourceRelRole();
}




