package com.ffcs.icity.api.module.payParams.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.payParams.entity.*;
import com.ffcs.icity.api.module.payParams.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddPayParamsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IPayParamsDao payParamsDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "id");
		assertNotEmpty(requestArgument, "mch_id");
		assertNotEmpty(requestArgument, "mch_key");
		assertNotEmpty(requestArgument, "notice_url");
		assertNotEmpty(requestArgument, "callback_url");
		assertNotEmpty(requestArgument, "mch_type");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String id=requestArgument.get("id").toString();
		String mchId=requestArgument.get("mch_id").toString();
		String mchKey=requestArgument.get("mch_key").toString();
		String noticeUrl=requestArgument.get("notice_url").toString();
		String callbackUrl=requestArgument.get("callback_url").toString();
		String mchType=requestArgument.get("mch_type").toString();
		
		PayParams payParams=new PayParams();
		payParams.setId(Integer.parseInt(id));
		payParams.setMchId(mchId);
		payParams.setMchKey(mchKey);
		payParams.setNoticeUrl(noticeUrl);
		payParams.setCallbackUrl(callbackUrl);
		payParams.setMchType(mchType);
		
		payParamsDao.insertPayParams(payParams);
		return "ok";
	}
}

