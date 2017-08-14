package com.ffcs.icity.api.module.userAccountLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userAccountLog.entity.*;
import com.ffcs.icity.api.module.userAccountLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserAccountLogDao {
	
	public List<UserAccountLog> findAll();
	
	public List<UserAccountLog> findByCondition(UserAccountLog userAccountLog);
	
	public UserAccountLog getUserAccountLogById(int id);
	
	public void insertUserAccountLog(UserAccountLog userAccountLog);
	
	public void updateUserAccountLog(UserAccountLog userAccountLog);
	
	public void deleteUserAccountLog(long id);
}
