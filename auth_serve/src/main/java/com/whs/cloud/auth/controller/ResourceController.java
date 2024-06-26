package com.whs.cloud.auth.controller;


import com.whs.cloud.auth.bean.Resource;
import com.whs.cloud.auth.bean.request.resource.ResourceBindRoleRequest;
import com.whs.cloud.auth.service.ResourceService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @GetMapping("/list")
    public RestResult list(){
        return RestResult.success(resourceService.list());
    }

    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Long id){
        return RestResult.success(resourceService.delete(id));
    }

    @PostMapping("/add")
    public RestResult add(Resource resource){
        return RestResult.success(resourceService.add(resource));
    }

    @PutMapping("/update")
    public RestResult update(Resource resource){
        return RestResult.success(resourceService.update(resource));
    }

    @PostMapping("/bind/roles")
    public RestResult bind(@RequestBody ResourceBindRoleRequest request){
        return RestResult.success(resourceService.bindToRole(request));
    }
}
