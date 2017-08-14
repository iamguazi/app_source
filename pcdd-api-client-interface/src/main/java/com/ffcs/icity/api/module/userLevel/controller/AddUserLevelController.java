package com.ffcs.icity.api.module.userLevel.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.userLevel.entity.*;
import com.ffcs.icity.api.module.userLevel.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddUserLevelController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserLevelDao userLevelDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "id");
		assertNotEmpty(requestArgument, "level_name");
		assertNotEmpty(requestArgument, "recharge_fee");
		assertNotEmpty(requestArgument, "create_time");
		assertNotEmpty(requestArgument, "level_icon");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String levelName=requestArgument.get("level_name").toString();
		String rechargeFee=requestArgument.get("recharge_fee").toString();
		String createTime=requestArgument.get("create_time").toString();
		String levelIcon=requestArgument.get("level_icon").toString();
		
		UserLevel userLevel=new UserLevel();
		userLevel.setId(Integer.parseInt(id));
		userLevel.setLevelName(levelName);
		userLevel.setRechargeFee(Double.parseDouble(rechargeFee));
		userLevel.setLevelIcon(levelIcon);
		
		userLevelDao.insertUserLevel(userLevel);
		return "ok";
	}
}

