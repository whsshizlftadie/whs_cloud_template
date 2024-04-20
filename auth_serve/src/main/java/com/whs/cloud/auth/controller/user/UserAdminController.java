package com.whs.cloud.auth.controller.user;


import com.whs.cloud.auth.bean.request.page.PageRequest;
import com.whs.cloud.auth.service.UserService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/page")
    public RestResult page(@RequestBody PageRequest request) {
        return RestResult.success(userService.page(request));
    }

    @GetMapping("roles/{ids}")
    public RestResult getRoleById(@PathVariable String ids) {
        return RestResult.success(userService.getRolesByUserId(ids));
    }

    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Long id) {
        return RestResult.success(userService.delete(id));
    }

}
