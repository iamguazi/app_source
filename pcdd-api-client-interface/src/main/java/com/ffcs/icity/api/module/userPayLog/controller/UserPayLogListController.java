package com.ffcs.icity.api.module.userPayLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.userPayLog.entity.*;
import com.ffcs.icity.api.module.userPayLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserPayLogListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserPayLogDao userPayLogDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
		assertNotEmpty(requestArgument, "type");
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
		String type=requestArgument.get("type").toString();
		
		UserPayLog userPayLog=new UserPayLog();
		userPayLog.setUserId(Integer.parseInt(userId));
		//1 付定金 2充值 3付全款
		userPayLog.setOrderType(Integer.parseInt(type));
		userPayLog.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		userPayLog.setPageSize(Integer.parseInt(pageSize));
		
		userPayLog.setStatus(1);
		return userPayLogDao.findByCondition(userPayLog);
	}
}

