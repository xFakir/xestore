package com.xcx.xestore.common.pojo.vo;

import com.xcx.xestore.common.constant.ResultConst;

/**
 * @ClassName : MD5Utils
 * @Description : 返回结果
 * @author xcx
 * @Date 2018-10-05 11:05
 * @Version : 1.0
 */
public class XResult {

    // 响应状态码
    private Integer status;

    // 响应消息
    private String msg;

    // 响应数据
    private Object data;

    public XResult(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public XResult(Integer status,String msg){
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public XResult(){

    }
    /**
     * @Author : xcx
     * @Description : 返回成功结果
     * @Date : 2018-10-06 10:11
     * @Param : [msg, data]
     * @return com.xcx.xestore.common.pojo.vo.XResult
     */
    public static XResult success(String msg,Object data) {
        return new XResult(ResultConst.RESULT_SUCCESS_STATUS,msg,data);
    }
    
    /**
     * @Author : xcx
     * @Description : 返回失败结果
     * @Date : 2018-10-06 10:46
     * @Param : [msg, data]
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    public static XResult failure(String msg,Object data){
        return new XResult(ResultConst.RESULT_FAILURE_STATUS,msg,data);
    }

    /**
     * @Author : xcx
     * @Description : 构建返回结果
     * @Date : 2018-10-05 14:35
     * @Param : [status, msg, data]
     * @return : com.xcx.xestore.common.pojo.vo.XResult
     */
    public static XResult build(Integer status,String msg,Object data){
        
        return new XResult(status,msg,data);

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
