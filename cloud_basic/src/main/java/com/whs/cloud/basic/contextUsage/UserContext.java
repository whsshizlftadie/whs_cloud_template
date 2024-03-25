package com.whs.cloud.basic.contextUsage;

import com.whs.cloud.basic.constant.SecurityConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserContext {

    public static String getTokenFromHttpServletHeader(){
        String token =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest().getHeader(SecurityConstants.AUTHORIZATION_KEY);

        return token;
    }
}
