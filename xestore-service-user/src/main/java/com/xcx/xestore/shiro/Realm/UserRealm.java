package com.xcx.xestore.shiro.Realm;

import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UserRealm extends AuthorizingRealm
{
    private final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro:login:count:";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro:is:lock:";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权认证...");

        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("user");
        simpleAuthorizationInfo.setRoles(roleSet);
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("add");
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        //simpleAuthorizationInfo.setRoles(userService.findRoles(username));
        //simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(username));

        logger.info("授权认证完成.");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("身份认证...");

        String username = (String) authenticationToken.getPrincipal();

        // TODO: 2018-09-18 getByUsernameAndPassword
        //searching from database
        User user = (User) userService.isUsernameExists(username).getData();



        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();

        String login_count_key = user.getUserId() + ":" + SHIRO_LOGIN_COUNT;
        String lock_status_key = user.getUserId() + ":" + SHIRO_IS_LOCK;

        //increment by 1
        value.increment(login_count_key,1);

        //if try more than 3
        if(Integer.valueOf(value.get(login_count_key)) > 3){
            value.set(lock_status_key,"TEMPORARY");
            stringRedisTemplate.expire(login_count_key,24,TimeUnit.DAYS);
        }
        if("TEMPORARY".equals(value.get(lock_status_key))){
            throw new DisabledAccountException("Your account has been locked for 24 hours");
        }
        // TODO: 2018-09-18 else if:danger,perpetuity 


        if(null == user){
            throw new UnknownAccountException("用户名或密码错误!");
        } else {// TODO: 2018-09-18 update latest login time 
            value.set(login_count_key,"0");
            logger.info("身份认证成功，登陆用户:" + username);
        }




        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
    }
}
