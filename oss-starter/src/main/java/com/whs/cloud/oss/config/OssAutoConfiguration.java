package com.whs.cloud.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.whs.cloud.oss.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(OSS.class)
@ConfigurationProperties(prefix = "oss")
public class OssAutoConfiguration {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.access_key_id}")
    private String accessKeyId;

    @Value("${oss.access_key_secret}")
    private String accessKeySecret;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;

    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Bean
    public OSSClientService ossClientService() {
        return new OSSClientService(ossClient());
    }

    @Bean
    public OSSGet ossDefaultGet() {
        return new OSSDefaultGet(ossClient());
    }

    @Bean
    public OSSPut ossDefaultPut() {
        return new OSSDefaultPut(ossClient());
    }

}