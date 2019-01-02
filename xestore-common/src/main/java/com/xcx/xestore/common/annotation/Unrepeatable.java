package com.xcx.xestore.common.annotation;

import java.lang.annotation.*;

/**
 * @InterfaceName : Unrepeatable
 * @Description : 不可重复提交注解
 * @Author : xcx
 * @Date : 2018-10-20 10:21
 * @Version : 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unrepeatable {

}
