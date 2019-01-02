package com.xcx.xestore.service.impl;


import com.xcx.xestore.common.constant.ResultConst;
import com.xcx.xestore.common.constant.UserConst;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.util.VerifyUtils;
import com.xcx.xestore.mapper.UserMapper;
import com.xcx.xestore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 用户名是否存在-方法名
     */
    private static final String USERNAME_METHOD = "getUserByUsername";

    /**
     * 注册-方法名
     */
    private static final String REGISTER_METHOD = "registerUser";

    /**
     * 登录-方法名
     */
    private static final String LOGIN_METHOD = "login";

    @Autowired
    private UserMapper userMapper;




    private User getUserByUserId(String userId) {

        return userMapper.getUserByUserId(userId);
    }


    private User getUserByUsername(String username) {

        return userMapper.getUserByUsername(username);
    }


    private User getUserByUsernameAndPassword(User user) {

        return userMapper.getUserByUsernameAndPassword(user);
    }


    private boolean verify(User user) {
        String methodName = getMethodCaller(Thread.currentThread());
        boolean boo = false;
        switch (methodName){
            case USERNAME_METHOD:
                boo = verifyUsername(user);
                break;
            case LOGIN_METHOD:
                boo = verifyLoginInfo(user);
                break;
            case REGISTER_METHOD:
                boo = verifyRegisterInfo(user);
            default:

        }

        return boo;
    }

    private String getMethodCaller(Thread thread){
        StackTraceElement stackTraceElement = thread.getStackTrace()[2];
        return stackTraceElement.getMethodName();
    }

    private boolean verifyUsername(User user){
        boolean boo = VerifyUtils.verifyUsername(user.getUsername());
        return boo;
    }

    private boolean verifyLoginInfo(User user){
        boolean boo = VerifyUtils.verifyUsername(user.getUsername())&&VerifyUtils.verifyPassword(user.getPassword());
        return boo;
    }

    private boolean verifyRegisterInfo(User user){
        boolean boo = VerifyUtils.verifyUsername(user.getUsername()) &&
                VerifyUtils.verifyPassword(user.getPassword()) &&
                VerifyUtils.verifyEmail(user.getEmail()) &&
                VerifyUtils.verifyPhoneNumber(user.getTelephone());
        return boo;
    }


    @Override
    public XResult isUsernameExists(String username) {
        User user = new User();
        user.setUsername(username);

        user = verifyUsername(user)?getUserByUsername(username):null;

        String msg = (user != null)?"用户名已存在":"用户名可以注册";

        Integer status = (user != null)?123:124;

        return new XResult(status,msg,user);
    }

    @Override
    public XResult registerUser(User user) {
        boolean boo = verifyRegisterInfo(user);
        XResult xResult = new XResult();
        if(!boo){
            xResult.setStatus(100);
            xResult.setMsg("注册失败，注册信息不合法");
            return xResult;
        }

        user.setRegisterTime(new Date());




        return null;
    }

    @Override
    public XResult login(User user) {

        user = verifyLoginInfo(user)?getUserByUsernameAndPassword(user):null;

        if (user != null) {
            String msg = "登陆成功";
            return new XResult(ResultConst.RESULT_SUCCESS_STATUS,msg,user);
        }else {

        }

        String msg = (user != null)?"用户名或密码错误":"登陆成功";

        Integer status = (user != null)?123:124;

        return new XResult(status,msg,user);
    }

    @Override
    public XResult updateUser(User user) {
        return null;
    }


}
