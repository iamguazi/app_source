package com.ffcs.icity.api.module.gameOpenLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameOpenLog.entity.*;
import com.ffcs.icity.api.module.gameOpenLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UpdateGameOpenLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "gameOpenLog_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String userId=requestArgument.get("user_id").toString();
		String gameType=requestArgument.get("game_type").toString();
		String openTime=requestArgument.get("open_time").toString();
		String gameNum=requestArgument.get("game_num").toString();
		String gameResult=requestArgument.get("game_result").toString();
		String gameResultDesc=requestArgument.get("game_result_desc").toString();
		String resultType=requestArgument.get("result_type").toString();
		String isBaozi=requestArgument.get("is_baozi").toString();
		
		

		return gameOpenLogDao.findByCondition(null);
	}
}

