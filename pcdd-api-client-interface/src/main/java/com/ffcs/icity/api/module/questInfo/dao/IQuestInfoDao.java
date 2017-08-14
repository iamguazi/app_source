package com.ffcs.icity.api.module.questInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.questInfo.entity.*;
import com.ffcs.icity.api.module.questInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IQuestInfoDao {
	
	public List<QuestInfo> findAll();
	
	public List<QuestInfo> findByCondition(QuestInfo questInfo);
	
	public QuestInfo getQuestInfoById(int id);
	
	public void insertQuestInfo(QuestInfo questInfo);
	
	public void updateQuestInfo(QuestInfo questInfo);
	
	public void deleteQuestInfo(long id);
}
