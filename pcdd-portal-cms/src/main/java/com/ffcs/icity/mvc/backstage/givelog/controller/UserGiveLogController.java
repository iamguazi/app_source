package com.ffcs.icity.mvc.backstage.givelog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.givelog.dao.UserGiveLogDao;
import com.ffcs.icity.mvc.backstage.givelog.entity.UserGiveLog;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userGiveLog")
@Controller
public class UserGiveLogController extends AsynController<UserGiveLog>{

	private UserGiveLogDao userGiveLogDao;

	@Override
	protected CRUDDao<UserGiveLog> getDao() {
		return this.userGiveLogDao;
	}

	@Autowired
	public void setUserGiveLogDao(UserGiveLogDao userGiveLogDao) {
		this.userGiveLogDao = userGiveLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/givelog";
	}

}
