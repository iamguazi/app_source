package com.ffcs.icity.api.module.roomUserInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.roomUserInfo.entity.*;
import com.ffcs.icity.api.module.roomUserInfo.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UpdateRoomUserInfoController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IRoomUserInfoDao roomUserInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "roomUserInfo_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		Object id=requestArgument.get("id");
		Object areaId=requestArgument.get("area_id");
		Object roomId=requestArgument.get("room_id");
		Object userPhoto=requestArgument.get("user_photo");
		Object userId=requestArgument.get("user_id");
		Object userName=requestArgument.get("user_name");
		Object mobile=requestArgument.get("mobile");
		
		RoomUserInfo roomUserInfo=roomUserInfoDao. getRoomUserInfoById(Integer.parseInt(id.toString()));
		 /**
	     * id    
	     */
		if(id!=null&&!"".equals(id.toString())){
			roomUserInfo.setId(Integer.parseInt(id.toString()));
		}
		 /**
	     * 游戏区域id    
	     */
		if(areaId!=null&&!"".equals(areaId.toString())){
			roomUserInfo.setAreaId(Integer.parseInt(areaId.toString()));
		}
		 /**
	     * 房间id    
	     */
		if(roomId!=null&&!"".equals(roomId.toString())){
			roomUserInfo.setRoomId(Integer.parseInt(roomId.toString()));
		}
		 /**
	     * userPhoto    
	     */
		if(userPhoto!=null&&!"".equals(userPhoto.toString())){
			roomUserInfo.setUserPhoto(userPhoto.toString());
		}
		 /**
	     * userId    
	     */
		if(userId!=null&&!"".equals(userId.toString())){
			roomUserInfo.setUserId(Integer.parseInt(userId.toString()));
		}
		 /**
	     * userName    
	     */
		if(userName!=null&&!"".equals(userName.toString())){
			roomUserInfo.setUserName(userName.toString());
		}
		 /**
	     * mobile    
	     */
		if(mobile!=null&&!"".equals(mobile.toString())){
			roomUserInfo.setMobile(mobile.toString());
		}
		roomUserInfoDao.updateRoomUserInfo(roomUserInfo);
		return "ok";
	}
}

