package com.ffcs.icity.api.module.roomUserInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobChatGroups;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobMessages;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.roomUserInfo.entity.*;
import com.ffcs.icity.api.module.roomUserInfo.dao.*;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.util.JSONHelper;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class SendRoomMsgController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IRoomUserInfoDao roomUserInfoDao;
	
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "room_id");
		assertNotEmpty(requestArgument, "msg_content");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String roomId=requestArgument.get("room_id").toString();
		String msgContent=requestArgument.get("msg_content").toString();
		String noticeType=requestArgument.get("noticeType").toString();
		
		MsgVo vo=new MsgVo();
		vo.setNoticeType(Integer.parseInt(noticeType));
		vo.setExtContent(msgContent);
		
		String from=requestArgument.get("from").toString();
		 ObjectNode txtmsg = factory.objectNode();
	        try {
				txtmsg.put("msg", JSONHelper.toJSONString(vo, true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        txtmsg.put("type","txt");
		RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
		 ObjectNode ext = factory.objectNode();
		 // 给一个群组发文本消息
        String targetTypegr = "chatgroups";
        ArrayNode targetgroup = factory.arrayNode();
        targetgroup.add(roomInfo.getImGourpId());
        ObjectNode sendTxtMessagegroupnode =EasemobMessages  . sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);
        if (null != sendTxtMessagegroupnode) {
           System.out.println("给一个群组发文本消息: " + sendTxtMessagegroupnode.toString());
        } 
		
		return "ok";
	}
}

