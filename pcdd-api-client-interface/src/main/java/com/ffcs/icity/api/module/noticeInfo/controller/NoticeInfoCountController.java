package com.ffcs.icity.api.module.noticeInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.noticeInfo.entity.*;
import com.ffcs.icity.api.module.noticeInfo.dao.*;
import com.ffcs.icity.api.module.userAndNotice.dao.IUserAndNoticeDao;
import com.ffcs.icity.api.module.userAndNotice.entity.UserAndNotice;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class NoticeInfoCountController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private INoticeInfoDao noticeInfoDao;
	
	@Autowired
	private IUserAndNoticeDao userAndNoticeDao;
	
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		
		
		Map<String, Object>result=new HashMap<String, Object>();
		int systemCount=noticeInfoDao.getUnreadCount(Integer.parseInt(userId));
		int myCount=noticeInfoDao.getUnreadMyCount(Integer.parseInt(userId));
		
		result.put("system_notice_count", systemCount);
		result.put("my_notice_count", myCount);
		result.put("notice_count", systemCount+myCount);
		return result;
	}
}

