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

public class UpdateUserInfoController  extends NoValidController {
	
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
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("user_id").toString();
		Object userPhoto=requestArgument.get("user_photo") ;
		Object nickName=requestArgument.get("nick_name") ;
		Object sex=requestArgument.get("sex") ;
		Object mobile=requestArgument.get("mobile") ;
		Object personalSign=requestArgument.get("personal_sign") ;
		
		Object withdrawalsPassword=requestArgument.get("withdrawals_password");
		Object password=requestArgument.get("password");
		 
		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(id));
		
		if(userPhoto!=null){
			userInfo.setUserPhoto(userPhoto.toString());
		}

		if(nickName!=null){
			userInfo.setNickName(nickName.toString());
		}
		if(sex!=null){
			userInfo.setSex(Integer.parseInt(sex.toString()));
		}
		
		if(personalSign!=null){
			userInfo.setPersonalSign(personalSign.toString());
		}
		if(mobile!=null){
			assertNotEmpty(requestArgument, "msg_id");
			assertNotEmpty(requestArgument, "msg_code");
			
			String msgId=requestArgument.get("msg_id").toString();
			String msgCode=requestArgument.get("msg_code").toString();
			
			senderService.VerificationCode(msgId, msgCode, mobile.toString());
			userInfo.setMobile(mobile.toString());
		}
		if(password!=null&&!"".equals(password.toString())){
			assertNotEmpty(requestArgument, "old_password");
			String oldPassword=requestArgument.get("old_password").toString();
			if(oldPassword.equals(userInfo.getPassword())){
				userInfo.setPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(password.toString()))));
			}else{
				throw new ApiException("原密码错误");
			}
		}
		
		
		 /**
	     * 提现密码    
	     */
		if(withdrawalsPassword!=null&&!"".equals(withdrawalsPassword.toString())){
			if(userInfo.getWithdrawalsPassword()==null){
				userInfo.setWithdrawalsPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(withdrawalsPassword.toString()))));
			}else{
				assertNotEmpty(requestArgument, "old_password");
				String oldPassword=requestArgument.get("old_password").toString();
				if(oldPassword.equals(userInfo.getWithdrawalsPassword())){
					userInfo.setWithdrawalsPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(withdrawalsPassword.toString()))));
				}else{
					throw new ApiException("原密码错误");
				}
			}
		}
		 
		userInfoDao.updateUserInfo(userInfo);
		return "ok";
	}
}

