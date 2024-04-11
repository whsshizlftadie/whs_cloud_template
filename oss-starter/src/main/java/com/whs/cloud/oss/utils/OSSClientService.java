package com.whs.cloud.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OSSClientService {

    private final OSS OSSClient;

    @Autowired
    public OSSClientService(OSS ossClient) {
        OSSClient = ossClient;
    }

    public OSSRestResult uploadFileToByte(String bucket,String fileName,MultipartFile multipartFile){
        try {
            uploadBytes(bucket,fileName, multipartFile.getBytes());
        } catch (Exception e) {
            return new OSSRestResult().fail(e.getMessage());
        }
        return new OSSRestResult<>();
    }

    public OSSRestResult uploadText(String bucket, String fileName, String content) {
        try {
            OSSClient.putObject(bucket, fileName, new ByteArrayInputStream(content.getBytes()));
        } catch (Exception e) {
            return new OSSRestResult().fail("上传过程发送了异常:" + e.getMessage());
        }
        return new OSSRestResult().success(bucket + "/" + fileName);
    }

    public OSSRestResult getTextFromOSS(String Bucket, String ObjectKey) {
        try {
            OSSObject object = OSSClient.getObject(Bucket, ObjectKey);
            InputStream objectInput = object.getObjectContent();
            byte[] bytes = IOUtils.readStreamAsByteArray(objectInput);
            return new OSSRestResult().success(new String(bytes));
        } catch (Exception e) {
            return new OSSRestResult().fail("获取内容发生了异常:" + e.getMessage());
        }

    }

    private void uploadBytes(String bucket,String fileName,byte[] bytes){
        try{
            OSSClient.putObject(bucket,fileName,new ByteArrayInputStream(bytes));
        }catch (Exception ex){
            throw ex;
        }
    }

    public class OSSRestResult<T> {

        private Integer code;

        private T message;

        public Integer getCode() {
            return code;
        }

        public OSSRestResult<T> setCode(Integer code) {
            this.code = code;
            return this;
        }

        public T getMessage() {
            return message;
        }

        public OSSRestResult<T> setMessage(T message) {
            this.message = message;
            return this;
        }

        public OSSRestResult fail(T t) {
            OSSRestResult tossRestResult = new OSSRestResult();
            tossRestResult.setCode(-100);
            tossRestResult.setMessage(t);
            return tossRestResult;
        }

        public OSSRestResult success(T t) {
            OSSRestResult tossRestResult = new OSSRestResult();
            tossRestResult.setCode(100);
            tossRestResult.setMessage(t);
            return tossRestResult;
        }
    }
}
