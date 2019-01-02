package com.xcx.xestore.controller;

import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.manager.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : RedisController
 * @Description :
 * @Author : xcx
 * @Date : 2018-12-11 16:44
 * @Version : 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisManager redisManager;

    @RequestMapping("/test")
    public XResult test(){

        String str = redisManager.get("test");

        XResult xResult = new XResult(1,"success",str);

        return xResult;


    }
}
