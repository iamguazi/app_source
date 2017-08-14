package com.ffcs.icity.api.module.userAccountLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.accountInfo.dao.IAccountInfoDao;
import com.ffcs.icity.api.module.accountInfo.entity.AccountInfo;
import com.ffcs.icity.api.module.userAccountLog.entity.*;
import com.ffcs.icity.api.module.userAccountLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserAccountLogListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserAccountLogDao userAccountLogDao;
	
	@Autowired
	private IAccountInfoDao accountInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
		assertNotEmpty(requestArgument, "user_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();
		String userId=requestArgument.get("user_id").toString();
		Object accountType=requestArgument.get("account_type");
		UserAccountLog userAccountLog=new UserAccountLog();
		userAccountLog.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		userAccountLog.setPageSize(Integer.parseInt(pageSize));
		userAccountLog.setUserId(Integer.parseInt(userId));
		if(accountType!=null){
			userAccountLog.setAccountType(Integer.parseInt(accountType.toString()));
		}
		List<UserAccountLog> list = userAccountLogDao.findByCondition(userAccountLog);
		AccountInfo accountInfo=new AccountInfo();
		List<AccountInfo> accountList = accountInfoDao.findByCondition(accountInfo);
		Map<String, AccountInfo> accountMap=new HashMap<String, AccountInfo>();
		for(AccountInfo vo:accountList){
			if(accountMap.get(vo.getId()+"")==null){
				accountMap.put(vo.getId()+"", vo);
			}
		}
		
		for(UserAccountLog log:list){
			log.setAccountInfo(accountMap.get(log.getAccountId()+""));
		}
		return list;
	}
}

