package com.ffcs.icity.api.module.messageLogs.controller;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotBlankString;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.dao.IMessageLogsDao;
import com.ffcs.icity.api.module.messageLogs.entity.MessageLogs;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;

public class SendSMSVerificationController extends NoValidController{

    @Autowired
    private SMSSenderService smsSenderService;
    @Autowired
    private IMessageLogsDao messageLogsDao;
    
    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        
        assertNotBlankString(requestArgument, "phone");
    }

    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {

        final String phone = (String) requestArgument.get("phone");
        
        // 判断验证码不重复
        // 短信通道限制每一天对同一个手机号码只能发送一条一样的数据
        // 每一天对同一个手机号码只能发送10条不一样的数据
        long count = 0;
        String codeTmp;
        MessageLogs m = new MessageLogs();
        m.setPhone(phone);
        
        // 获得当前时间，声明时间变量
        Calendar calendar = Calendar.getInstance(); 
        // 得到年  
        int year = calendar.get(Calendar.YEAR); 
        // 得到月，但是，月份要加上1
        int month = calendar.get(Calendar.MONTH); 
        month = month + 1;
        // 得到天
        int date = calendar.get(Calendar.DATE); 
        
        m.setCreateTimeBegin(year + "-" + month + "-" + date);
        m.setCreateTimeEnd(year + "-" + (month + 1) + "-" + date);

        do {
            codeTmp = this.generalCode(6);
            m.setCode(codeTmp);
            count = messageLogsDao.count(m);
        } while (count != 0);
        
        final String code = codeTmp;
        final String time = "5";
        final JSONObject json = new JSONObject();
        
        
        smsSenderService.sendSMS(phone, null, new String[]{code, time}, new SMSSenderService.SMSCallback() {
            
            @Override
            public void afterSender(Map<String, Object> resultMap) {
                String resultCode = (String) resultMap.get("statusCode");
                MessageLogs message = new MessageLogs();
                message.setPhone(phone);
                message.setCode(code);
                message.setStatus(resultCode);
                json.put("status", resultCode);
                if("000000".equals(resultCode)){
                    message.setSmsMessageSid((String) resultMap.get("smsMessageSid"));
                }else{
                    message.setMessage((String) resultMap.get("statusMsg"));
                }
                messageLogsDao.insertMessageLogs(message);
                json.put("id", message.getId());
            }
        });
        json.put("time", time);
        return json;
    }
    
    /**
     * 生成随机验证码
     * @param length
     * @return
     * @date: 2015-2-9 下午3:34:33
     * @version: 1.00.00
     * @history:
     */
    private String generalCode(int length){
        StringBuffer base = new StringBuffer("0123456789");
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        int range = base.length();
        for (int i = 0; i < length; i++) {
            code.append(base.charAt(random.nextInt(range)));
        }
        return code.toString();
    }
    
}
