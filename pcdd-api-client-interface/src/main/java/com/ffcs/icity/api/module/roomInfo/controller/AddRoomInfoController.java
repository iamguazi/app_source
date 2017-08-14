package com.ffcs.icity.api.module.roomInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobChatGroups;
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

public class AddRoomInfoController  extends NoValidController {
	
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
		String roomName="";
//		String roomPhoto=requestArgument.get("room_photo").toString();
		 
		for(int i=1;i<=4;i++){
			roomName="vip房间"+i;
			ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
			dataObjectNode.put("groupname",roomName);
			dataObjectNode.put("desc", roomName);
			dataObjectNode.put("approval", true);
			dataObjectNode.put("public", true);
			dataObjectNode.put("maxusers", 600);
			dataObjectNode.put("owner", "admin");
			ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
			dataObjectNode.put("members", arrayNode);
			ObjectNode creatChatGroupNode =new EasemobChatGroups(). creatChatGroups(dataObjectNode);
			System.out.println(creatChatGroupNode.toString());
			RoomInfo roomInfo=new RoomInfo();
			roomInfo.setImGourpId(creatChatGroupNode.get("data").get("groupid").asText());
			roomInfo.setGameType(Integer.parseInt(gameType));
			roomInfo.setAreaId(Integer.parseInt(areaId));
			roomInfo.setRoomName(roomName);
//			roomInfo.setRoomPhoto(roomPhoto);
			roomInfo.setRebotMaxCount(5);
			roomInfo.setRebotMinCount(1);
			roomInfo.setPeopleMaxCount(600);
			roomInfo.setPerMaxPoint(10000.0);
			roomInfo.setPerMinPoint(10.0);
			roomInfo.setAllMaxPoint(10000000.0);
			roomInfo.setStatus(1);
			
			roomInfoDao.insertRoomInfo(roomInfo);
		}
		return "ok";
	}
	
	 
    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
}

