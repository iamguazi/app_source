package com.ffcs.icity.api.module.payListConfig.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.payListConfig.entity.*;
import com.ffcs.icity.api.module.payListConfig.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class PayListConfigListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IPayListConfigDao payListConfigDao;
	
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
	 
		PayListConfig payListConfig=new PayListConfig();
		payListConfig.setStatus(1);
		// 1线上支付 2线下支付  
		payListConfig.setType(1);
		Map<String, Object>result=new HashMap<String, Object>();
		result.put("online", payListConfigDao.findByCondition(payListConfig));
		
		payListConfig.setType(2);
		result.put("offline", payListConfigDao.findByCondition(payListConfig));
		return result;
	}
}

