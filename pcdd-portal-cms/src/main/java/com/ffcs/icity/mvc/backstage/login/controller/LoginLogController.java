package com.ffcs.icity.mvc.backstage.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.login.dao.LoginLogDao;
import com.ffcs.icity.mvc.backstage.login.entity.LoginLog;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/loginLog")
@Controller
public class LoginLogController extends AsynController<LoginLog>{

	private LoginLogDao loginLogDao;

	@Override
	protected CRUDDao<LoginLog> getDao() {
		return this.loginLogDao;
	}

	@Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/login_log/manager";
	}
}
