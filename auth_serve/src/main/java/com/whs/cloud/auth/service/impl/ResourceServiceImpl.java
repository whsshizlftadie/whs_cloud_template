package com.whs.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.whs.cloud.auth.bean.Resource;
import com.whs.cloud.auth.mapper.ResourceMapper;


import com.whs.cloud.auth.service.ResourceService;
import org.springframework.stereotype.Service;

/**
* @author 86157
* @description 针对表【cloud_auth_resource】的数据库操作Service实现
* @createDate 2024-03-16 17:58:38
*/
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
    implements ResourceService {

}




