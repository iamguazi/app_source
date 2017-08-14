package com.ffcs.icity.api.module.shareBili.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.otherInfo.dao.IOtherInfoDao;
import com.ffcs.icity.api.module.otherInfo.entity.OtherInfo;
import com.ffcs.icity.api.module.shareBili.entity.*;
import com.ffcs.icity.api.module.shareBili.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class ShareBiliListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IShareBiliDao shareBiliDao;
	
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {

		ShareBili shareBili=new ShareBili();
		
		OtherInfo choiceNum = otherInfoDao.getOtherInfoByKey("choice_num");
		Map<String, Object>result=new HashMap<String, Object>();
		result.put("num", choiceNum.getOtherValue());
		result.put("share_url", otherInfoDao.getOtherInfoByKey("share_url").getOtherValue());
		result.put("bili_list", shareBiliDao.findByCondition(shareBili));
		
		return result;
	}
}

