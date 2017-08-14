package com.ffcs.icity.mvc.backstage.other.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.other.dao.OtherParamsDao;
import com.ffcs.icity.mvc.backstage.other.entity.OtherParams;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/otherParams")
@Controller
public class OtherParamsController extends AsynController<OtherParams>{

	private OtherParamsDao otherParamsDao;

	@Override
	protected CRUDDao<OtherParams> getDao() {
		return this.otherParamsDao;
	}

	@Autowired
	public void setOtherParamsDao(OtherParamsDao otherParamsDao) {
		this.otherParamsDao = otherParamsDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/other/params";
	}
}
