package com.ffcs.icity.api.module.userChoiceLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.userChoiceLog.entity.*;
import com.ffcs.icity.api.module.userChoiceLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserChoiceLogListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserChoiceLogDao userChoiceLogDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "game_type");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();
		String userId=requestArgument.get("user_id").toString();
		String gameType=requestArgument.get("game_type").toString();
		Object startTime=requestArgument.get("start_time");
		Object endTime=requestArgument.get("end_time");
		Object roomId=requestArgument.get("room_id");
		UserChoiceLog userChoiceLog=new UserChoiceLog();
		userChoiceLog.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		userChoiceLog.setPageSize(Integer.parseInt(pageSize));
		userChoiceLog.setUserId(Integer.parseInt(userId));
		if(roomId!=null){
			userChoiceLog.setRoomId(Integer.parseInt(roomId.toString()));
		}
		if(!"0".equals(gameType)){
			userChoiceLog.setGameType(Integer.parseInt(gameType));
		}
		if(startTime!=null){
			userChoiceLog.setStartTime(startTime.toString());
		}
		
		if(endTime!=null){
			userChoiceLog.setEndTime(endTime.toString());
		}
		return userChoiceLogDao.findByCondition(userChoiceLog);
	}
}

