package com.ffcs.icity.mvc.backstage.notice.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;

@MyBatisRepository
public interface NoticeInfoDao extends CRUDDao<NoticeInfo> {
	
}