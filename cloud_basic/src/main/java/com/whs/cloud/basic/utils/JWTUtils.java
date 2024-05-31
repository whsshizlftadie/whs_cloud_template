package com.whs.cloud.basic.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;

@Component
@RefreshScope
public class JWTUtils {


    @Value("${jwt.sign}")
    private String sign;

    public String getSign() {
        return sign;
    }

    public JWTUtils setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String buildToken(String username, List<String> roleName, Long expireTime) throws JOSEException {
        JWSAlgorithm algorithm = JWSAlgorithm.HS256;

        JWSHeader jwsHeader =
                new JWSHeader.Builder(algorithm)       // 加密算法
                        .type(JOSEObjectType.JWT) // 静态常量
                        .build();

        jwsHeader.getParsedBase64URL();

        Map<String, Object> playLoadMap = new HashMap();

        playLoadMap.put("username", username);
        playLoadMap.put("role", roleName);

        // 添加过期时间，当前时间 + 1小时
        Date expirationTime = new Date(System.currentTimeMillis() + expireTime);
        playLoadMap.put("exp", expirationTime.getTime() / 1000);

        JSONObject jsonObject = new JSONObject(playLoadMap);

        Payload payload = new Payload(jsonObject);

        JWSSigner jwsSigner = new MACSigner(sign);

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
// 进行签名（根据前两部分生成第三部分）
        jwsObject.sign(jwsSigner);

        return jwsObject.serialize();
    }

    public msgVerifyUser verifyToken(String token) throws JOSEException, ParseException {

        JWSObject parse = JWSObject.parse(token);

        Payload payload = parse.getPayload();

        JWSHeader header = parse.getHeader();

        JSONObject jsonObject = payload.toJSONObject();
        msgVerifyUser msgVerifyUser = new msgVerifyUser();

        List<String> role = (List<String>) jsonObject.get("role");
        msgVerifyUser.setRole(role);
        msgVerifyUser.setUsername(jsonObject.getAsString("username"));

        return msgVerifyUser;
    }

    public class msgVerifyUser {
        private String username;
        private List<String> role;

        @Override
        public String toString() {
            return new StringJoiner(", ", msgVerifyUser.class.getSimpleName() + "[", "]")
                    .add("username='" + username + "'")
                    .add("role='" + role + "'")
                    .toString();
        }

        public String getUsername() {
            return username;
        }

        public msgVerifyUser setUsername(String username) {
            this.username = username;
            return this;
        }

        public List<String> getRole() {
            return role;
        }

        public msgVerifyUser setRole(List<String> role) {
            this.role = role;
            return this;
        }
    }
}
