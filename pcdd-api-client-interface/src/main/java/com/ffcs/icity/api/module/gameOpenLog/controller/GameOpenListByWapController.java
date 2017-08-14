package com.ffcs.icity.api.module.gameOpenLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameOpenLog.entity.*;
import com.ffcs.icity.api.module.gameOpenLog.dao.*;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.userChoiceLog.controller.AddUserChoiceLogByRobotController;
import com.ffcs.icity.api.module.userChoiceLog.util.OpenTimeUtil;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.util.DateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class GameOpenListByWapController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Autowired
	private OpenTimeUtil openTimeUtil;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "game_type");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameType=requestArgument.get("game_type").toString();
		
		GameOpenLog gameOpenLog=new GameOpenLog();
		gameOpenLog.setGameType(Integer.parseInt(gameType));
		gameOpenLog.setStart(0);
		gameOpenLog.setPageSize(30);
		
		Map<String, Object>result=new HashMap<String, Object>();
		List<GameOpenLog> list = gameOpenLogDao.findByCondition(gameOpenLog);
		result.put("open_time", list);
		
		 
		return result;
	}
	
	@Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
	 
}

