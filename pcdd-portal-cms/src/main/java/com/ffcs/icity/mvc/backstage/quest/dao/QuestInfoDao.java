package com.ffcs.icity.mvc.backstage.quest.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.quest.entity.QuestInfo;

@MyBatisRepository
public interface QuestInfoDao extends CRUDDao<QuestInfo> {
	
}