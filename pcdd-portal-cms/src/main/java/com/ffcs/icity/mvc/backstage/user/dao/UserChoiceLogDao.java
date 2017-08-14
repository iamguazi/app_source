package com.ffcs.icity.mvc.backstage.user.dao;

import java.util.Map;

import com.ffcs.icity.mvc.backstage.user.entity.UserChoiceLog;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserChoiceLogDao extends CRUDDao<UserChoiceLog> {
	
	Double getTotalXhibitPoint(Map<String,Object> params);
}