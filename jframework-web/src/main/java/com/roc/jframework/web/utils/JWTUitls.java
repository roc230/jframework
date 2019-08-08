package com.roc.jframework.web.utils;

import com.sun.tools.javac.code.Attribute;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUitls {

    /**
     * 创建JWT
     * @param id 唯一标识
     * @param subject 主体名（用户名）
     * @param ttlmillis 有效时长
     * @return
     */
    public static String createJWT(String id, String subject, long ttlmillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", "xxxxxxxx");
        claims.put("user_name", "admin");
        claims.put("nick_name", "dddd");
        SecretKey key = secretKey();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setIssuer(subject)
                .signWith(signatureAlgorithm, key);

        if(ttlmillis >= 0){
            long expMillis = nowMillis + ttlmillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * 解释JWT的内容
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        SecretKey key = secretKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    private static SecretKey secretKey(){
        String stringkey = Constant.JWT_SECRET;

        byte[] encodeKey = Base64.getDecoder().decode(stringkey);

        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");

        return key;
    }

}
