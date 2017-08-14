package com.ffcs.icity.api.module.messageLogs.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotBlankString;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.entity.*;
import com.ffcs.icity.api.module.messageLogs.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class MessageLogsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IMessageLogsDao messageLogsDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		 assertNotBlankString(requestArgument, "id");
		 assertNotBlankString(requestArgument, "phone");
		 assertNotBlankString(requestArgument, "code");
		 assertNotBlankString(requestArgument, "create_time");
		 assertNotBlankString(requestArgument, "status");
		 assertNotBlankString(requestArgument, "sms_message_sid");
		 assertNotBlankString(requestArgument, "message");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String phone=requestArgument.get("phone").toString();
		String code=requestArgument.get("code").toString();
		String createTime=requestArgument.get("create_time").toString();
		String status=requestArgument.get("status").toString();
		String smsMessageSid=requestArgument.get("sms_message_sid").toString();
		String message=requestArgument.get("message").toString();
		
		

		return messageLogsDao.findByCondition(null);
	}
}

