package com.xcx.xestore.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName : TokenUtils
 * @Description :
 * @Author : xcx
 * @Date : 2018-12-25 14:30
 * @Version : 1.0
 */
public class TokenUtils {
    private final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * @Description : 生成token
     * @Date : 2018-12-25 15:01
     * @param request
     * @return : java.lang.String
     */
    public static String createToken(HttpServletRequest request) throws Exception{
        //ip地址
        String ip = IpUtils.getIpAddress(request).replace(".","");
        //请求方法名
        String method = request.getMethod();
        //当前时间
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        //随机数
        String random = String.valueOf(new Random().nextInt(24 * 60 * 60));
        //使用JWT加密
        Map<String,String> claims = new HashMap<String,String>();
        claims.put("ip",ip);
        claims.put("method",method);
        claims.put("currentTimeMillis",currentTimeMillis);
        claims.put("random",random);

        String token = JWTUtils.createJWT(claims, 10 * 60);

        return token;
    }

}
