package com.ffcs.icity.mvc.backstage.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("backstage")
@Controller
public class IndexController {
	
	@RequestMapping("/index")
	protected Object index(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/backstage/index";
	}
	
}
