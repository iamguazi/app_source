package com.ffcs.icity.mvc.backstage.userlevel.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.userlevel.entity.UserLevel;

@MyBatisRepository
public interface UserLevelDao extends CRUDDao<UserLevel> {
	
}