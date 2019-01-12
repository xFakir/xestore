package com.xcx.xestore.service.mail.impl;

import com.xcx.xestore.common.constant.MailConst;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.pojo.bo.MailBo;
import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.util.LinkUtils;
import com.xcx.xestore.common.util.MailUtils;
import com.xcx.xestore.service.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.Date;

/**
 * @ClassName : MailServiceImpl
 * @Description :
 * @Author : xcx
 * @Date : 2019-01-10 14:21
 * @Version : 1.0
 */
@Service
public class MailServiceImpl implements MailService {
    public static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    @Async("asyncMailServiceExecutor")
    public void sendActivateMail(User user,String code) {
        try {
            MailBo mailBo = new MailBo();
            mailBo.setSubject("这是一封激活账号的邮件");
            mailBo.setSendDate(new Date());
            mailBo.setFrom(new InternetAddress(MailConst.ACTIVATE_FROM_MAIL));
            mailBo.setRecipientType(Message.RecipientType.TO);
            mailBo.setToMail(new InternetAddress(user.getEmail()));
            mailBo.setContent(LinkUtils.getRegisterActivateLink(user,code));
            mailBo.setCharset("text/html;charset=UTF-8");
            MailUtils.sendMail(mailBo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
