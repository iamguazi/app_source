package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
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

public class AddUserInfoController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "id");
		assertNotEmpty(requestArgument, "account");
		assertNotEmpty(requestArgument, "password");
		assertNotEmpty(requestArgument, "user_photo");
		assertNotEmpty(requestArgument, "nick_name");
		assertNotEmpty(requestArgument, "sex");
		assertNotEmpty(requestArgument, "mobile");
		assertNotEmpty(requestArgument, "point");
		assertNotEmpty(requestArgument, "create_time");
		assertNotEmpty(requestArgument, "band_id");
		assertNotEmpty(requestArgument, "band_type");
		assertNotEmpty(requestArgument, "band_mobile");
		assertNotEmpty(requestArgument, "registration_id");
		assertNotEmpty(requestArgument, "code");
		assertNotEmpty(requestArgument, "status");
		assertNotEmpty(requestArgument, "im_account");
		assertNotEmpty(requestArgument, "level");
		assertNotEmpty(requestArgument, "user_type");
		assertNotEmpty(requestArgument, "login_time");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String account=requestArgument.get("account").toString();
		String password=requestArgument.get("password").toString();
		String userPhoto=requestArgument.get("user_photo").toString();
		String nickName=requestArgument.get("nick_name").toString();
		String sex=requestArgument.get("sex").toString();
		String mobile=requestArgument.get("mobile").toString();
		String point=requestArgument.get("point").toString();
		String createTime=requestArgument.get("create_time").toString();
		String bandId=requestArgument.get("band_id").toString();
		String bandType=requestArgument.get("band_type").toString();
		String bandMobile=requestArgument.get("band_mobile").toString();
		String registrationId=requestArgument.get("registration_id").toString();
		String code=requestArgument.get("code").toString();
		String status=requestArgument.get("status").toString();
		String imAccount=requestArgument.get("im_account").toString();
		String level=requestArgument.get("level").toString();
		String userType=requestArgument.get("user_type").toString();
		String loginTime=requestArgument.get("login_time").toString();
		
		UserInfo userInfo=new UserInfo();
		userInfo.setId(Integer.parseInt(id));
		userInfo.setAccount(account);
		userInfo.setPassword(password);
		userInfo.setUserPhoto(userPhoto);
		userInfo.setNickName(nickName);
		userInfo.setSex(Integer.parseInt(sex));
		userInfo.setMobile(mobile);
		userInfo.setPoint(Double.parseDouble(point));
		userInfo.setBandId(bandId);
		userInfo.setBandType(Integer.parseInt(bandType));
		userInfo.setBandMobile(bandMobile);
		userInfo.setRegistrationId(registrationId);
		userInfo.setCode(code);
		userInfo.setStatus(Integer.parseInt(status));
		userInfo.setImAccount(imAccount);
		userInfo.setLevel(Integer.parseInt(level));
		userInfo.setUserType(Integer.parseInt(userType));
		
		userInfoDao.insertUserInfo(userInfo);
		return "ok";
	}
}

