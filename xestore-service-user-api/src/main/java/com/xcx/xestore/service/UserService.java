package com.xcx.xestore.service;


import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;

/**
 * 用户服务
 * @author xcx
 * @version v1.0.0
 */
public interface UserService {
    /**
     * 2018-10-09 11:10
     * @param userId 用户Id
     * @return com.xcx.xestore.common.pojo.vo.XResult
     */
    //XResult getUserByUserId(String userId);

    /**
     * @Author : xcx
     * @Description : 根据用户名查询用户
     * @Date : 2018-10-09 11:07
     * @Param : username 用户名
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    //XResult getUserByUsername(String username);

    /**
     * @Description : 根据用户名密码查询用户
     * @Date : 2018-10-11 11:17
     * @param user 用户
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    //XResult getUserByUsernameAndPassword(User user);

    /***
     * @Description : 验证用户信息合法性
     * @Date : 2018-10-12 15:49
     * @param user 用户
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    //XResult verify(User user);

    /**
     * @Description : 用户名是否存在
     * @Date : 2018-10-13 10:02
     * @param username 用户名
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    XResult isUsernameExists(String username);

    /**
     * @Description : 注册
     * @Date : 2018-10-12 16:00
     * @param user 用户
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    XResult registerUser(User user);

    /**
     * @Description : 登录
     * @Date : 2018-10-12 16:00
     * @param user 用户
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    XResult login(User user);
    
    /**
     * @Description : 修改用户信息
     * @Date : 2018-10-12 16:00
     * @param user 用户
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    XResult updateUser(User user);

    
}
