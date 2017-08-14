package com.ffcs.icity.mvc.backstage.withdrawalslogs.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.withdrawalslogs.dao.WithdrawalsLogsDao;
import com.ffcs.icity.mvc.backstage.withdrawalslogs.entity.WithdrawalsLogs;
import com.ffcs.icity.mvc.backstage.withdrawalslogs.service.WithdrawalsLogsService;
import com.ffcs.icity.mvc.common.CodingUtil;
import com.ffcs.icity.mvc.controller.SimpleController;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/withdrawalsLogs")
@Controller
public class WithdrawalsLogsController extends AsynController<WithdrawalsLogs>{

	@Autowired
	private WithdrawalsLogsService withdrawalsLogsService; 

	@Value("${imgShowRoot}")
    private String imgShowRoot;
	
	private WithdrawalsLogsDao withdrawalsLogsDao;

	@Override
	protected CRUDDao<WithdrawalsLogs> getDao() {
		return this.withdrawalsLogsDao;
	}

	@Autowired
	public void setWithdrawalsLogsDao(WithdrawalsLogsDao withdrawalsLogsDao) {
		this.withdrawalsLogsDao = withdrawalsLogsDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/withdrawalslogs";
	}

	
	@RequestMapping("toIndex")
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("imgShowRoot", imgShowRoot);
    	request.setAttribute("userId", request.getParameter("userId"));
    	request.setAttribute("account", request.getParameter("account"));
    	request.setAttribute("nickName", request.getParameter("nickName"));
    	if(request.getParameter("nickName") != null ){
    		try {
				request.setAttribute("nickNameIso", CodingUtil.changeIso2Utf(request.getParameter("nickName").toString()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
        return super.getFullViewName(SimpleController.LIST_VIEW_NAME); 
    }
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response,@RequestBody List<Map<String,Object>> data) throws Exception{
		String status = request.getParameter("status");
		withdrawalsLogsService.updateStatus(data,status);
		return "true";
	}

	@RequestMapping("/noticeWithdrawalsLog")
	@ResponseBody
	public Object noticeWithdrawalsLog(HttpServletRequest request, HttpServletResponse response){
		int count;
		try {
			count = withdrawalsLogsDao.countStatus(0);
			return Result.getSuccessForData(count);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServerError(e.getLocalizedMessage());
		}
	}
}
