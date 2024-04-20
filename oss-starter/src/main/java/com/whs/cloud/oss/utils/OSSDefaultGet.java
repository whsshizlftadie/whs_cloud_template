package com.whs.cloud.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class OSSDefaultGet implements OSSGet {

    private final OSS ossClient;

    public OSSDefaultGet(OSS ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public URL getURL(String bucket, String ObjectName) {

        return ossClient.generatePresignedUrl(bucket,ObjectName,new Date(new Date().getTime() + 180 * 1000L));
    }

    @Override
    public String getText(String bucket, String ObjectName) throws IOException {
        OSSObject object = ossClient.getObject(bucket, ObjectName);
        InputStream objectInput = object.getObjectContent();
        byte[] bytes = IOUtils.readStreamAsByteArray(objectInput);;
        return new String(bytes);
    }
}
