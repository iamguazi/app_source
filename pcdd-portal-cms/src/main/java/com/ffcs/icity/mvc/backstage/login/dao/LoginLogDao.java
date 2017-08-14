package com.ffcs.icity.mvc.backstage.login.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.login.entity.LoginLog;

@MyBatisRepository
public interface LoginLogDao extends CRUDDao<LoginLog> {
	
}