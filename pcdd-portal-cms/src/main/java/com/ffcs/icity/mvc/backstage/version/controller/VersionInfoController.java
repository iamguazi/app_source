package com.ffcs.icity.mvc.backstage.version.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.version.dao.VersionInfoDao;
import com.ffcs.icity.mvc.backstage.version.entity.VersionInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/versionInfo")
@Controller
public class VersionInfoController extends AsynController<VersionInfo>{

	private VersionInfoDao versionInfoDao;

	@Override
	protected CRUDDao<VersionInfo> getDao() {
		return this.versionInfoDao;
	}

	@Autowired
	public void setVersionInfoDao(VersionInfoDao versionInfoDao) {
		this.versionInfoDao = versionInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/version";
	}
}
