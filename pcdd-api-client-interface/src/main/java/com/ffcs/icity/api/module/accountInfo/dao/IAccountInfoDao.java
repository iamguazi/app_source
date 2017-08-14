package com.ffcs.icity.api.module.accountInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.accountInfo.entity.*;
import com.ffcs.icity.api.module.accountInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IAccountInfoDao {
	
	public List<AccountInfo> findAll();
	
	public List<AccountInfo> findByCondition(AccountInfo accountInfo);
	
	public AccountInfo getAccountInfoById(int id);
	
	public void insertAccountInfo(AccountInfo accountInfo);
	
	public void updateAccountInfo(AccountInfo accountInfo);
	
	public void deleteAccountInfo(long id);
}
