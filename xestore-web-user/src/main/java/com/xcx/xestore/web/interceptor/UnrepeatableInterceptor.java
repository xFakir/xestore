package com.xcx.xestore.web.interceptor;

import com.xcx.xestore.common.annotation.Unrepeatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName : UnrepeatableInterceptor
 * @Description : 防止重复提交拦截器
 * @Author : xcx
 * @Date : 2018-10-19 17:12
 * @Version : 1.0
 */
public class UnrepeatableInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(UnrepeatableInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Unrepeatable unrepeatable = method.getAnnotation(Unrepeatable.class);
            if(unrepeatable != null){

            }

            return true;
        }else{
            return super.preHandle(request, response, handler);
        }
    }


}
