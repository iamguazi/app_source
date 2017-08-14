package com.ffcs.icity.mvc.backstage.fenxiao.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.fenxiao.dao.UserFenxiaoLogDao;
import com.ffcs.icity.mvc.backstage.fenxiao.entity.UserFenxiaoLog;
import com.ffcs.icity.mvc.backstage.fenxiao.service.UserFenxiaoLogService;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userFenxiaoLog")
@Controller
public class UserFenxiaoLogController extends AsynController<UserFenxiaoLog>{

	@Autowired
	private UserFenxiaoLogService userFenxiaoLogService;
	
	private UserFenxiaoLogDao userFenxiaoLogDao;

	@Override
	protected CRUDDao<UserFenxiaoLog> getDao() {
		return this.userFenxiaoLogDao;
	}

	@Autowired
	public void setUserFenxiaoLogDao(UserFenxiaoLogDao userFenxiaoLogDao) {
		this.userFenxiaoLogDao = userFenxiaoLogDao;
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response,@RequestBody List<Map<String,Object>> data) throws Exception{
		String status = request.getParameter("status");
		userFenxiaoLogService.updateStatus(data,status);
		return "true";
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/fenxiao/log";
	}
}
