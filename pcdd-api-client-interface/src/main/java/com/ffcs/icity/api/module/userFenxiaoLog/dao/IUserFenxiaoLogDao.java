package com.ffcs.icity.api.module.userFenxiaoLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userFenxiaoLog.entity.*;
import com.ffcs.icity.api.module.userFenxiaoLog.dao.*;
import com.ffcs.icity.api.module.userHuiShuiLog.entity.TuiJianVoLog;

import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserFenxiaoLogDao {
	
	public List<UserFenxiaoLog> findAll();
	
	public List<UserFenxiaoLog> findByCondition(UserFenxiaoLog userFenxiaoLog);
	
	public UserFenxiaoLog getUserFenxiaoLogById(int id);
	
	public void insertUserFenxiaoLog(UserFenxiaoLog userFenxiaoLog);
	
	public void updateUserFenxiaoLog(UserFenxiaoLog userFenxiaoLog);
	
	public void deleteUserFenxiaoLog(long id);
	
	public int getFenxiaoCount();
	
	//获取下级所有用户昨天的投注情况
	public List<TuiJianVoLog> getAllUserListByCode(String code);
	
}
