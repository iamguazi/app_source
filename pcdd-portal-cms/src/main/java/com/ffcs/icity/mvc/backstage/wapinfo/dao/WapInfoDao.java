package com.ffcs.icity.mvc.backstage.wapinfo.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.wapinfo.entity.WapInfo;

@MyBatisRepository
public interface WapInfoDao extends CRUDDao<WapInfo> {
	public WapInfo getWapInfoByKey(String wapKey);
}