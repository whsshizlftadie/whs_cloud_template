package com.whs.cloud.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.whs.cloud.basic.result.RestResult;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;

@Component
public class GatewayErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    /**
     * 处理给定的异常
     *
     * @param exchange
     * @param ex
     * @return
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        RestResult<Object> result = RestResult.failure(ex.getMessage());

        DataBuffer dataBuffer = response.bufferFactory()
                .allocateBuffer().write(JSON.toJSONBytes(result));
        response.setStatusCode(HttpStatus.OK);
        //基于流形式
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeAndFlushWith(Mono.just(ByteBufMono.just(dataBuffer)));
    }
}
