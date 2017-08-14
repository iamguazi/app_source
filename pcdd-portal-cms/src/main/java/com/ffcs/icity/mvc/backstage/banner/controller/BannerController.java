package com.ffcs.icity.mvc.backstage.banner.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.banner.dao.BannerDao;
import com.ffcs.icity.mvc.backstage.banner.entity.Banner;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/banner")
@Controller
public class BannerController extends AsynController<Banner>{

	private BannerDao bannerDao;

	@Override
	protected CRUDDao<Banner> getDao() {
		return this.bannerDao;
	}

	@Autowired
	public void setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/banner";
	}
	
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("ids");
		if(ids.length <= 0 ){
			return false;
		}
		String status = request.getParameter("status");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		params.put("status", status);
		bannerDao.updateStatus(params);
		return true;
	}
}
