package com.ffcs.icity.api.module.roomUserInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobChatGroups;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.roomUserInfo.entity.*;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.roomUserInfo.dao.*;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddRoomUserInfoByRobotController  extends NoValidController {
	
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
		
		RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
		RoomUserInfo roomUserInfo=new RoomUserInfo();
		roomUserInfo.setAreaId(roomInfo.getAreaId());
		roomUserInfo.setRoomId(Integer.parseInt(roomId));
		roomUserInfo.setUserId(Integer.parseInt(userId));
		roomUserInfo.setUserPhoto(userInfo.getUserPhoto());
		roomUserInfo.setUserName(userInfo.getNickName());
		roomUserInfo.setMobile(userInfo.getMobile());
		/**
		 * 在群组中添加一个人
		 * curl示例
		 * curl -X POST 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojianguo002' -H 'Authorization: Bearer {token}'
		 */
		String addToChatgroupid = roomInfo.getImGourpId();
		String toAddUsername = userInfo.getImAccount();
		ObjectNode addUserToGroupNode =new EasemobChatGroups().addUserToGroup(addToChatgroupid, toAddUsername);
		System.out.println(addUserToGroupNode.toString());
		RoomUserInfo roomUser = roomUserInfoDao.getRoomUserInfoByParams(roomUserInfo);
		if(roomUser==null){
			roomUserInfoDao.insertRoomUserInfo(roomUserInfo);
		} 
		try {
			msgService.sendJoinMsg(roomId, Integer.parseInt(userId));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "ok";
	}
}

