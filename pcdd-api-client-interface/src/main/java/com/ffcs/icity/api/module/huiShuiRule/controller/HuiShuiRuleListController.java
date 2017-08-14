package com.ffcs.icity.api.module.huiShuiRule.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.huiShuiRule.entity.*;
import com.ffcs.icity.api.module.huiShuiRule.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class HuiShuiRuleListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IHuiShuiRuleDao huiShuiRuleDao;
	
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

		HuiShuiRule huiShuiRule=new HuiShuiRule();
		Map<String, Object>result=new HashMap<String, Object>();
		
		huiShuiRule.setAreaType(1);
		result.put("room1", huiShuiRuleDao.findByCondition(huiShuiRule));
		huiShuiRule.setAreaType(2);
		result.put("room2", huiShuiRuleDao.findByCondition(huiShuiRule));
		huiShuiRule.setAreaType(3);
		result.put("room3", huiShuiRuleDao.findByCondition(huiShuiRule));
		return result;
	}
	
	@Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
}

