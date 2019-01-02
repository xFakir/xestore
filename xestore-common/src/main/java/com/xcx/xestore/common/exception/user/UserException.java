package com.xcx.xestore.common.exception.user;

/**
 * @ClassName : UserException
 * @Description :
 * @Author : xcx
 * @Date : 2018-10-10 14:29
 * @Version : 1.0
 */
public class UserException extends RuntimeException{
    private String code;

    public UserException(String code, String msg){
        super(msg);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
