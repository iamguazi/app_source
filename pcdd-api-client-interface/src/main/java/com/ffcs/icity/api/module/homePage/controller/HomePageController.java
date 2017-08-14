package com.ffcs.icity.api.module.homePage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.otherInfo.dao.IOtherInfoDao;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;

public class HomePageController extends NoValidController{

	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IOtherInfoDao otherInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
	}

	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		// TODO Auto-generated method stub
		
		Map<String, Object>result=new HashMap<String, Object>();
		result.put("user_count", otherInfoDao.getOtherInfoByKey("people_num").getOtherValue());
		result.put("point", otherInfoDao.getOtherInfoByKey("get_all_point").getOtherValue());
		result.put("bili",  otherInfoDao.getOtherInfoByKey("zhuan_qian_bili").getOtherValue());
		return result;
	}
	
}
