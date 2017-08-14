package com.ffcs.icity.mvc.common;

import javax.servlet.http.HttpServletRequest;

import com.ffcs.icity.mvc.backstage.manager.entity.Manager;

public class SessionUtil {

	public static void setManager(HttpServletRequest request,Manager manager) {
		request.getSession().setAttribute("manager", manager);
	}
	
	public static Manager getManager(HttpServletRequest request) {
		Manager sManager = request.getSession().getAttribute("manager") == null ? null
				: (Manager) request.getSession().getAttribute("manager");
		return sManager;
	}

	public static void removeManagerSesstionKey(HttpServletRequest request) {
		request.getSession().removeAttribute("manager");
	}
}
