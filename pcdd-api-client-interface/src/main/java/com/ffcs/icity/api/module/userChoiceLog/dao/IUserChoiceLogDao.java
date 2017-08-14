package com.ffcs.icity.api.module.userChoiceLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userChoiceLog.entity.*;
import com.ffcs.icity.api.module.userChoiceLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserChoiceLogDao {
	
	public List<UserChoiceLog> findAll();
	
	public List<UserChoiceLog> findByCondition(UserChoiceLog userChoiceLog);
	
	public UserChoiceLog getUserChoiceLogById(int id);
	
	public void insertUserChoiceLog(UserChoiceLog userChoiceLog);
	
	public void updateUserChoiceLog(UserChoiceLog userChoiceLog);
	
	public void deleteUserChoiceLog(long id);
	
	//批量更新未中奖记录
	public void updateUserChoiceLogByUnZhong(UserChoiceLog userChoiceLog);
	
	//批量更新中奖记录
	public void updateUserChoiceLogByZhong(UserChoiceLog userChoiceLog);
	
	//获取中奖记录
	public List<UserChoiceLog> getZhongJiangList(UserChoiceLog userChoiceLog);
	
	public double getSumByNo(UserChoiceLog userChoiceLog);
	
	//13 14特殊处理开奖
	public void updateUserChoiceLogBy1314(Map<String, Object>params);
	
	
	public  double getUserChoiceAllFee(UserChoiceLog userChoiceLog);
}
