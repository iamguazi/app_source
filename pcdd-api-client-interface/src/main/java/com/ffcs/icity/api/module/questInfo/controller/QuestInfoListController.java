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

public class QuestInfoListController  extends NoValidController {
	
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
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {

		QuestInfo questInfo=new QuestInfo();
		
		List<OtherInfo> list = otherInfoDao.findByCondition(null);
		Map<String, Object>result=new HashMap<String, Object>();
		for(OtherInfo info:list){
			if(info.getOtherKey().contains("kefu")){
				result.put(info.getOtherKey(), info.getOtherValue());
			}
		}
		 
		result.put("quest_list", questInfoDao.findByCondition(questInfo));
		return result;
	}
	
	@Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
}

