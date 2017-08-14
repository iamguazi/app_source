package com.ffcs.icity.api.module.messageLogs.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.module.messageLogs.dao.IMessageLogsDao;
import com.ffcs.icity.api.module.messageLogs.entity.MessageLogs;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SMSSenderService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${SMSIsProd:1}")
    private String SMSIsProd;       // 是否生产环境
    @Value("${SMSAppId}")
    private String SMSAppId;        // app Id
    @Value("${SMSModuleId}")
    private String SMSModuleId;     // 短信模板ID
    @Value("${SMSAccountSid:8a48b5514b35422d014b36742f0c00b3}")
    private String SMSAccountSid;   // 账户SID
    @Value("${SMSAuthToken:729f9bd1d95e487085e42a18b8bc1ee9}")
    private String SMSAuthToken;   // 账户令牌
    
    @Autowired
    private IMessageLogsDao messageLogDao;

    public Map<String, Object> sendSMS(String phones, String SMSModuleId, String[] contentArgs){
        if(null == SMSModuleId || StringUtils.isBlank(SMSModuleId)){
            SMSModuleId = this.SMSModuleId;
        }
        return this.senderSMS(phones, SMSModuleId, contentArgs);
    }
    
    public Map<String, Object> sendSMS(String phones, String SMSModuleId, String[] contentArgs, SMSCallback callback){
        if(null == SMSModuleId || StringUtils.isBlank(SMSModuleId)){
            SMSModuleId = this.SMSModuleId;
        }
        Map<String, Object> result = this.senderSMS(phones, SMSModuleId, contentArgs);
        if(null != callback){
            callback.afterSender(result);
        }
        return result;
    }
    
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> senderSMS(String phones, String SMSModuleId, String[] contentArgs) {
        HashMap<String, Object> result = null;

        logger.debug("phones = " + phones);
        logger.debug("SMSModuleId = " + SMSModuleId);
        logger.debug("contentArgs = {}", ToStringBuilder.reflectionToString(contentArgs, ToStringBuilder.getDefaultStyle()));
        
        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        
        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        if("1".equals(SMSIsProd)){
            restAPI.init("app.cloopen.com", "8883");
        }else{
            restAPI.init("sandboxapp.cloopen.com", "8883");
        }
        
        
        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount(SMSAccountSid, SMSAuthToken);
        
        
        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId(SMSAppId);
        
        
        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。                                                                                                                             *
        //**************************************************************************************************
        
        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});                                                                         *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
        result = restAPI.sendTemplateSMS(phones, SMSModuleId, contentArgs);
        
        logger.debug("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                logger.debug(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            logger.error("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        return result;
    }
    
    public void VerificationCode(String msgId,String msgCode,String userPhone) throws ApiException{
    	MessageLogs message = messageLogDao.getMessageLogsById(Integer.parseInt(msgId));
    	if(message==null){
			throw new ApiException("验证码不存在");
		}
		
		if("-1".equals(message.getStatus())){
			throw new ApiException("验证码已使用");
		}
		
		if(!userPhone.equals(message.getPhone())){
			throw new ApiException("验证手机号码不匹配");
		}
		
		if(!msgCode.equals(message.getCode())){
			throw new ApiException("验证码不正确");
		}
		message.setStatus("-1");
		messageLogDao.updateMessageLogs(message);
    }
    public static abstract class SMSCallback{
        
        public abstract void afterSender(Map<String, Object> resultMap);
    }
}
