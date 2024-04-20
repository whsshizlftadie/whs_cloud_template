package com.whs.cloud.auth.controller.role;

import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.request.role.RoleBindUserRequest;
import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/role")
public class RoleAdminController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/bind/users")
    public RestResult bindUsers(@RequestBody RoleBindUserRequest request){
        return RestResult.success(roleService.roleBindUser(request));
    }

    @PutMapping("/update")
    public RestResult update(@RequestBody Role role){
        return RestResult.success(roleService.update(role));
    }

    @GetMapping("/del/{id}")
    public RestResult del(@PathVariable Long id) {
        return RestResult.success(roleService.del(id));
    }

    @PostMapping("/add")
    public RestResult add(@RequestBody Role role){
        return RestResult.success();
    }

    @GetMapping("/resources/{ids}")
    public RestResult getResourcesByRoleId(@PathVariable String ids){
        return RestResult.success(roleService.roleAndResources(ids));
    }
}
