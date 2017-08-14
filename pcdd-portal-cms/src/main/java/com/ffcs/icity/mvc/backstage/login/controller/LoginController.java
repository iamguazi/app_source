package com.ffcs.icity.mvc.backstage.login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.backstage.login.dao.LoginLogDao;
import com.ffcs.icity.mvc.backstage.login.entity.LoginLog;
import com.ffcs.icity.mvc.backstage.manager.dao.ManagerDao;
import com.ffcs.icity.mvc.backstage.manager.entity.Manager;
import com.ffcs.icity.mvc.common.SessionUtil;

/**
 * <p>
 * Copyright: Copyright (c) 2016-7-28
 * </p>
 * <p>
 * Company: Ffcs. Co.,Ltd
 * </p>
 * 
 * @ClassName: LoginController.java
 * @Description: TODO功能描述
 * @author: Linguisen
 * @version: V1.0
 * @Date: 2016-7-28 上午10:22:01
 */
@RequestMapping("admin")
@Controller
public class LoginController {

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private LoginLogDao loginLogDao;

	@RequestMapping("/toLogin")
	protected Object toLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "/backstage/login/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		SessionUtil.removeManagerSesstionKey(req);
		return "redirect:/backstage/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	protected Object doLogin(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		Map<String, Object> result = new HashMap<String, Object>();

		String userName = req.getParameter("name");
		String password = req.getParameter("password");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("name", userName);
		//paramsMap.put("password", password);
		LoginLog loginLog = new LoginLog();
		loginLog.setCreateTime(new Date());
		loginLog.setUserType(2);
		loginLog.setIp(getIp(req));
		loginLog.setRemarks("登录日志");
		try {
			List<Manager> managers = managerDao.getManagerByNameAndPwd(paramsMap);
			if(managers == null || managers.size() <= 0){
				loginLog.setUserId(null);
				loginLog.setUserName(userName);
				loginLog.setRemarks("错误原因:账号不存在\r\n"+getRemarks(req,userName));
				try {
					loginLogDao.insert(loginLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
				result.put("result_code", 0);
				result.put("result_msg", "账号或者密码错误");
				return result;
			}
			if (managers.get(0).getPasswd().equals(password)) {
				
				if (managers.get(0).getStatus().intValue() != 1) {
					result.put("result_code", 0);
					result.put("result_msg", "账号被禁用");
					loginLog.setUserId(null);
					loginLog.setUserName(userName);
					loginLog.setRemarks("错误原因:账号被禁用\r\n"+getRemarks(req,userName));
					try {
						loginLogDao.insert(loginLog);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return result;
				}

				managerDao.updateLoginTime(managers.get(0));

				req.getSession().setAttribute("manager", managers.get(0));
				result.put("result_code", 1);
				result.put("result_msg", "登录成功");
				
				loginLog.setUserId(managers.get(0).getId().intValue());
				loginLog.setUserName(managers.get(0).getName());
				try {
					loginLogDao.insert(loginLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			} else {
				result.put("result_code", 0);
				result.put("result_msg", "账号或者密码错误");
				
				loginLog.setUserId(null);
				loginLog.setUserName(userName);
				loginLog.setRemarks("错误原因:账号或者密码错误\r\n"+getRemarks(req,userName));
				try {
					loginLogDao.insert(loginLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}
		}catch (Exception e){
			result.put("result_code", 0);
			result.put("result_msg", e.getMessage());
			loginLog.setUserId(null);
			loginLog.setUserName(userName);
			loginLog.setRemarks("错误原因:"+e.getMessage()+"\r\n"+getRemarks(req,userName));
			try {
				loginLogDao.insert(loginLog);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return result;
		}
		
	}
     
	private String getRemarks(HttpServletRequest request,String userName){
		String referer = request.getHeader("Referer"); 
		String userAgent = request.getHeader("User-Agent"); 
		String ip = getIp(request);
		StringBuilder sb = new StringBuilder();
		sb.append("用户名:").append(userName).append("\r\n")
		.append("ip:").append(ip).append("\r\n")
		.append("userAgent:").append(userAgent)
		.append("\r\n").append("referer:").append(referer).append("\r\n");
		return sb.toString();
	}
	
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
