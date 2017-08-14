package com.ffcs.icity.api.module.userAccountLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.userAccountLog.entity.*;
import com.ffcs.icity.api.module.userAccountLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddUserAccountLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserAccountLogDao userAccountLogDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "account_type");
		assertNotEmpty(requestArgument, "point");
		assertNotEmpty(requestArgument, "user_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		Object account=requestArgument.get("account");
		String accountType=requestArgument.get("account_type").toString();
		Object realName=requestArgument.get("real_name");
		String point=requestArgument.get("point").toString();
		String userId=requestArgument.get("user_id").toString();
		String accountId=requestArgument.get("account_id").toString();
		
		Object bankName=requestArgument.get("bank_name");
		Object addType=requestArgument.get("add_type");
		UserAccountLog userAccountLog=new UserAccountLog();
		if(account!=null){
			userAccountLog.setAccount(account.toString());
		}
		userAccountLog.setAccountId(Integer.parseInt(accountId));
		userAccountLog.setAccountType(Integer.parseInt(accountType));
		if(realName!=null){
			userAccountLog.setRealName(realName.toString());
		}
		if(bankName!=null){
			userAccountLog.setBankName(bankName.toString());
		}
		if(addType!=null){
			userAccountLog.setAddType(addType.toString());
		}
		userAccountLog.setPoint(Double.parseDouble(point));
		userAccountLog.setStatus(0);
		userAccountLog.setUserId(Integer.parseInt(userId));
		
		userAccountLogDao.insertUserAccountLog(userAccountLog);
		return "ok";
	}
	
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		// TODO Auto-generated method stub
		
		return new String[] {requestArgument.get("point").toString()};
	}
}

