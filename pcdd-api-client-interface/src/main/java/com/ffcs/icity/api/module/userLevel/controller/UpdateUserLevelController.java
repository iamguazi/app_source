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

public class UpdateUserLevelController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserLevelDao userLevelDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "userLevel_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		Object id=requestArgument.get("id");
		Object levelName=requestArgument.get("level_name");
		Object rechargeFee=requestArgument.get("recharge_fee");
		Object createTime=requestArgument.get("create_time");
		Object levelIcon=requestArgument.get("level_icon");
		
		UserLevel userLevel=userLevelDao. getUserLevelById(Integer.parseInt(id.toString()));
		 /**
	     * id    
	     */
		if(id!=null&&!"".equals(id.toString())){
			userLevel.setId(Integer.parseInt(id.toString()));
		}
		 /**
	     * levelName    
	     */
		if(levelName!=null&&!"".equals(levelName.toString())){
			userLevel.setLevelName(levelName.toString());
		}
		 /**
	     * rechargeFee    
	     */
		if(rechargeFee!=null&&!"".equals(rechargeFee.toString())){
			userLevel.setRechargeFee(Double.parseDouble(rechargeFee.toString()));
		}
		 /**
	     * createTime    
	     */
		if(createTime!=null&&!"".equals(createTime.toString())){
		}
		 /**
	     * 等级图标    
	     */
		if(levelIcon!=null&&!"".equals(levelIcon.toString())){
			userLevel.setLevelIcon(levelIcon.toString());
		}
		userLevelDao.updateUserLevel(userLevel);
		return "ok";
	}
}

