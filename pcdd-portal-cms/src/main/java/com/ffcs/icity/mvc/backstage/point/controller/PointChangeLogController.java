package com.ffcs.icity.mvc.backstage.point.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.point.dao.PointChangeLogDao;
import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;

@RequestMapping("backstage/pointChangeLog")
@Controller
public class PointChangeLogController extends AsynController<PointChangeLog>{
	
	@Value("${imgShowRoot}")
	private String imgShowRoot; 
	 
	private PointChangeLogDao pointChangeLogDao;
	
	@Override
	protected CRUDDao<PointChangeLog> getDao() {
		
		return pointChangeLogDao;
	}

	@Autowired
	public void setPointChangeLogDao(PointChangeLogDao pointChangeLogDao) {
		this.pointChangeLogDao = pointChangeLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/point";
	}
	
	
	@RequestMapping("toIndex")
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("imgShowRoot", imgShowRoot);
    	request.setAttribute("userId", request.getParameter("userId"));
        return super.getFullViewName("list"); 
    }
	
	
	@Override
	protected Page<PointChangeLog> getPage(Map<String, Object> params, HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<PointChangeLog> page = new Page<PointChangeLog>(pageNo, pageSize);
		pointChangeLogDao.queryViewIndex(params, page);
		return page;
	}
	
	@RequestMapping("/getPointDescs")
	@ResponseBody
	public Object getPointDescs(HttpServletRequest request, HttpServletResponse response){
		try {
			List<String> pointDescs = pointChangeLogDao.getPointDesc();
			return Result.getSuccessForData(pointDescs);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServerError(e.getLocalizedMessage());
		}
	}
}
