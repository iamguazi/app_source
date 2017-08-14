package com.ffcs.icity.api.module.payListConfig.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.payListConfig.entity.*;
import com.ffcs.icity.api.module.payListConfig.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IPayListConfigDao {
	
	public List<PayListConfig> findAll();
	
	public List<PayListConfig> findByCondition(PayListConfig payListConfig);
	
	public PayListConfig getPayListConfigById(int id);
	
	public void insertPayListConfig(PayListConfig payListConfig);
	
	public void updatePayListConfig(PayListConfig payListConfig);
	
	public void deletePayListConfig(long id);
}
