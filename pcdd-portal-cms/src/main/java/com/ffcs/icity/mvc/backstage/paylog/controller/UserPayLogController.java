package com.ffcs.icity.mvc.backstage.paylog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.paylog.dao.UserPayLogDao;
import com.ffcs.icity.mvc.backstage.paylog.entity.UserPayLog;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userPayLog")
@Controller
public class UserPayLogController extends AsynController<UserPayLog>{

	private UserPayLogDao userPayLogDao;

	@Override
	protected CRUDDao<UserPayLog> getDao() {
		return this.userPayLogDao;
	}

	@Autowired
	public void setUserPayLogDao(UserPayLogDao userPayLogDao) {
		this.userPayLogDao = userPayLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/paylog";
	}
}
