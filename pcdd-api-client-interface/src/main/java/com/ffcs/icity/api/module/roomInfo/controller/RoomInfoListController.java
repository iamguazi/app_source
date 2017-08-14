package com.ffcs.icity.api.module.roomInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.roomInfo.entity.*;
import com.ffcs.icity.api.module.roomInfo.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class RoomInfoListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "game_type");
		assertNotEmpty(requestArgument, "area_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameType=requestArgument.get("game_type").toString();
		String areaId=requestArgument.get("area_id").toString();

		RoomInfo roomInfo=new RoomInfo();
		roomInfo.setGameType(Integer.parseInt(gameType));
		roomInfo.setAreaId(Integer.parseInt(areaId));
		roomInfo.setStatus(1);
		List<RoomInfo> list = roomInfoDao.findByCondition(roomInfo);
		for(RoomInfo room:list){
			if(!"-1".equals(room.getPassword().toString())){
				room.setPassword("0");
			}
		}
		return list;
	}
}

