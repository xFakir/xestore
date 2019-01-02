package com.xcx.xestore.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @ClassName : JWTUtils
 * @Description : JSON WEB TOKEN 工具类
 * @Author : xcx
 * @Date : 2018-10-19 15:42
 * @Version : 1.0
 */
public class JWTUtils {

    private static Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * 密钥
     */
    private static final String SECRET = "xcx52smm";

    /**
     * 签发者
     */
    private static final String ISSUER = "xcx";

    /**
     * 过期时间单位
     */
    private static final int EXPIRE_UNIT = Calendar.SECOND;

    /**
     * 生成JWT
     * @param claims
     * @param expire
     * @return
     * @throws Exception
     */
    public static String createJWT(Map<String,String> claims, int expire) throws Exception {
        //签发时间
        Date istDate = new Date();

        //设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(EXPIRE_UNIT, expire);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");


        JWTCreator.Builder builder = JWT.create()
                .withHeader(map)
                .withExpiresAt(expiresDate)
                .withIssuedAt(istDate)
                .withIssuer(ISSUER);
        claims.forEach((k,v) -> builder.withClaim(k,v));
        String token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证JWT
     * @param token
     * @return
     * @throws Exception
     */
    public static boolean verifyJWT(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);

        } catch (Exception e) {
            throw new RuntimeException("凭证过期！");
        }
        return true;

    }

    /**
     * 获取claims
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> getClaims(String token) throws Exception{
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaims();
    }

    /**
     * 生成普通token
     * @return
     * @throws Exception
     */
    public static String createToken() throws Exception{
        String token = UUID.randomUUID().toString().replace("-","");


        return token;

    }


    public static void main(String[] args) throws Exception {
        //String token = JWTUtils.createJWT(null,0);

        System.out.println(createToken());
        //正常测试
       /* Map<String, Claim> verifyToken = JWTUtils.verifyJWT(token);
        String asString = verifyToken.get("name").asString();
        System.out.println(asString);*/

        //过期测试

        /*token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoidGVzdCIsImV4cCI6MTUyNzQ5NzA3MiwiaWF0IjoxNTI3NDk3MDExLCJhZ2UiOjExfQ.yg1Hn4FT0OWu8KecNzvaayMEbbDrKctjWlI4bblcRfA";
        Map<String, Claim> verifyToken2 = JWTUtils.verifyJWT(token);*/
    }
}
