package com.xcx.xestore.common.exception.user;

import com.xcx.xestore.common.exception.common.ServiceException;

/**
 * @ClassName : UserServiceException
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-10 14:29
 * @Version : 1.0
 */
public class UserServiceException extends ServiceException {

    public UserServiceException(Integer code, String msg) {
        super(code, msg);
    }
}
