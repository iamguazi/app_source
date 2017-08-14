package com.ffcs.icity.api.module.gameOpenLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameOpenLog.entity.*;
import com.ffcs.icity.api.module.gameOpenLog.Service.OpenService;
import com.ffcs.icity.api.module.gameOpenLog.dao.*;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class OpenLogNumController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private OpenService openService;
	
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "game_num");
		assertNotEmpty(requestArgument, "game_type");
		assertNotEmpty(requestArgument, "is_close");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameNum=requestArgument.get("game_num").toString();
		String gameType=requestArgument.get("game_type").toString();
		String isClose=requestArgument.get("is_close").toString();
		GameOpenLog gameOpenLog=new GameOpenLog();
		gameOpenLog.setGameNum(gameNum);
		gameOpenLog.setGameType(Integer.parseInt(gameType));
//		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByNum(gameOpenLog);
		if("1".equals(isClose)){
			msgService.closeMsg(gameNum,Integer.parseInt(gameType));
			//开奖上期
			openService.openJiang((Long.parseLong(gameNum)-1)+"",gameType);
		}else if("0".equals(isClose)){
			msgService.openMsg(gameNum,Integer.parseInt(gameType));
		}else{
			msgService.daojishiMsg(gameNum,Integer.parseInt(gameType));
		}
		return "ok";
	}
	
	 @Override
	    public String[] getSignItems(Map<String, Object> requestArgument) {
	    	// TODO Auto-generated method stub
	    	return new String[] {"no_valid"};
	    }
}

