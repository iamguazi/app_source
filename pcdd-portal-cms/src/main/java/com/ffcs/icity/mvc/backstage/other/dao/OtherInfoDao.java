package com.ffcs.icity.mvc.backstage.other.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mvc.backstage.other.entity.OtherInfo;

@MyBatisRepository
public interface OtherInfoDao extends CRUDDao<OtherInfo> {
	public OtherInfo getByKey(String otherKey);
}