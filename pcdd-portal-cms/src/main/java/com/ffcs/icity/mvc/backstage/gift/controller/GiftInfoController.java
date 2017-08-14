package com.ffcs.icity.mvc.backstage.gift.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.gift.dao.GiftInfoDao;
import com.ffcs.icity.mvc.backstage.gift.entity.GiftInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/giftInfo")
@Controller
public class GiftInfoController extends AsynController<GiftInfo>{

	private GiftInfoDao giftInfoDao;

	@Override
	protected CRUDDao<GiftInfo> getDao() {
		return this.giftInfoDao;
	}

	@Autowired
	public void setGiftInfoDao(GiftInfoDao giftInfoDao) {
		this.giftInfoDao = giftInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/gift";
	}
	
	@RequestMapping("/isExist")
	@ResponseBody
	public Object isExist(HttpServletRequest request ,HttpServletResponse reponse){
		String id = request.getParameter("id");
		String giftKey = request.getParameter("giftKey");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("giftKey", giftKey);
		if(StringUtils.isNotBlank(id)){
			params.put("id", Integer.parseInt(id));
		}
		
		return giftInfoDao.isExist(params)>0?false:true;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("ids");
		String status = request.getParameter("status");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		params.put("status", status);
		giftInfoDao.updateStatus(params);
		return true;
	}
}
