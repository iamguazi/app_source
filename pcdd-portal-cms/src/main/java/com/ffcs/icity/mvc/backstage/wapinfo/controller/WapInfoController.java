package com.ffcs.icity.mvc.backstage.wapinfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.wapinfo.dao.WapInfoDao;
import com.ffcs.icity.mvc.backstage.wapinfo.entity.WapInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/wapInfo")
@Controller
public class WapInfoController extends AsynController<WapInfo>{

	private WapInfoDao wapInfoDao;

	@Override
	protected CRUDDao<WapInfo> getDao() {
		return this.wapInfoDao;
	}
    
   @Override
	protected String getEntityName() {
		return "backstage/wapinfo";
	}
	
	@Autowired
	public void setWapInfoDao(WapInfoDao wapInfoDao) {
		this.wapInfoDao = wapInfoDao;
	}
	
	
	@RequestMapping("/getWapInfoByKey")
	@ResponseBody
	public WapInfo getWapInfoByKey(HttpServletRequest request,HttpServletResponse response){
		WapInfo wapInfo = wapInfoDao.getWapInfoByKey(request.getParameter("wapKey"));
		return wapInfo==null?new WapInfo():wapInfo;
	}
	
	
}
