package com.ffcs.icity.api.module.roomUserInfo.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomUserInfo.dao.IRoomUserInfoDao;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddRoomUserInfoMsgController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IRoomUserInfoDao roomUserInfoDao;
	
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private MsgService msgService;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "room_id");
		assertNotEmpty(requestArgument, "user_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String roomId=requestArgument.get("room_id").toString();
		String userId=requestArgument.get("user_id").toString();

		try {
			msgService.sendJoinMsg(roomId, Integer.parseInt(userId));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "ok";
	}
}

