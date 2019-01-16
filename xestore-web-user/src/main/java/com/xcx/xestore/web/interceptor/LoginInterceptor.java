package com.xcx.xestore.web.interceptor;

import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.manager.redis.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName : LoginInterceptor
 * @Description :
 * @Author : xcx
 * @Date : 2019-01-14 19:27
 * @Version : 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisManager redisManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            XResult xResult = new XResult(200,"用户未登录,请登录.");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(xResult);
            return false;
        }
        if(redisManager.zrank("onlineUsers", user.getUserId()) != null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, 1);
            date = calendar.getTime();
            redisManager.zadd("onlineUsers", date.getTime(), user.getUserId());

            logger.info("更新用户时间戳");
        } else {
            request.getSession().removeAttribute("user");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(new XResult(200,"登录过期,请重新登录."));
            logger.info("登录过期,请重新登录.");
        }


        return super.preHandle(request, response, handler);

    }
}
