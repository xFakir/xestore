package com.xcx.xestore.web.controller;

import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : PortalController
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-10 16:09
 * @Version : 1.0
 */

@RestController
public class PortalController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public XResult register(){
        XResult xResult = new XResult(11,"ss",null);


        return xResult;
    }

}
