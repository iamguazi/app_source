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

public class CheckWithdrawalsLogsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IWithdrawalsLogsDao withdrawalsLogsDao;
	
	@Autowired
	private IMessageLogsDao messageLogDao;
	
	
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
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String fee=requestArgument.get("fee").toString();
		OtherInfo other = otherInfoDao.getOtherInfoByKey("bili");
		
		Map<String, Object>result=new HashMap<String, Object>();
		result.put("fee", fee);
		result.put("real_fee", Arith.mul(Double.parseDouble(other.getOtherValue()), Double.parseDouble(fee)));
		return result;
	}
	
}

