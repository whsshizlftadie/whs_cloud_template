package com.whs.cloud.oss.utils;

import com.aliyun.oss.OSS;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public class OSSDefaultPut implements OSSPut {

    private final OSS ossClient;

    public OSSDefaultPut(OSS ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public void putObject(String bucket, String ObjectName, MultipartFile file) throws IOException {
        ossClient.putObject(bucket,ObjectName,file.getInputStream());
    }

    @Override
    public void putText(String bucket, String ObjectName, String text) {
        ossClient.putObject(bucket,ObjectName,new ByteArrayInputStream(text.getBytes()));
    }
}
