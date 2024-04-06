package com.whs.cloud.gateway.FeignClient;

import com.whs.cloud.basic.result.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-service")
public interface UserServiceClient {

    @GetMapping("/auth/user/count")
    RestResult count();
}
