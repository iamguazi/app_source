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

public class RoomUserInfoListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IRoomUserInfoDao roomUserInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();

		RoomUserInfo roomUserInfo=new RoomUserInfo();
		roomUserInfo.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		roomUserInfo.setPageSize(Integer.parseInt(pageSize));
		return roomUserInfoDao.findByCondition(roomUserInfo);
	}
}

