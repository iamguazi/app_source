package com.ffcs.icity.mvc.backstage.payparams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.payparams.dao.PayParamsDao;
import com.ffcs.icity.mvc.backstage.payparams.entity.PayParams;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/payParams")
@Controller
public class PayParamsController extends AsynController<PayParams>{

	private PayParamsDao payParamsDao;

	@Override
	protected CRUDDao<PayParams> getDao() {
		return this.payParamsDao;
	}

	@Autowired
	public void setPayParamsDao(PayParamsDao payParamsDao) {
		this.payParamsDao = payParamsDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/payparams";
	}
}
