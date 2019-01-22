package com.xcx.xestore.service.impl;


import com.xcx.xestore.common.constant.ResultConst;
import com.xcx.xestore.common.exception.common.PersistenceException;
import com.xcx.xestore.common.exception.common.ServiceException;
import com.xcx.xestore.common.exception.user.UserServiceException;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.util.VerifyUtils;
import com.xcx.xestore.manager.redis.RedisManager;
import com.xcx.xestore.mapper.UserMapper;
import com.xcx.xestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Autowired
    private RedisManager redisManager;




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
        if (!boo) {
            xResult.setStatus(100);
            xResult.setMsg("注册失败，注册信息不合法");
            return xResult;
        }

        user.setRegisterTime(new Date());
        user.setActivated(0);

        userMapper.saveUser(user);

        xResult.setStatus(200);
        xResult.setMsg("注册成功，请在邮箱中激活。");
        return xResult;
    }

    @Override
    public XResult activateUser(User user,String code){
        XResult xResult = new XResult();
        if(verifyActivateCode(user,code)){
            user.setActivated(1);
            userMapper.updateUser(user);
            logger.info("激活成功");
            xResult.setStatus(200);
            xResult.setMsg("激活成功");
            return xResult;
        }
        xResult.setStatus(100);
        xResult.setMsg("激活失败");
        return xResult;
    }

    @Override
    public XResult login(User user) {

        user = verifyLoginInfo(user)?getUserByUsernameAndPassword(user):null;

        if (user != null) {
            String msg = "";
            if(redisManager.sismember("onlineUsers", user.getUserId())) {
                msg = "用户已登录";
                // TODO: 2019-01-16 记录ip 异地登录发送通知等等

            } else {

                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.MINUTE, 1);
                date = calendar.getTime();

                redisManager.zadd("onlineUsers", date.getTime(), user.getUserId());

                msg = "登陆成功";
            }

            return new XResult(ResultConst.RESULT_SUCCESS_STATUS,msg,user);
        }else {
            return new XResult(ResultConst.RESULT_FAILURE_STATUS,"用户名或密码错误");
        }

    }

    @Override
    public XResult updateUser(User user) {
        return null;
    }

    @Override
    public XResult exceptionTest(){
        User user = new User();
        user.setUserId("1234");
        try {
            userMapper.updateUser(user);
            return null;
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ServiceException(1,"service ex",e);
        }

    }

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

    /**
     * 将用户和激活码存入redis
     * @param user
     * @param code
     */
    private void saveActivateCode(User user,String code){
        redisManager.setex(user.getUserId(),1 * 60,code);
        logger.info("激活码存入redis");

    }

    /**
     * 验证用户和激活码
     * @param user
     * @param code
     * @return
     */
    private boolean verifyActivateCode(User user, String code){

        return code.equals(redisManager.get(user.getUserId()));
    }

}
