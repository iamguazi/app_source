package com.ffcs.icity.api.module.otherInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.otherInfo.entity.*;
import com.ffcs.icity.api.module.otherInfo.dao.*;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.withdrawalsLogs.dao.IWithdrawalsLogsDao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class OtherInfoListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IWithdrawalsLogsDao withdrawalsLogsDao;
	
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
		String userId=requestArgument.get("user_id").toString();
		
		List<OtherInfo> list = otherInfoDao.findByCondition(null);
		Map<String, Object>result=new HashMap<String, Object>();
		OtherInfo other=null;
		for(OtherInfo info:list){
			if("tixian_free_count".equals(info.getOtherValue())){
				other=info;
			}
			if(info.getOtherKey().contains("tixian")){
				result.put(info.getOtherKey(), info.getOtherValue());
			}
		}
		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
		result.put("point", userInfo.getPoint());
		
		int count = withdrawalsLogsDao.getWithdrawalsCount(userInfo.getId());
		int freeCount=3;
		if(other!=null){
			freeCount= Integer.parseInt(other.getOtherValue()) ;
		}
		if(count>freeCount){
			result.put("free_count", 0);
		}else{
			result.put("free_count", freeCount-count);
		}
		
		return result;
	}
}

