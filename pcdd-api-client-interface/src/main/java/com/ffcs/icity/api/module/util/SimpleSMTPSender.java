package com.ffcs.icity.api.module.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleSMTPSender {

    Properties emailProperties = new Properties(null);
    Session mailConnection = null;
    Message message = null;
    Address sender = null;
    Address[] allReceiver = null;
    Multipart mtp = null;
    String senderEmail;
    String senderPassWord;
    String SMTP;

    public SimpleSMTPSender(String senderEmail, String senderPassWord,
                            String sMTP) {
        super();
        this.senderEmail = senderEmail;
        this.senderPassWord = senderPassWord;
        SMTP = sMTP;
    }

    public void setProperties(String receiverList) throws MessagingException{

        //传递一个邮件服务器名smtp.163.com
        //mail.smtp.host代表是发信人所在的邮箱服务器名
        emailProperties.put("mail.smtp.host", SMTP);
        emailProperties.put("mail.smtp.auth", "true");
        //对于发送邮件，只需要保证发送人所在的邮件服务器正确打开就可以了
        //收信人的邮箱可以是任意地址，如@163.com,@qq.com,@126.com

        //创建一个程序与邮件服务器的通信
        mailConnection = Session.getInstance(emailProperties, null);
        message = new MimeMessage(mailConnection);

        //设置发送人和接受人

            sender = new InternetAddress(senderEmail);
            //群发邮件
            message.setFrom(sender);
            allReceiver = InternetAddress.parse(receiverList);
            message.setRecipients(Message.RecipientType.TO, allReceiver);

    }

    public void setMessage(String subject, String content) throws MessagingException {

            message.setSubject(subject);
            //新建一个MimeMultipart对象用来存放多个BodyPart对象
            mtp = new MimeMultipart();
            //------设置信件文本内容------
            //新建一个存放信件内容的BodyPart对象
            BodyPart mdp = new MimeBodyPart();
            //给BodyPart对象设置内容和格式/编码方式
            System.out.println(content);
            mdp.setContent(content, "text/html;charset=UTF-8");
            //将含有信件内容的BodyPart加入到MimeMultipart对象中
            mtp.addBodyPart(mdp);
//			//设置信件附件
//			mdp = new MimeBodyPart();
//			FileDataSource fds = new FileDataSource("D:/test.docx");
//			DataHandler dh = new DataHandler(fds);
//			mdp.setFileName("test1.docx");//可以对文件进行重命名
//			mdp.setDataHandler(dh);
//			mtp.addBodyPart(mdp);
            //把mtp作为消息对象的内容
            message.setContent(mtp);
            //先进行存储邮件
            message.saveChanges();


    }

    public void doSend() throws MessagingException{

            Transport transport = mailConnection.getTransport("smtp");
            //邮件服务器名,用户名，密码
            transport.connect(SMTP, senderEmail, senderPassWord);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

    }

}
