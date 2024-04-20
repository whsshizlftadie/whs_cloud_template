package com.whs.cloud.oss.utils;

import java.io.IOException;
import java.net.URL;

public interface OSSGet {

    URL getURL(String bucket,String ObjectName);

    String getText(String bucket,String ObjectName) throws IOException;
}
