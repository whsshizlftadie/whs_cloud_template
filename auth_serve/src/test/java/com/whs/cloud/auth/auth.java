package com.whs.cloud.auth;


import com.nimbusds.jose.JOSEException;
import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.vo.RoleAndResourceVo;
import com.whs.cloud.auth.mapper.RoleResourceMapper;
import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.auth.utils.jwt.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class auth {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private JWTUtils jwtUtils;

    @Test
    public void testMysqlCon() {
        System.out.println(userService.count());
    }

    @Test
    public void testInitRoleRelResourceUrlSql() {
        List<RoleAndResourceVo> roleAndResourceVos = roleResourceMapper.loadResourceRelRole();

        roleAndResourceVos.forEach(i -> System.out.println(i));
    }

    @Test
    public void tokenBuildTest() throws JOSEException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("admin");
        strings.add("vip");
        String s = jwtUtils.buildToken("whs", strings, 3600 * 24000L);
        System.out.println(s);
    }

    @Test
    public void tokenVerify() throws ParseException, JOSEException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbImFkbWluIiwidmlwIl0sImV4cCI6MTcxMDY5NzQ0MSwidXNlcm5hbWUiOiJ3aHMifQ.V-Su65NZJ0AqD-gW0mrOyFS34XkmI19nUD3dYBD0_-8";
        JWTUtils.msgVerifyUser msgVerifyUser = jwtUtils.verifyToken(token);
        System.out.println(msgVerifyUser);

    }

    @Test
    public void testGetRolesByUserId() {
//        List<Role> rolesByUserId = userService.getRolesByUserId(1L);
//        rolesByUserId.forEach(i -> System.out.println(i));
    }
}
