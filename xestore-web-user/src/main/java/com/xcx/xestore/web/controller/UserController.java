package com.xcx.xestore.web.controller;

import com.xcx.xestore.common.annotation.Token;
import com.xcx.xestore.common.constant.TokenHandler;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.util.VerifyCodeUtils;
import com.xcx.xestore.manager.redis.RedisManager;
import com.xcx.xestore.service.UserService;
import com.xcx.xestore.service.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisManager redisManager;

    @RequestMapping(value = "/showRegister")
    @Token(handler = TokenHandler.ADD)
    public String registerView(){
        return "a";
    }

    @RequestMapping(value = "/showLogin")
    public String loginView(){
        return "/user/login";
    }

    @RequestMapping(value = "showUserDetail")
    public String userDetailView(){
        return "/user/detail";
    }



    @PostMapping(value = "/login")
    public XResult login(HttpServletRequest request, Model model, User user){

        XResult xResult = userService.login(user);

        request.getSession().setAttribute("user",(User)xResult.getData());

        return xResult;
    }

    @PostMapping(value = "/register")
    @Token(handler = TokenHandler.CHECK)
    public XResult register(User user){

        userService.registerUser(user);

        //异步发送邮件
        String activateCode = VerifyCodeUtils.getActivateCode();
        mailService.sendActivateMail(user,activateCode);

        //将激活码存入redis
        redisManager.setex(user.getUserId(),1 * 60,activateCode);

        XResult xResult = new XResult(11,"ss",user);


        return xResult;
    }

    @PostMapping(value = "/activate/{code}")
    public XResult activate(User user,@PathVariable("code") String code){



        return userService.activateUser(user,code);

    }

    @GetMapping(value = "/isUsernameExists")
    public XResult isUsernameExists(User user){
        if(user.getUsername() != null){
            return userService.isUsernameExists(user.getUsername());
        }
        return new XResult(100,"error",null);
    }

    @RequestMapping(value = "/exceptionTest")
    public XResult exceptionTest() throws Exception{

        userService.exceptionTest();

        return new XResult(100,"error",null);
    }
}
