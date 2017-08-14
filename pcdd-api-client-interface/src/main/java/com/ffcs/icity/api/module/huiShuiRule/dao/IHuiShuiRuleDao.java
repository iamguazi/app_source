package com.ffcs.icity.api.module.huiShuiRule.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.huiShuiRule.entity.*;
import com.ffcs.icity.api.module.huiShuiRule.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IHuiShuiRuleDao {
	
	public List<HuiShuiRule> findAll();
	
	public List<HuiShuiRule> findByCondition(HuiShuiRule huiShuiRule);
	
	public HuiShuiRule getHuiShuiRuleById(int id);
	
	public void insertHuiShuiRule(HuiShuiRule huiShuiRule);
	
	public void updateHuiShuiRule(HuiShuiRule huiShuiRule);
	
	public void deleteHuiShuiRule(long id);
}
