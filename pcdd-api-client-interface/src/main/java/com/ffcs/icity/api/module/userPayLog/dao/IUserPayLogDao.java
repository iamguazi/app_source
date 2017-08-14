package com.ffcs.icity.api.module.userPayLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userPayLog.entity.*;
import com.ffcs.icity.api.module.userPayLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserPayLogDao {
	
	public List<UserPayLog> findAll();
	
	public List<UserPayLog> findByCondition(UserPayLog userPayLog);
	
	public UserPayLog getUserPayLogById(int id);
	
	public void insertUserPayLog(UserPayLog userPayLog);
	
	public void updateUserPayLog(UserPayLog userPayLog);
	
	public void deleteUserPayLog(long id);
	
	public UserPayLog getUserPayLogByOrderNo(String orderNo);
	
	
}
