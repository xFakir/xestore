package com.xcx.xestore.common.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : VerifyUtils
 * @Description : 前端数据验证工具类
 * @Author : xcx
 * @Date : 2018-10-12 16:15
 * @Version : 1.0
 */
public class VerifyUtils {

    /** 邮件 */
    private static final String V_EMAIL="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /** 颜色 */
    private static final String V_COLOR="^[a-fA-F0-9]{6}$";

    /** url */
    private static final String V_URL="^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    /** 仅中文 */
    private static final String V_CHINESE="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /** 仅ACSII字符 */
    private static final String V_ASCII="^[\\x00-\\xFF]+$";

    /** 邮编 */
    private static final String V_ZIPCODE="^\\d{6}$";

    /** 手机 */
    private static final String V_MOBILE="^(1)[0-9]{10}$";

    /** ip地址 */
    private static final String V_IP4="^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /** 图片 */
    private static final String V_PICTURE="(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    /** 压缩文件 */
    private static final String V_RAR="(.*)\\.(rar|zip|7zip|tgz)$";

    /** 日期 */
    private static final String V_DATE="^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    /** QQ号码 */
    private static final String V_QQ_NUMBER="^[1-9]*[1-9][0-9]*$";

    /** 电话号码的函数(包括验证国内区号,国际区号,分机号) */
    private static final String V_TEL="^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    /** 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串 */
    private static final String V_USERNAME="^\\w+$";

    /** 字母 */
    private static final String V_LETTER="^[A-Za-z]+$";

    /** 大写字母 */
    private static final String V_LETTER_U="^[A-Z]+$";

    /** 小写字母 */
    private static final String V_LETTER_I="^[a-z]+$";

    /** 身份证 */
    private static final String V_IDCARD ="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**验证密码(数字和英文同时存在)*/
    private static final String V_PASSWORD_REG="[A-Za-z]+[0-9]";

    /**验证密码长度(6-18位)*/
    private static final String V_PASSWORD_LENGTH="^\\d{6,18}$";

    /**验证一个月的31天*/
    private static final String V_31DAYS="^((0?[1-9])|((1|2)[0-9])|30|31)$";

    /**
     * @Description : 验证日期
     * @Date : 2018-10-12 16:34
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyDate(String str){
        return match(V_DATE, str);
    }

    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyUsername(String str){
        return match(V_USERNAME,str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyPassword(String str){
        return match(V_PASSWORD_REG,str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyEmail(String str){
        return match(V_EMAIL, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyPhoneNumber(String str){
        return match(V_TEL, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyIDCard(String str){
        return match(V_IDCARD, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:54
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyIP(String str){
        return match(V_IP4, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:55
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyURL(String str){
        return match(V_URL, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:55
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyImage(String str){
        return match(V_PICTURE, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:55
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyColor(String str){
        return match(V_COLOR, str);
    }
    
    /**
     * @Description : 
     * @Date : 2018-10-12 16:55
     * @param str 待验证字符串
     * @return : boolean
     */
    public static boolean verifyZIP(String str){
        return match(V_RAR, str);
    }


    /**
     * @Description : 字符串验证
     * @Date : 2018-10-12 16:27
     * @param regex 正则表达式字符串
     * @param str 待验证字符串
     * @return : boolean
     */
    private static boolean match(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
