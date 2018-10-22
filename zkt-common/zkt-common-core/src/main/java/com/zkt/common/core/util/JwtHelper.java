package com.zkt.common.core.util;

import com.zkt.common.core.constant.CommonConstant;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.jwt
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
public class JwtHelper {
    private JwtHelper() {
    }

    public static Claims parseJWT(String jsonWebToken, String base64Security) throws Exception{
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
    }

    public static String createJWT(String userName, String userId, String role,
                                   String audience, String issuer, long tTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim(CommonConstant.JWT_KEY_USER_NAME, userName)
                .claim(CommonConstant.JWT_KEY_USER_ID, userId)
                .claim(CommonConstant.JWT_KEY_USER_ROLE, role)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (tTLMillis >= 0) {
            long expMillis = nowMillis + tTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}
