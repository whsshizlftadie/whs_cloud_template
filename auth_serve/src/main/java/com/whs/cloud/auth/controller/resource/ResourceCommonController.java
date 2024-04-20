package com.whs.cloud.auth.controller.resource;

import com.whs.cloud.auth.service.ResourceService;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/common/resource")
public class ResourceCommonController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/list")
    public RestResult list(){
        return RestResult.success(resourceService.list());
    }
}
