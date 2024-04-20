package com.whs.cloud.auth.controller.role;


import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.service.RoleService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/role")
public class RoleCommonController {

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


}
