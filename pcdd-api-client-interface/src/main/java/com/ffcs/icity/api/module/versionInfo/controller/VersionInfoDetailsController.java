package com.ffcs.icity.api.module.versionInfo.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.versionInfo.entity.*;
import com.ffcs.icity.api.module.versionInfo.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class VersionInfoDetailsController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IVersionInfoDao versionInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "client_no");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		//1用户端 2商户端
		String client =requestArgument.get("client_no").toString();
		Object clientType=requestArgument.get("client_type");
		
		//ios返回
		if(clientType!=null&&"ios".equals(clientType.toString())){
			//1安卓  2ios
			VersionInfo version = versionInfoDao. getVersionInfoByNew(2);
			if(client.equals(version.getVersionNo())){
				return "{}";
			}else{
				return version;
			}
		}
		//1安卓  2ios
		VersionInfo version = versionInfoDao. getVersionInfoByNew(1);
		if(client.equals(version.getVersionNo())){
			return "0";
		}else{
			return version;
		}
		
		
	}
}

