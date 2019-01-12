package com.xcx.xestore.service.mail;

import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.vo.XResult;

/**
 * @ClassName : MailService
 * @Description :
 * @Author : xcx
 * @Date : 2019-01-10 14:20
 * @Version : 1.0
 */
public interface MailService {
    void sendActivateMail(User user,String code);

}
