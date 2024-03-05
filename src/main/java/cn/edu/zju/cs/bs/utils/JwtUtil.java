package cn.edu.zju.cs.bs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static String genToken(Map<String, Object> userInfos) {
        return JWT.create()
                .withClaim("user", userInfos)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*6))
                .sign(Algorithm.HMAC512("zjucsbs"));
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC512("zjucsbs"))
                .build()
                .verify(token)
                .getClaim("user")
                .asMap();
    }
}
