package com.ffcs.icity.mvc.backstage.notice.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.notice.entity.UserAndNotice;

@MyBatisRepository
public interface UserAndNoticeDao extends CRUDDao<UserAndNotice> {
	
}