package com.ffcs.icity.api.module.util;

import javax.mail.MessagingException;

public class EmailUtils {
    private static String senderEmail = "kjy@xiaozhilin.com";
    private static String senderPassWord = "Kjy123456";
    private static String sMTP = "smtp.mxhichina.com";
    public static Boolean sendEmail(String receiverEmail,String subject,String content){
        //添加成功，发生验证邮件
        try {

        String receiverList = receiverEmail;
        //content = "";
        SimpleSMTPSender simpleSMTPSender = new SimpleSMTPSender(senderEmail, senderPassWord, sMTP);
        simpleSMTPSender.setProperties(receiverList);
        simpleSMTPSender.setMessage(subject, content);
        simpleSMTPSender.doSend();
        System.out.println("success send");
        return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
    	EmailUtils.sendEmail("461542638@qq.com", "密码更改", "您的密码重置为");
	}
}
