package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;
import com.ffcs.icity.api.module.userInfo.entity.*;
import com.ffcs.icity.api.module.userInfo.dao.*;
import com.ffcs.icity.api.module.userLevel.dao.IUserLevelDao;
import com.ffcs.icity.api.module.userLevel.entity.UserLevel;
import com.ffcs.icity.api.module.util.MD5Utils;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserInfoLoginController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	
	@Autowired
	private SMSSenderService senderService;
	
	@Autowired
	private IUserLevelDao userLevelDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "account");
		assertNotEmpty(requestArgument, "password");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String account=requestArgument.get("account").toString();
		String password=requestArgument.get("password").toString();
		Object registrationId=requestArgument.get("registration_id");
		
		

		UserInfo user = userInfoDao. getUserInfoByAccount(account);
		if(user!=null){
			if(!password.equals(user.getPassword())){
				throw new ApiException("用户密码错误");
			}
			if(user.getStatus()==0){
				throw new ApiException("用户被禁用");
			}else if(registrationId!=null&&!registrationId.toString().equals(user.getRegistrationId())){
				user.setRegistrationId(registrationId.toString());
				userInfoDao.updateUserInfo(user);
			}
			user.setLoginTime(new Date());
			//判断等级
			UserLevel level = userLevelDao.getUserLevelByPoint(user.getAllPoint());
			if(level!=null&&level.getId()>user.getLevel()){
				user.setLevel(level.getId());
			}
			userInfoDao.updateUserInfo(user);
			
			
			
			return user;
		}else{
			throw new ApiException("用户不存在");
		}
	}
}

