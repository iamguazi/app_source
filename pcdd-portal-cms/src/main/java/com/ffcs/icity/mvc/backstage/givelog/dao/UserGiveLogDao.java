package com.ffcs.icity.mvc.backstage.givelog.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.givelog.entity.UserGiveLog;

@MyBatisRepository
public interface UserGiveLogDao extends CRUDDao<UserGiveLog> {
    	
}