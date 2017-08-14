package com.ffcs.icity.mvc.backstage.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.manager.entity.Manager;
import com.ffcs.icity.mvc.dao.CRUDDao;

@RequestMapping("backstage/manager/modifyPassword")
@Controller
public class ModifyPasswordController extends AsynController<Manager>{

	@Override
	protected CRUDDao<Manager> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/modifyPassword";
	}
}
