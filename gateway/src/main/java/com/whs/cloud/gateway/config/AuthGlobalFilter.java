package com.whs.cloud.gateway.config;

import com.whs.cloud.basic.result.RestResult;
import com.whs.cloud.gateway.FeignClient.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Lazy
    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
