package com.ffcs.icity.api.module.userLevel.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userLevel.entity.*;
import com.ffcs.icity.api.module.userLevel.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserLevelDao {
	
	public List<UserLevel> findAll();
	
	public List<UserLevel> findByCondition(UserLevel userLevel);
	
	public UserLevel getUserLevelById(int id);
	
	public void insertUserLevel(UserLevel userLevel);
	
	public void updateUserLevel(UserLevel userLevel);
	
	public void deleteUserLevel(long id);
	
	public UserLevel getUserLevelByPoint(double rechargeFee);
}
