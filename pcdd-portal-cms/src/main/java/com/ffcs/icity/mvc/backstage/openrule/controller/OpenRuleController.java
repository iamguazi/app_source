package com.ffcs.icity.mvc.backstage.openrule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.openrule.dao.OpenRuleDao;
import com.ffcs.icity.mvc.backstage.openrule.entity.OpenRule;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/openRule")
@Controller
public class OpenRuleController extends AsynController<OpenRule>{

	private OpenRuleDao openRuleDao;

	@Override
	protected CRUDDao<OpenRule> getDao() {
		return this.openRuleDao;
	}

	@Autowired
	public void setOpenRuleDao(OpenRuleDao openRuleDao) {
		this.openRuleDao = openRuleDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/open_rule";
	}
}
