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

public class UpdatePayParamsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IPayParamsDao payParamsDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "payParams_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		Object id=requestArgument.get("id");
		Object mchId=requestArgument.get("mch_id");
		Object mchKey=requestArgument.get("mch_key");
		Object noticeUrl=requestArgument.get("notice_url");
		Object callbackUrl=requestArgument.get("callback_url");
		Object mchType=requestArgument.get("mch_type");
		
		PayParams payParams=payParamsDao. getPayParamsById(Integer.parseInt(id.toString()));
		 /**
	     * id    
	     */
		if(id!=null&&!"".equals(id.toString())){
			payParams.setId(Integer.parseInt(id.toString()));
		}
		 /**
	     * 商户id    
	     */
		if(mchId!=null&&!"".equals(mchId.toString())){
			payParams.setMchId(mchId.toString());
		}
		 /**
	     * 商户key    
	     */
		if(mchKey!=null&&!"".equals(mchKey.toString())){
			payParams.setMchKey(mchKey.toString());
		}
		 /**
	     * noticeUrl    
	     */
		if(noticeUrl!=null&&!"".equals(noticeUrl.toString())){
			payParams.setNoticeUrl(noticeUrl.toString());
		}
		 /**
	     * callbackUrl    
	     */
		if(callbackUrl!=null&&!"".equals(callbackUrl.toString())){
			payParams.setCallbackUrl(callbackUrl.toString());
		}
		 /**
	     * 商户名    
	     */
		if(mchType!=null&&!"".equals(mchType.toString())){
			payParams.setMchType(mchType.toString());
		}
		payParamsDao.updatePayParams(payParams);
		return "ok";
	}
}

