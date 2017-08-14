package com.ffcs.icity.api.module.userPayLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.otherInfo.dao.IOtherInfoDao;
import com.ffcs.icity.api.module.userPayLog.entity.*;
import com.ffcs.icity.api.module.userPayLog.dao.*;

import java.text.SimpleDateFormat;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class CheckUserPayLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserPayLogDao userPayLogDao;
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "order_no");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String orderNo=requestArgument.get("order_no").toString();
		
		UserPayLog log = userPayLogDao.getUserPayLogByOrderNo(orderNo);
		if(log==null){
			return "0";
		}else{
			if(userId.equals(log.getUserId()+"")){
				return log.getStatus()+"";
			}
		}
		return "0";
	}
	
	 
	/**
	 * 生成订单号
	 * @return
	 */
	public String getOrderNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date()).toString()+(int)(new Random().nextDouble()*(100000 - 10000) + 10000);

	}
}

