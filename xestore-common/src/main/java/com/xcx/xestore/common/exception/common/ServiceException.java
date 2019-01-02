package com.xcx.xestore.common.exception.common;

/**
 * @ClassName : ServiceException
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-11 10:40
 * @Version : 1.0
 */
public class ServiceException extends RuntimeException {
    private Integer code;

    public ServiceException(Integer code, String msg){
        super(msg);
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
