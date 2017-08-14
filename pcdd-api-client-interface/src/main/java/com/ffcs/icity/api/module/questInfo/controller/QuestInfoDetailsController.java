package com.ffcs.icity.api.module.questInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.otherInfo.dao.IOtherInfoDao;
import com.ffcs.icity.api.module.otherInfo.entity.OtherInfo;
import com.ffcs.icity.api.module.questInfo.entity.*;
import com.ffcs.icity.api.module.questInfo.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class QuestInfoDetailsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IQuestInfoDao questInfoDao;
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "num");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {

		String num=requestArgument.get("num").toString();
		 
		 QuestInfo vo = questInfoDao.getQuestInfoById(Integer.parseInt(num));
		 if(vo==null){
			 throw new ApiException("请联系客服");
		 }
		return questInfoDao.getQuestInfoById(Integer.parseInt(num));
	}
	
	@Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
}

