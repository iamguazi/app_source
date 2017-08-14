package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
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

public class UpdateUserBankInfoController  extends NoValidController {
	
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
		assertNotEmpty(requestArgument, "withdrawals_password");
		assertNotEmpty(requestArgument, "real_name");
		assertNotEmpty(requestArgument, "bank_name");
		assertNotEmpty(requestArgument, "bank_no");
		assertNotEmpty(requestArgument, "open_card_address");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("user_id").toString();
		 
		
		String withdrawalsPassword=requestArgument.get("withdrawals_password").toString();
		Object realName=requestArgument.get("real_name");
		Object bankName=requestArgument.get("bank_name");
		Object bankNo=requestArgument.get("bank_no");
		Object openCardAddress=requestArgument.get("open_card_address");
		
		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(id));
		
		 
		 /**
	     * 提现密码    
	     */
		if(!withdrawalsPassword.equals(userInfo.getWithdrawalsPassword())){
			throw new ApiException("密码错误");
		}

		 /**
	     * 开户姓名    
	     */
		if(realName!=null&&!"".equals(realName.toString())){
			userInfo.setRealName(realName.toString());
		}
		 /**
	     * 银行名称    
	     */
		if(bankName!=null&&!"".equals(bankName.toString())){
			userInfo.setBankName(bankName.toString());
		}
		 /**
	     * 银行卡号    
	     */
		if(bankNo!=null&&!"".equals(bankNo.toString())){
			userInfo.setBankNo(bankNo.toString());
		}
		 /**
	     * 开户地址    
	     */
		if(openCardAddress!=null&&!"".equals(openCardAddress.toString())){
			userInfo.setOpenCardAddress(openCardAddress.toString());
		}
		userInfoDao.updateUserInfo(userInfo);
		return "ok";
	}
	
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		// TODO Auto-generated method stub
		return new String[] { requestArgument.get("withdrawals_password").toString()};
	}
}

