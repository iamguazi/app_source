package com.ffcs.icity.mvc.backstage.huishui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.huishui.dao.HuiShuiRuleDao;
import com.ffcs.icity.mvc.backstage.huishui.entity.HuiShuiRule;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/huiShuiRule")
@Controller
public class HuiShuiRuleController extends AsynController<HuiShuiRule>{

	private HuiShuiRuleDao huiShuiRuleDao;

	@Override
	protected CRUDDao<HuiShuiRule> getDao() {
		return this.huiShuiRuleDao;
	}

	@Autowired
	public void setHuiShuiRuleDao(HuiShuiRuleDao huiShuiRuleDao) {
		this.huiShuiRuleDao = huiShuiRuleDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/huishui/rules";
	}
}
