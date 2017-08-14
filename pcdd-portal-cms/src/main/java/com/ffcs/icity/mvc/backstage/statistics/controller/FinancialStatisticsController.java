package com.ffcs.icity.mvc.backstage.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.BaseConstant;
import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.backstage.statistics.dao.FinancialStatisticsDao;
import com.ffcs.icity.mvc.backstage.statistics.entity.FinancialStatistics;
import com.ffcs.icity.mvc.utils.RequestUtils;


@RequestMapping("backstage/financialStatistics")
@Controller
public class FinancialStatisticsController {
	
	@Autowired
	private FinancialStatisticsDao financialStatisticsDao;
	
	
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "backstage/statistics/index/list";
	} 
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request,HttpServletResponse response){
		try{
			Map<String,Object> params = RequestUtils.resolveParams(request);
			List<FinancialStatistics> result = financialStatisticsDao.query(params);
			Map<String,Object> resultMap  = new HashMap<String,Object>();
			resultMap.put("status",BaseConstant.SUCCESS_FOR_DATA);
			resultMap.put("desc",BaseConstant.SUCCESS_FOR_DATA_DESC);
			resultMap.put("dataList",result);
			return resultMap; 
		}catch(Exception e){
			return Result.getServerError(e.getLocalizedMessage());
		}
	}

}
