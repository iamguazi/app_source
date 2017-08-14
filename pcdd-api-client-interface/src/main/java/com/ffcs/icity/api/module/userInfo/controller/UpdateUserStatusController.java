package com.ffcs.icity.api.module.userInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobMessages;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;
import com.ffcs.icity.api.module.userInfo.entity.*;
import com.ffcs.icity.api.module.userInfo.dao.*;
import com.ffcs.icity.api.module.util.MD5Utils;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UpdateUserStatusController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private SMSSenderService senderService;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "id");
		assertNotEmpty(requestArgument, "status");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String status=requestArgument.get("status").toString();
		 
		if("1".equals(status)){
			ObjectNode vo = new  EasemobMessages().openUserStatus("u_"+id);
			System.out.println(vo.toString());
		}
		
		if("0".equals(status)){
			ObjectNode vo = new  EasemobMessages().closeUserStatus("u_"+id);
			System.out.println(vo.toString());
		}
		 
		UserInfo userInfo = new UserInfo();
		userInfo.setId(Integer.parseInt(id));
		userInfo.setStatus(Integer.parseInt(status));
		 
		userInfoDao.updateUserInfo(userInfo);
		return "ok";
	}
	
	@Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
}

