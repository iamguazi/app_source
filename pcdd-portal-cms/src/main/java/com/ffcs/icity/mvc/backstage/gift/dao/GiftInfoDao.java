package com.ffcs.icity.mvc.backstage.gift.dao;

import java.util.Map;

import com.ffcs.icity.mvc.backstage.gift.entity.GiftInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface GiftInfoDao extends CRUDDao<GiftInfo> {
	public int isExist(Map<String,Object> params);
	public void updateStatus(Map<String,Object> params);
}