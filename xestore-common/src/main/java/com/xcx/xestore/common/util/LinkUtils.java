package com.xcx.xestore.common.util;

import com.xcx.xestore.common.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName : LinkUtils
 * @Description : 链接生成工具类
 * @Author : xcx
 * @Date : 2019-01-08 22:10
 * @Version : 1.0
 */
public class LinkUtils {
    private static final Logger logger = LoggerFactory.getLogger(LinkUtils.class);

    public static String getRegisterActivateLink(User user, String code){
        return "<html><head></head><body><h1>您好，"+ user.getUsername() +"。</h1><h3>点击以下链接完成注册。</h3><h3><a href='点击此处激活'>http://localhost:8083/user/activate/" + code
                + "</href></h3></body></html>";
    }
}
