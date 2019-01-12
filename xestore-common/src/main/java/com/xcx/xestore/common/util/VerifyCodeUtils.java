package com.xcx.xestore.common.util;

import com.xcx.xestore.common.pojo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @ClassName : DateUtils
 * @Description : 生成验证码
 * @Author : xcx
 * @Date : 2018-10-05 12:16
 * @Version : 1.0
 */

public class VerifyCodeUtils {
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeUtils.class);


    public static String getActivateCode(){
        return UUID.randomUUID().toString().replace("-","");

    }


}
