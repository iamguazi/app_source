package com.ffcs.icity.api.module.noticeInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.noticeInfo.entity.*;
import com.ffcs.icity.api.module.noticeInfo.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class NoticeInfoListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private INoticeInfoDao noticeInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "type");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();
		String userId=requestArgument.get("user_id").toString();
		String type=requestArgument.get("type").toString();

		NoticeInfo noticeInfo=new NoticeInfo();
		noticeInfo.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		noticeInfo.setPageSize(Integer.parseInt(pageSize));
		noticeInfo.setUserId(Integer.parseInt(userId));
		
		if("1".equals(type)){
			noticeInfo.setNoticeType(1);
			return noticeInfoDao.findByCondition(noticeInfo);
		}else{
			return noticeInfoDao.getMyList(noticeInfo);
		}
	}
}

