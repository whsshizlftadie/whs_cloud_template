package com.whs.cloud.auth.handler;

import com.whs.cloud.auth.bean.vo.ResourceAndRoleVo;
import com.whs.cloud.auth.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class initDataRoleAndResource {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void initRel() {

        List<ResourceAndRoleVo> resourceAndRoleVos = roleResourceMapper.loadResourceRelRole();

        Map<String, List<String>> resourceMap = new HashMap<>();

        for (ResourceAndRoleVo resourceAndRoleVo : resourceAndRoleVos) {

            String url = resourceAndRoleVo.getUrl();

            resourceMap.computeIfAbsent(url, v -> resourceAndRoleVo.getRoleName());

        }

        redisTemplate.opsForHash().putAll("cloud::auth_roleAndResource", resourceMap);

    }
}
