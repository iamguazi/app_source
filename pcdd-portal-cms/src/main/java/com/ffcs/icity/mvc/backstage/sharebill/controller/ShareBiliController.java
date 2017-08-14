package com.ffcs.icity.mvc.backstage.sharebill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.sharebill.dao.ShareBiliDao;
import com.ffcs.icity.mvc.backstage.sharebill.entity.ShareBili;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/shareBili")
@Controller
public class ShareBiliController extends AsynController<ShareBili>{

	private ShareBiliDao shareBiliDao;

	@Override
	protected CRUDDao<ShareBili> getDao() {
		return this.shareBiliDao;
	}

	@Autowired
	public void setShareBiliDao(ShareBiliDao shareBiliDao) {
		this.shareBiliDao = shareBiliDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/share_bili";
	}
}
