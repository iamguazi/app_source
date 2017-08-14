package com.ffcs.icity.mvc.backstage.version.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.version.entity.VersionInfo;

@MyBatisRepository
public interface VersionInfoDao extends CRUDDao<VersionInfo> {
	
}