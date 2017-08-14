package com.ffcs.icity.mvc.backstage.payparams.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.payparams.entity.PayParams;

@MyBatisRepository
public interface PayParamsDao extends CRUDDao<PayParams> {
	
}