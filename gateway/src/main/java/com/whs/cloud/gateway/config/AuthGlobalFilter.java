package com.whs.cloud.gateway.config;

import com.nimbusds.jose.JOSEException;
import com.whs.cloud.basic.constant.SecurityConstants;
import com.whs.cloud.basic.utils.ConvertUtils;
import com.whs.cloud.basic.utils.JWTUtils;
import com.whs.cloud.basic.utils.UrlPatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws RuntimeException {

        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders headers = request.getHeaders();

        if (headers.containsKey(SecurityConstants.AUTHORIZATION_KEY)) {

            String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);

            JWTUtils.msgVerifyUser msgVerifyUser = null;

            try {
                msgVerifyUser = jwtUtils.verifyToken(token);
            } catch (JOSEException | ParseException e) {
                throw new RuntimeException(e.getMessage());
            }

            List<String> tokenRole = msgVerifyUser.getRole();

            Map<String, Object> urlAndRoleMap
                    = redisTemplate.opsForHash().entries("cloud::auth_roleAndResource");

            boolean isPass = false;

            for (Map.Entry<String, Object> entry : urlAndRoleMap.entrySet()) {


                if (UrlPatternUtils.match(entry.getKey(), request.getURI().getPath())) {
                    Object value = entry.getValue();
                    List<String> roles = ConvertUtils.castList(value, String.class);

                    for (String roleName : roles) {
                        for (String roleContains : tokenRole) {
                            if (roleName.equals(roleContains)) {
                                isPass = true;
                                break;
                            }
                        }
                        if (isPass) {
                            break;
                        }
                    }
                }

                if (isPass) {
                    break;
                }
            }

            if (!isPass) {
                throw new RuntimeException("auth not access");
            }

        } else {
            throw new RuntimeException("not correct request");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
