package com.whs.cloud.oss.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OSSPut {

    void putObject(String bucket, String ObjectName, MultipartFile file) throws IOException;

    void putText(String bucket,String ObjectName,String text);
}
