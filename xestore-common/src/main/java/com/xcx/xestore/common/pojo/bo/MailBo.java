package com.xcx.xestore.common.pojo.bo;

import lombok.Data;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.Date;

/**
 * @ClassName : MailBo
 * @Description :
 * @Author : xcx
 * @Date : 2019-01-11 13:42
 * @Version : 1.0
 */
@Data
public class MailBo {
    private String subject;
    private Date sendDate;
    private InternetAddress from;
    private Message.RecipientType recipientType;
    private InternetAddress toMail;
    private String content;
    private String charset;
}
