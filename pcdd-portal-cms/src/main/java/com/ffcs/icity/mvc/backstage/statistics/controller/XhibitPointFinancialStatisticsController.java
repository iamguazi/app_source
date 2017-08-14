package com.ffcs.icity.mvc.backstage.statistics.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.statistics.dao.PreviewFinancialStatisticsDao;
import com.ffcs.icity.mvc.backstage.statistics.entity.PreviewFinancialStatistics;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;


@RequestMapping("backstage/xhibitPoinFinancialStatistics")
@Controller
public class XhibitPointFinancialStatisticsController extends AsynController<PreviewFinancialStatistics>{
	
	
	@Autowired
	private PreviewFinancialStatisticsDao previewfinancialStatisticsDao;
	
	
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "backstage/statistics/xhibitPoint/list";
	} 
	
	

	@Override
	protected CRUDDao<PreviewFinancialStatistics> getDao() {
		return previewfinancialStatisticsDao;
	}
	
	@Override
	protected Page<PreviewFinancialStatistics> getPage(
			Map<String, Object> params, HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<PreviewFinancialStatistics> page = new Page<PreviewFinancialStatistics>(pageNo, pageSize);
		previewfinancialStatisticsDao.queryPreview(params, page);
		return page;
	}
	
}
