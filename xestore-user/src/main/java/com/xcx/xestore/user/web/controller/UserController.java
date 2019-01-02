package com.xcx.xestore.user.web.controller;

import com.xcx.xestore.common.constant.ResultConst;
import com.xcx.xestore.common.constant.UserConst;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.util.JWTUtils;
import com.xcx.xestore.manager.redis.RedisManager;
import com.xcx.xestore.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    private RedisManager redisManager;

    @RequestMapping(value = "/showSignUp")
    public String registerView(){
        return "/signUp";
    }

    @RequestMapping(value = "/showSignIn")
    public String loginView(){
        return "/user/signIn";
    }

    @RequestMapping(value = "showUserDetail")
    public String userDetailView(){
        return "/user/detail";
    }



    @PostMapping(value = "/signIn")
    public XResult login(HttpServletRequest request, Model model, User user){
        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);*/
        XResult xResult = new XResult();



        //验证账号密码
        xResult = userService.login(request,user);
        if(xResult.getStatus().equals(ResultConst.RESULT_SUCCESS_STATUS)){
            //账号密码正确
            user = (User) xResult.getData();
            request.getSession().setAttribute("user",user);

            //生成token
            Map<String,String> claims = new HashMap<>();
            claims.put("username",user.getUsername());

            try {
                String token = JWTUtils.createToken(claims,UserConst.KEEP_LOGIN_TIME);
                //添加token
                request.getSession().setAttribute("token",token);


            } catch (Exception e){
                //TODO: 捕获异常
            }



        } else {
            //账号密码错误

        }



        return null;
    }

    @PostMapping(value = "/signUp")
    public XResult register(User user){

        userService.registerUser(user);


        XResult xResult = new XResult(11,"ss",user);


        return xResult;
    }

    @GetMapping(value = "/isUsernameExists/{username}")
    public XResult isUsernameExists(@PathVariable String username){

        return userService.isUsernameExists(username);
    }

}
