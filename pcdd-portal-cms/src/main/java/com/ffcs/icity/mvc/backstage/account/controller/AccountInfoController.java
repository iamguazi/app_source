package com.ffcs.icity.mvc.backstage.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.account.dao.AccountInfoDao;
import com.ffcs.icity.mvc.backstage.account.entity.AccountInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/accountInfo")
@Controller
public class AccountInfoController extends AsynController<AccountInfo>{

	private AccountInfoDao accountInfoDao;

	@Override
	protected CRUDDao<AccountInfo> getDao() {
		return this.accountInfoDao;
	}

	@Autowired
	public void setAccountInfoDao(AccountInfoDao accountInfoDao) {
		this.accountInfoDao = accountInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/account/info";
	}
}
