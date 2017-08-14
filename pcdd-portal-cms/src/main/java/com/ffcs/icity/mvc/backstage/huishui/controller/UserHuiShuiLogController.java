package com.ffcs.icity.mvc.backstage.huishui.controller;

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
import com.ffcs.icity.mvc.backstage.huishui.dao.UserHuiShuiLogDao;
import com.ffcs.icity.mvc.backstage.huishui.entity.UserHuiShuiLog;
import com.ffcs.icity.mvc.backstage.huishui.service.UserHuiShuiLogService;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userHuiShuiLog")
@Controller
public class UserHuiShuiLogController extends AsynController<UserHuiShuiLog>{

	@Autowired
	private UserHuiShuiLogService userHuiShuiLogDaoService;
	
	private UserHuiShuiLogDao userHuiShuiLogDao;

	@Override
	protected CRUDDao<UserHuiShuiLog> getDao() {
		return this.userHuiShuiLogDao;
	}

	@Autowired
	public void setUserHuiShuiLogDao(UserHuiShuiLogDao userHuiShuiLogDao) {
		this.userHuiShuiLogDao = userHuiShuiLogDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/huishui/log";
	}
	
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response,@RequestBody List<Map<String,Object>> data) throws Exception{
		String status = request.getParameter("status");
		userHuiShuiLogDaoService.updateStatus(data,status);
		return "true";
	}
}
