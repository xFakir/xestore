package com.xcx.xestore.common.util;

import com.xcx.xestore.common.pojo.bo.MailBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xcx.xestore.common.pojo.User;
import org.springframework.scheduling.annotation.Async;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName : MailUtils
 * @Description : 邮件工具类
 * @Author : xcx
 * @Date : 2019-01-08 14:46
 * @Version : 1.0
 */
public class MailUtils{
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    /**
     * 发件邮箱
     */
    private static final String FROM_EMAIL = "18679159286@163.com";

    /**
     * 发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
     */
    private static final String HOST = "smtp.163.com";

    private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";

    private static final String MAIL_SMTP_HOST ="smtp.163.com";

    private static final String MAIL_SMTP_PORT ="25";

    private static final String MAIL_SMTP_AUTH ="true";

    private static final String PASSWROD ="xcx123456";

    private static Session getSession(){
        Properties properties = new Properties();
        //指定发送的邮箱的邮箱协议
        properties.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        //指定SMTP服务器
        properties.setProperty("mail.smtp.host",MAIL_SMTP_HOST);
        //smtp是发信邮件服务器,端口是25
        properties.setProperty("mail.smtp.port", MAIL_SMTP_PORT);
        //指定是否需要SMTP验证
        properties.setProperty("mail.smtp.auth",MAIL_SMTP_AUTH);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(FROM_EMAIL, PASSWROD);
            }
        });
        return session;
    }

    public static void sendMail(MailBo mailBo){

        try {
            Session session = getSession();
            Message message = new MimeMessage(session);
            message.setSubject(mailBo.getSubject());
            message.setSentDate(mailBo.getSendDate());
            message.setFrom(mailBo.getFrom());
            message.setRecipient(mailBo.getRecipientType(),mailBo.getToMail());
            message.setContent(mailBo.getContent(),mailBo.getCharset());

            Transport.send(message);
            logger.info("mail has been successfully sent");
        } catch (Exception e){
            e.printStackTrace();
        }

    }




}
