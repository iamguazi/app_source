package com.ffcs.icity.api.module.withdrawalsLogs.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.withdrawalsLogs.entity.*;
import com.ffcs.icity.api.module.withdrawalsLogs.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class WithdrawalsLogsListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IWithdrawalsLogsDao withdrawalsLogsDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		
		String userId=requestArgument.get("user_id").toString();
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();

		WithdrawalsLogs withdrawalsLogs=new WithdrawalsLogs();
		
		withdrawalsLogs.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		withdrawalsLogs.setPageSize(Integer.parseInt(pageSize));
		withdrawalsLogs.setUserId(Integer.parseInt(userId));
		
		Map<String, Object>result=new HashMap<String, Object>();
		Map<String, Object>params=new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userType", 1);
		result.put("list", withdrawalsLogsDao.findByCondition(withdrawalsLogs));
		result.put("all_fee", withdrawalsLogsDao.getFee(params));
		return result;
	}
}

