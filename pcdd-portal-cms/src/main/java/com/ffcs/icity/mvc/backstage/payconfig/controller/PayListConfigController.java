package com.ffcs.icity.mvc.backstage.payconfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.payconfig.dao.PayListConfigDao;
import com.ffcs.icity.mvc.backstage.payconfig.entity.PayListConfig;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/payListConfig")
@Controller
public class PayListConfigController extends AsynController<PayListConfig>{

	private PayListConfigDao payListConfigDao;

	@Override
	protected CRUDDao<PayListConfig> getDao() {
		return this.payListConfigDao;
	}

	@Autowired
	public void setPayListConfigDao(PayListConfigDao payListConfigDao) {
		this.payListConfigDao = payListConfigDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/pay_config";
	}
	
	@RequestMapping("getPayListConfig")
	@ResponseBody
	public Object getPayListConfig(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("status", 1);
			List<PayListConfig> payList = payListConfigDao.query(params, null);
			if (CollectionUtils.isEmpty(payList)){
				return Result.getSuccessForNoData();
			} else {
				return Result.getSuccessForData(payList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServiceError();
		}
	}
}
