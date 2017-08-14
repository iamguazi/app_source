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

public class OpenLogByBlackController  extends NoValidController {
	
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
		assertNotEmpty(requestArgument, "open_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameId=requestArgument.get("open_id").toString();
		GameOpenLog gameOpenLog = gameOpenLogDao.getGameOpenLogById(Integer.parseInt(gameId));
		String resultType ="";
		if(gameOpenLog.getIsBaozi()==null){
			if(gameOpenLog.getGameResult()>13){
				 resultType="大";
			}else{
				resultType="小";
			}
			if(gameOpenLog.getGameResult()%2==0){
				resultType=resultType+",双";
			}else{
				resultType=resultType+",单";
			}
			gameOpenLog.setResultType(resultType);
			//三个数字相同 为豹子
			try {
				String result=gameOpenLog.getGameResultDesc().split("=")[0];
				int sum1=Integer.parseInt(result.split("+")[0]);
				int sum2=Integer.parseInt(result.split("+")[1]);
				int sum3=Integer.parseInt(result.split("+")[2]);
				
				if(sum1==sum2&&sum2==sum3){
					
					gameOpenLog.setIsBaozi(1);
				}else{
					gameOpenLog.setIsBaozi(0);
				}
			} catch (Exception e) {
				// TODO: handle exception
				gameOpenLog.setIsBaozi(0);
			}
			gameOpenLog.setGivePoint(0.0);
			gameOpenLog.setColor(AddGameOpenLogController.getColor(gameOpenLog.getGameResult())+"");
			
			gameOpenLogDao.updateGameOpenLog(gameOpenLog);
			
		}
		
		openService.openJiang(gameOpenLog.getGameNum(),gameOpenLog.getGameType()+"");
		return "ok";
	}
	
	 @Override
	    public String[] getSignItems(Map<String, Object> requestArgument) {
	    	// TODO Auto-generated method stub
	    	return new String[] {"no_valid"};
	    }
}

