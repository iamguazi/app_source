package com.ffcs.icity.api.module.withdrawalsLogs.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.withdrawalsLogs.entity.*;
import com.ffcs.icity.api.module.withdrawalsLogs.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IWithdrawalsLogsDao {
	
	public List<WithdrawalsLogs> findAll();
	
	public List<WithdrawalsLogs> findByCondition(WithdrawalsLogs withdrawalsLogs);
	
	public WithdrawalsLogs getWithdrawalsLogsById(int id);
	
	public void insertWithdrawalsLogs(WithdrawalsLogs withdrawalsLogs);
	
	public void updateWithdrawalsLogs(WithdrawalsLogs withdrawalsLogs);
	
	public void deleteWithdrawalsLogs(long id);
	
	public double getFee(Map< String, Object> params);
	
	public int getWithdrawalsCount(int userId);
}
