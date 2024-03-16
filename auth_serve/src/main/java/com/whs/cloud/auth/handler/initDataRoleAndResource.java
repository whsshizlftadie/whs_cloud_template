package com.whs.cloud.auth.handler;

import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;
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

        List<RoleAndResourceVo> roleAndResourceVos = roleResourceMapper.loadResourceRelRole();

        Map<String, List<String>> resourceMap = new HashMap<>();

        for (RoleAndResourceVo roleAndResourceVo : roleAndResourceVos) {

            String url = roleAndResourceVo.getUrl();

            resourceMap.computeIfAbsent(url, v -> roleAndResourceVo.getRoleName());

        }

        redisTemplate.opsForHash().putAll("cloud::auth_roleAndResource", resourceMap);

    }
}
