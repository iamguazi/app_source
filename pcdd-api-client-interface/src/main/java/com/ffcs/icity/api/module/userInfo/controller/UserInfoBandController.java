package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.entity.MessageLogs;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;
import com.ffcs.icity.api.module.userInfo.entity.*;
import com.ffcs.icity.api.module.userInfo.dao.*;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserInfoBandController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private SMSSenderService senderService;
	
	
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "mobile");
		assertNotEmpty(requestArgument, "msg_id");
		assertNotEmpty(requestArgument, "msg_code");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String mobile=requestArgument.get("mobile").toString();
		String msgId=requestArgument.get("msg_id").toString();
		String msgCode=requestArgument.get("msg_code").toString();
		
		//验证码验证
		UserInfo user = userInfoDao. getUserInfoById(Integer.parseInt(userId));
		senderService.VerificationCode(msgId, msgCode, mobile);
		int count = userInfoDao.getUserInfoByParams(mobile);
		if(count>0){
			throw new ApiException("手机号已经绑定其他帐号");
		}
		user.setMobile(mobile);
		userInfoDao.updateUserInfo(user);
		
		return "ok";
	}
	
	
}

