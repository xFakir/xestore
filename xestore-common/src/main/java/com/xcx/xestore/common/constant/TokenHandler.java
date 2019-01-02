package com.xcx.xestore.common.constant;

/**
 * @InterfaceName : Token
 * @Description : 发送token注解
 * @Author : xcx
 * @Date : 2018-12-20 10:21
 * @Version : 1.0
 */
public enum TokenHandler {
    /**
     * 添加token
     */
    ADD,
    /**
     * 删除token
     */
    REMOVE,
    /**
     * 更新token
     */
    UPDATE,
    /**
     * 检查token
     */
    CHECK;

   /* private final int expire;

    private TokenHandler(int expire){
        this.expire = expire;
    }

    public int getExpire(){
        return expire;
    }*/
}
