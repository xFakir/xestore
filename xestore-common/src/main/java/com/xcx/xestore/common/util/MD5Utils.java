package com.xcx.xestore.common.util;

import com.xcx.xestore.common.constant.ExceptionConst;
import com.xcx.xestore.common.exception.common.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.xml.ws.Service;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName : MD5Utils
 * @Description : MD5工具类
 * @Author : xcx
 * @Date 2018-10-05 12:05
 * @Version : 1.0
 */
public class MD5Utils {

    /**
     * @Description : 加密字符串
     * @Date : 2018-10-11 10:58
     * @param str 待加密字符串
     * @param key 密钥
     * @param charset 指定字符集
     * @return : java.lang.String
     */
    public static String encript(String str, String key, String charset){
        str += key;
        return DigestUtils.md5DigestAsHex(getContent(str,charset));
    }
    
    /**
     * @Description : 将字符串转换未byte数组
     * @Date : 2018-10-11 10:55
     * @param str 待加密字符串
     * @param charset 指定字符集
     * @return : byte[]
     */
    private static byte[] getContent(String str, String charset){
        if(StringUtils.isEmpty(charset)){
            return str.getBytes();
        }

        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(ExceptionConst.EXCEPTION_CHARSET,"您指定的字符集有误，请检查。");
        }
    }

    public static void main(String[] args) {
        System.out.println(encript("123","smm",null));
    }
}
