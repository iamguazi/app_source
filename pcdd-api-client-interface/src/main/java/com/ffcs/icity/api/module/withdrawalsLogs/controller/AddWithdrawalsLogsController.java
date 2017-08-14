package com.ffcs.icity.api.module.withdrawalsLogs.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.messageLogs.dao.IMessageLogsDao;
import com.ffcs.icity.api.module.messageLogs.entity.MessageLogs;
import com.ffcs.icity.api.module.otherInfo.dao.IOtherInfoDao;
import com.ffcs.icity.api.module.otherInfo.entity.OtherInfo;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.withdrawalsLogs.entity.*;
import com.ffcs.icity.api.module.withdrawalsLogs.dao.*;
import com.ffcs.icity.api.support.Arith;
import com.ffcs.icity.api.util.RequestArgumentAssert;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddWithdrawalsLogsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IWithdrawalsLogsDao withdrawalsLogsDao;
	
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	 
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "fee");
		assertNotEmpty(requestArgument, "withdrawals_password");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String fee=requestArgument.get("fee").toString();
		String withdrawalsPassword=requestArgument.get("withdrawals_password").toString();
		OtherInfo minFee = otherInfoDao.getOtherInfoByKey("tixian_min_fee");
		if(Double.parseDouble(minFee.getOtherValue())>Double.parseDouble(fee)){
			throw new ApiException("提现最低额度"+minFee.getOtherValue());
		}
		
		int count = withdrawalsLogsDao.getWithdrawalsCount(Integer.parseInt(userId));
		OtherInfo freeCountInfo = otherInfoDao.getOtherInfoByKey("tixian_free_count");
		int freeCount=Integer.parseInt(freeCountInfo.getOtherValue());
		double bili=1.0;
		//超过免费提现次数
		if(freeCount<=count){
			OtherInfo tixianBili = otherInfoDao.getOtherInfoByKey("tixian_bili");
			if(tixianBili!=null){
				bili=1-Double.parseDouble(tixianBili.getOtherValue());
			}
		}
		//扣除提现金额
		UserInfo user = userInfoDao.getUserInfoById(Integer.parseInt(userId));
		if(!withdrawalsPassword.equals(user.getWithdrawalsPassword())){
			throw new ApiException("提现密码错误");
		}
		if(user.getPoint()<Double.parseDouble(fee)){
			throw new ApiException("余额不足");
		}
		
		user.setPoint(user.getPoint()-Integer.parseInt(fee));
		userInfoDao.updateUserInfo(user);
		
		
		WithdrawalsLogs withdrawalsLogs=new WithdrawalsLogs();
		withdrawalsLogs.setUserId(Integer.parseInt(userId));
		withdrawalsLogs.setUserType(1);
		withdrawalsLogs.setType(1);
		withdrawalsLogs.setFee(Double.parseDouble(fee));
		
		
		withdrawalsLogs.setBili(bili);
		withdrawalsLogs.setRealFee(Double.parseDouble(fee)*withdrawalsLogs.getBili());
		withdrawalsLogs.setAccount(user.getBankNo());
		withdrawalsLogs.setStatus(0);
		withdrawalsLogs.setSource("3");
 
		withdrawalsLogs.setRealName(user.getRealName());
		
		withdrawalsLogs.setCreateBankName(user.getOpenCardAddress());
		withdrawalsLogs.setBankName(user.getBankName());
		
		withdrawalsLogs.setMobile(user.getMobile());
		
		withdrawalsLogsDao.insertWithdrawalsLogs(withdrawalsLogs);
		return "ok";
	}
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		// TODO Auto-generated method stub
		
		Object client = requestArgument.get("client");
		if(client!=null&&"android".equals(client.toString())){
			return new String[] { requestArgument.get("fee").toString()};
		}else{
			return new String[] { requestArgument.get("withdrawals_password").toString(),requestArgument.get("fee").toString()};
		}
		
	}
}

