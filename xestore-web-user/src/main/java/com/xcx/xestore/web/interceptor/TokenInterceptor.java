package com.xcx.xestore.web.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.xcx.xestore.common.annotation.Token;
import com.xcx.xestore.common.constant.TokenHandler;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.util.JWTUtils;
import com.xcx.xestore.common.util.TokenUtils;
import com.xcx.xestore.manager.redis.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName : TokenInterceptor
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-20 10:23
 * @Version : 1.0
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(UnrepeatableInterceptor.class);

    @Autowired
    private RedisManager redisManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token token = method.getAnnotation(Token.class);
            if(token != null){
                TokenHandler tokenHandler = token.handler();
                if(tokenHandler == TokenHandler.CHECK){
                    checkToken(request);
                }else if(tokenHandler == TokenHandler.ADD){

                    addToken(request);
                }
            }
            return true;
        }else{
            return super.preHandle(request, response, handler);
        }


    }


    private void addToken(HttpServletRequest request) throws Exception{
        String token = TokenUtils.createToken(request);
        logger.info(token);
        request.getSession().setAttribute("token",token);

    }

    private void removeToken(HttpServletRequest request){
        request.getSession().removeAttribute("token");
    }

    private void updateToken(HttpServletRequest request) throws Exception{
        String token = TokenUtils.createToken(request);
        logger.info(token);
        request.getSession().setAttribute("token",token);
    }

    private boolean check(HttpServletRequest request)throws Exception{
        String clientToken = request.getHeader("Authorization");
        String serverToken = request.getSession().getAttribute("token").toString();
        if (serverToken != null && clientToken != null) {
            //判断token是否过期
            if(JWTUtils.verifyJWT(serverToken)){
                return clientToken.equals(serverToken);
            }
        }
        return false;

    }

    private void checkToken(HttpServletRequest request) throws Exception{
        if(check(request)){
            logger.info("not repeat");
            updateToken(request);
        }else{
            logger.info("repeat");
        }
    }
}
