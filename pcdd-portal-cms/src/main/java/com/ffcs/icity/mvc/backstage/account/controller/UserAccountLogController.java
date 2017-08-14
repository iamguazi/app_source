package com.ffcs.icity.mvc.backstage.account.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.account.dao.UserAccountLogDao;
import com.ffcs.icity.mvc.backstage.account.entity.UserAccountLog;
import com.ffcs.icity.mvc.backstage.account.service.UserAccountLogService;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userAccountLog")
@Controller
public class UserAccountLogController extends AsynController<UserAccountLog>{

	private final Logger logger = LoggerFactory.getLogger(UserAccountLogController.class);
	
	@Value("${imgShowRoot}")
    private String imgShowRoot;
	
	@Autowired
	private UserAccountLogService userAccountLogService;

	private UserAccountLogDao userAccountLogDao;

	@Override
	protected CRUDDao<UserAccountLog> getDao() {
		return this.userAccountLogDao;
	}

	@Autowired
	public void setUserAccountLogDao(UserAccountLogDao userAccountLogDao) {
		this.userAccountLogDao = userAccountLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/account/log";
	}
	
	@RequestMapping("toIndex")
    @SuppressWarnings("static-access")
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("imgShowRoot", imgShowRoot);
    	request.setAttribute("accountId", request.getParameter("accountId"));
    	request.setAttribute("accountType", request.getParameter("accountType"));
    	request.setAttribute("account", request.getParameter("account"));
        return super.getFullViewName(super.LIST_VIEW_NAME); 
    }
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response){
		Long id = Long.parseLong(request.getParameter("id")) ;
		Integer status = Integer.parseInt(request.getParameter("status"));
		Long userId = Long.parseLong(request.getParameter("userId"));
		Double point = Double.parseDouble(request.getParameter("point"));
		userAccountLogService.updateStatus(id, status, userId, point);;
		return true;
	}
	
	@RequestMapping("/noticeNewUserAccountLog")
	@ResponseBody
	public Object noticeNewUserAccountLog(HttpServletRequest request, HttpServletResponse response){
		int count;
		try {
			logger.debug("start noticeNewUserAccountLog.......");
			count = userAccountLogDao.countStatus(0);
			return Result.getSuccessForData(count);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServerError(e.getLocalizedMessage());
		}
	}
	
}
