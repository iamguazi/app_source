package com.ffcs.icity.api.module.exchangeGiftLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.exchangeGiftLog.entity.*;
import com.ffcs.icity.api.module.exchangeGiftLog.dao.*;
import com.ffcs.icity.api.module.giftInfo.dao.IGiftInfoDao;
import com.ffcs.icity.api.module.giftInfo.entity.GiftInfo;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.pointChangeLog.entity.PointChangeLog;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddExchangeGiftLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IExchangeGiftLogDao exchangeGiftLogDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IGiftInfoDao giftInfoDao;
	
	@Autowired
	private IPointChangeLogDao pointChangeLogDao;
	
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "gift_id");
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "gift_count");
		assertNotEmpty(requestArgument, "address");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String giftId=requestArgument.get("gift_id").toString();
		String userId=requestArgument.get("user_id").toString();
		String giftCount=requestArgument.get("gift_count").toString();
		String address=requestArgument.get("address").toString();
		
		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
		GiftInfo giftInfo = giftInfoDao.getGiftInfoById(Integer.parseInt(giftId));
		
		ExchangeGiftLog exchangeGiftLog=new ExchangeGiftLog();
		exchangeGiftLog.setGiftId(Integer.parseInt(giftId));
		exchangeGiftLog.setUserId(Integer.parseInt(userId));
		exchangeGiftLog.setGiftName(giftInfo.getGiftName());
		exchangeGiftLog.setGiftCount(Integer.parseInt(giftCount));
		exchangeGiftLog.setNickName(userInfo.getNickName());
		exchangeGiftLog.setStatus(0);
		exchangeGiftLog.setRealName(userInfo.getRealName());
		exchangeGiftLog.setMobile(userInfo.getMobile());
		exchangeGiftLog.setUserAccount(userInfo.getAccount());
		exchangeGiftLog.setGiftPoint(giftInfo.getGiftPoint());
		exchangeGiftLog.setPoint(exchangeGiftLog.getGiftPoint()*exchangeGiftLog.getGiftCount());
		exchangeGiftLog.setAddress(address);
		
		if(userInfo.getPoint()<exchangeGiftLog.getPoint()){
			throw new ApiException("积分不足");
		}
		userInfo.setPoint(userInfo.getPoint()-exchangeGiftLog.getPoint());
		userInfoDao.updateUserInfo(userInfo);
		
		exchangeGiftLogDao.insertExchangeGiftLog(exchangeGiftLog);
		
		PointChangeLog pointChangeLog=new PointChangeLog();
		pointChangeLog.setUserId(Integer.parseInt(userId));
		pointChangeLog.setPoint(-exchangeGiftLog.getPoint());
		pointChangeLog.setPointDesc("兑换礼品");
		
		pointChangeLogDao.insertPointChangeLog(pointChangeLog);
		return "ok";
	}
	
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		// TODO Auto-generated method stub
		
		return new String[] {requestArgument.get("gift_count").toString()};
	}
}

