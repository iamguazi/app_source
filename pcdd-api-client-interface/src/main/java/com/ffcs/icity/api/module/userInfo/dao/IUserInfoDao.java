package com.ffcs.icity.api.module.userInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userInfo.entity.*;
import com.ffcs.icity.api.module.userInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserInfoDao {
	
	public List<UserInfo> findAll();
	
	public List<UserInfo> findByCondition(UserInfo userInfo);
	
	public UserInfo getUserInfoById(int id);
	
	public void insertUserInfo(UserInfo userInfo);
	
	public void updateUserInfo(UserInfo userInfo);
	
	public void deleteUserInfo(long id);
	
	public UserInfo getUserInfoByAccount(String account);
	
	public UserInfo getUserInfoByBand(UserInfo userInfo);	
	
	public int getUserCount();
	
	public int getUserInfoByParams(String account);
	
	
	public void updateUserInfoByPoint(UserInfo userInfo);	
	
}
