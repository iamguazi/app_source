package com.ffcs.icity.mvc.backstage.gift.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.gift.dao.ExchangeGiftLogDao;
import com.ffcs.icity.mvc.backstage.gift.entity.ExchangeGiftLog;
import com.ffcs.icity.mvc.backstage.gift.service.ExchangeGiftLogService;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/exchangeGiftLog")
@Controller
public class ExchangeGiftLogController extends AsynController<ExchangeGiftLog>{

	@Autowired
	private ExchangeGiftLogService exchangeGiftLogService;
	
	private ExchangeGiftLogDao exchangeGiftLogDao;

	@Override
	protected CRUDDao<ExchangeGiftLog> getDao() {
		return this.exchangeGiftLogDao;
	}

	@Autowired
	public void setExchangeGiftLogDao(ExchangeGiftLogDao exchangeGiftLogDao) {
		this.exchangeGiftLogDao = exchangeGiftLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/gift_exchange";
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] ids = request.getParameterValues("ids");
		if(ids.length <= 0 ){
			return false;
		}
		String status = request.getParameter("status");
		exchangeGiftLogService.updateStatus(ids,Integer.parseInt(status));
		return true;
	}
	
	@RequestMapping("/noticeExchangeGiftLog")
	@ResponseBody
	public Object noticeExchangeGiftLog(HttpServletRequest request, HttpServletResponse response){
		int count;
		try {
			count = exchangeGiftLogDao.countStatus(0);
			return Result.getSuccessForData(count);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServerError(e.getLocalizedMessage());
		}
	}
}
