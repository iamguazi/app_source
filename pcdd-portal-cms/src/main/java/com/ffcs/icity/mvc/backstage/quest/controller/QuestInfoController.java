package com.ffcs.icity.mvc.backstage.quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.quest.dao.QuestInfoDao;
import com.ffcs.icity.mvc.backstage.quest.entity.QuestInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/questInfo")
@Controller
public class QuestInfoController extends AsynController<QuestInfo>{

	private QuestInfoDao questInfoDao;

	@Override
	protected CRUDDao<QuestInfo> getDao() {
		return this.questInfoDao;
	}

	@Autowired
	public void setQuestInfoDao(QuestInfoDao questInfoDao) {
		this.questInfoDao = questInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/quest";
	}
}
