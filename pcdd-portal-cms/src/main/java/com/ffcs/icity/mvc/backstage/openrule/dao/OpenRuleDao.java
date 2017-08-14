package com.ffcs.icity.mvc.backstage.openrule.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.openrule.entity.OpenRule;

@MyBatisRepository
public interface OpenRuleDao extends CRUDDao<OpenRule> {
	
}