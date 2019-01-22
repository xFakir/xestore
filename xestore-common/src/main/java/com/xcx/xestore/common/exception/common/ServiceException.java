package com.xcx.xestore.common.exception.common;

/**
 * @ClassName : ServiceException
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-11 10:40
 * @Version : 1.0
 */
public class ServiceException extends BaseException {

    public ServiceException(Integer code, String msg) {
        super(code, msg);
    }
    public ServiceException(Integer code, String msg,Throwable throwable) {
        super(code, msg,throwable);
    }
}
