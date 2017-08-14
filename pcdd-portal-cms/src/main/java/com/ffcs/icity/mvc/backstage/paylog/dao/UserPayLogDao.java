package com.ffcs.icity.mvc.backstage.paylog.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.paylog.entity.UserPayLog;

@MyBatisRepository
public interface UserPayLogDao extends CRUDDao<UserPayLog> {
	
}