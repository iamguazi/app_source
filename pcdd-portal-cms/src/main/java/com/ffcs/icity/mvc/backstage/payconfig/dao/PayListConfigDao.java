package com.ffcs.icity.mvc.backstage.payconfig.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.payconfig.entity.PayListConfig;

@MyBatisRepository
public interface PayListConfigDao extends CRUDDao<PayListConfig> {
	
}