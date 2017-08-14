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

public class AddUserPayLogController  extends NoValidController {
	
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
		assertNotEmpty(requestArgument, "total_fee");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String totalFee=requestArgument.get("total_fee").toString();
		
		Object payType=requestArgument.get("pay_type");
		
		UserPayLog userPayLog=new UserPayLog();
		userPayLog.setOrderNo(getOrderNo());
		userPayLog.setStatus(0);
		userPayLog.setFreeFee(0.0);
		userPayLog.setUserId(Integer.parseInt(userId));
		userPayLog.setTotalFee(Double.parseDouble(totalFee));
		//支付方式 1 余额 2微信 3 支付宝
		if(payType!=null){
			userPayLog.setPayType(Integer.parseInt(payType.toString()));
			
		}else{
			userPayLog.setPayType(2);
		}
		//1付款 2充值 
		userPayLog.setOrderType(2);
		userPayLog.setPayDesc("充值");
		 
		
		userPayLogDao.insertUserPayLog(userPayLog);
		Map<String, Object> result=new HashMap<String, Object>();
//		result.put("alipay_notify_url", otherInfoDao.getOtherInfoByKey("alipay_notify_url").getOtherValue());
		result.put("order_no", userPayLog.getOrderNo());
		return result;
	}
	
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		// TODO Auto-generated method stub
		
		return new String[] {requestArgument.get("total_fee").toString()};
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

