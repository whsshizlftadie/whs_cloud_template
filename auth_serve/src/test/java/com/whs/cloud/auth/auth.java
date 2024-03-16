package com.whs.cloud.auth;


import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;
import com.whs.cloud.auth.mapper.RoleResourceMapper;
import com.whs.cloud.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class auth {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Test
    public void testMysqlCon() {
        System.out.println(userService.count());
    }

    @Test
    public void testInitRoleRelResourceUrlSql() {
        List<RoleAndResourceVo> roleAndResourceVos = roleResourceMapper.loadResourceRelRole();

        roleAndResourceVos.forEach(i -> System.out.println(i));
    }
}
