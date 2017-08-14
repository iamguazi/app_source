package com.ffcs.icity.api.module.userHuiShuiLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userHuiShuiLog.entity.*;
import com.ffcs.icity.api.module.userHuiShuiLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserHuiShuiLogDao {
	
	public List<UserHuiShuiLog> findAll();
	
	public List<UserHuiShuiLog> findByCondition(UserHuiShuiLog userHuiShuiLog);
	
	public UserHuiShuiLog getUserHuiShuiLogById(int id);
	
	public void insertUserHuiShuiLog(UserHuiShuiLog userHuiShuiLog);
	
	public void updateUserHuiShuiLog(UserHuiShuiLog userHuiShuiLog);
	
	public void deleteUserHuiShuiLog(long id);
	
	//获取用户id和下注亏损总额
	public List<HuiShuiVoLog>getUserList(Map<String , Object> params);
	
	public int getHuishuiCount(int areaId);
	
	//获取有上家的用户
	public List<TuiJianVoLog> getUserListByCode(Map<String , Object> params);
	
	
	
	
}
