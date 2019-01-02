package com.xcx.xestore.common.annotation;

import com.xcx.xestore.common.constant.TokenHandler;

import java.lang.annotation.*;

/**
 * @InterfaceName : Token
 * @Description : token相关注解
 * @Author : xcx
 * @Date : 2018-10-20 10:21
 * @Version : 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {
    TokenHandler handler() default TokenHandler.CHECK;
}
