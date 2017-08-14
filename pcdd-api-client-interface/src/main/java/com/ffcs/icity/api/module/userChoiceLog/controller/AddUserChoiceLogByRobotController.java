package com.ffcs.icity.api.module.userChoiceLog.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameBili.dao.IGameBiliDao;
import com.ffcs.icity.api.module.gameBili.entity.GameBili;
import com.ffcs.icity.api.module.gameOpenLog.dao.IGameOpenLogDao;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.userChoiceLog.dao.IUserChoiceLogDao;
import com.ffcs.icity.api.module.userChoiceLog.entity.UserChoiceLog;
import com.ffcs.icity.api.module.userChoiceLog.util.OpenTimeUtil;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddUserChoiceLogByRobotController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserChoiceLogDao userChoiceLogDao;
	
	@Autowired
	private IGameBiliDao gameBiliDao;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IPointChangeLogDao pointChangeLogDao;
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private OpenTimeUtil openTimeUtil;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "area_id");
		assertNotEmpty(requestArgument, "room_id");
		assertNotEmpty(requestArgument, "point");
		assertNotEmpty(requestArgument, "bili_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String roomId=requestArgument.get("room_id").toString();
		String areaId=requestArgument.get("area_id").toString();
		String point=requestArgument.get("point").toString();
		String biliId=requestArgument.get("bili_id").toString();
		
		GameOpenLog gameOpenLogQuery=new GameOpenLog();
		//大于3 输入加拿大投注
		if(Integer.parseInt(areaId)>3){
			gameOpenLogQuery.setGameType(2);
		}else{
			gameOpenLogQuery.setGameType(1);
		}
	
		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByLast(gameOpenLogQuery);
		 int isAdd=openTimeUtil.curGameStatus(openLog);
		 if(isAdd==1){
			 if(RandomUtils.nextInt(500)%3==0){
				 isAdd=2;
			 }
		 }
		if(isAdd==1){
			//下注消息
			System.out.println(biliId);
			GameBili gameBili = gameBiliDao.getGameBiliById(Integer.parseInt(biliId));
			UserChoiceLog userChoiceLog=new UserChoiceLog();
			userChoiceLog.setUserId(Integer.parseInt(userId));
			userChoiceLog.setRoomId(Integer.parseInt(roomId));
			userChoiceLog.setAreaId(Integer.parseInt(areaId));
			userChoiceLog.setChoiceNo((Long.parseLong(openLog.getGameNum())+1)+"");
			userChoiceLog.setChoiceResult(gameBili.getResult());
			userChoiceLog.setBili(gameBili.getBili());
			userChoiceLog.setPoint(Double.parseDouble(point));
			userChoiceLog.setGameType(gameBili.getGameType());
			userChoiceLog.setBiliId(Integer.parseInt(biliId));
			userChoiceLog.setChoiceName(gameBili.getBiliName());
//			userChoiceLogDao.insertUserChoiceLog(userChoiceLog);
//			
			
			try {
				msgService.sendAddPointMsg(roomId, Integer.parseInt(userId), userChoiceLog);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			//加入房间消息
			if(isAdd==2){
				try {
					if(RandomUtils.nextInt(500)%10==0){
						msgService.sendJoinMsg(roomId, Integer.parseInt(userId));
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		
		
		
		return "ok";
	}
	
	 
	public static void main(String[] args) {
		GameOpenLog openLog=new GameOpenLog();
		openLog.setGameType(2);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			openLog.setOpenTime(sdf.parse("2017-02-23 23:51"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 @Override
	    public String[] getSignItems(Map<String, Object> requestArgument) {
	    	// TODO Auto-generated method stub
	    	return new String[] {"no_valid"};
	    }
}

