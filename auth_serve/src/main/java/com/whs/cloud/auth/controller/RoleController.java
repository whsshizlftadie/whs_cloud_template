package com.whs.cloud.auth.controller;


import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * role http api
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping("/page")
    public RestResult page(@RequestBody PageRequest request) {
        return RestResult.success(roleService.page(request));
    }

    @GetMapping("{id}")
    public RestResult roleById(@PathVariable Long id) {
        return RestResult.success(roleService.getById(id));
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
        return RestResult.success();
    }

}
