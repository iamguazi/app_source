package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;
import com.ffcs.icity.api.module.userInfo.entity.*;
import com.ffcs.icity.api.module.userInfo.dao.*;
import com.ffcs.icity.api.module.util.MD5Utils;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserInfoFindPasswordController  extends NoValidController {
	
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
		assertNotEmpty(requestArgument, "account");
		assertNotEmpty(requestArgument, "mobile");
		assertNotEmpty(requestArgument, "password");
		assertNotEmpty(requestArgument, "msg_id");
		assertNotEmpty(requestArgument, "msg_code");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String account=requestArgument.get("account").toString();
		String password=requestArgument.get("password").toString();
		String mobile=requestArgument.get("mobile").toString();
		String msgId=requestArgument.get("msg_id").toString();
		String msgCode=requestArgument.get("msg_code").toString();
		
		

		UserInfo user = userInfoDao. getUserInfoByAccount(account);
		if(user!=null){
			senderService.VerificationCode(msgId, msgCode, mobile.toString());
			
			if(user.getStatus()==0){
				throw new ApiException("用户被禁用");
			} 
			 
			user.setPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(password))));
			userInfoDao.updateUserInfo(user);
			return user;
		}else{
			throw new ApiException("用户不存在");
		}
	}
}

