package com.ffcs.icity.mvc.backstage.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    
 Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 后台需要登录验证的URL
     */
    public static String BACKSTAGE_LOGINVALID_URL_PREV = "/backstage/";
    /**
     * 后台不需要验证的URL
     */
    public static String[] BACKSTAGE_NOLOGINVALID_URL_PREV = new String[]{"/admin/login","/admin/toLogin","/captcha.jpg"};
    
    /**
     * 后台验证失败重定向url
     */
    public static String BACKSTAGE_NOLOGIN_URL = "/admin/toLogin";
    
    
    /**
     * 用户未登录
     */
    public static int CODE_USER_DOESNOT_LOGIN = 100;
    public static String DESC_USER_DOESNOT_LOGIN = "您还未登录，请先登录!";
    
    /**
     * 用户注销
     */
    public static int CODE_USER_LOGOUT = 101;
    public static String DESC_USER_LOGOUT = "注销成功!";
    
    
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        if(this.isBackstageValidURL(request) && null == request.getSession().getAttribute("manager")){
        	return this.noLogin(request, response);
        }
        
        return true;
    }
    
    /**
     * 未登录
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @date: 2014-12-22 下午1:38:58
     * @version: 1.00.00
     * @history:
     */
    private boolean noLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher(BACKSTAGE_NOLOGIN_URL).forward(request, response);
        return false;
    }
    
    
    /**
     * 判断url是否要做后台登录验证
     * 
     * @param request
     * @return
     * @date: 2014-12-15 下午3:34:37
     * @version: 1.00.00
     * @history:
     */
    protected boolean isBackstageValidURL(HttpServletRequest request){
    	String uri = request.getRequestURI();
    	String contextPath = request.getContextPath();
        logger.debug("request uri = " + uri);
        
        if(uri.startsWith(contextPath+BACKSTAGE_LOGINVALID_URL_PREV)){
            for (String noValidUrl : BACKSTAGE_NOLOGINVALID_URL_PREV) {
                if(uri.startsWith(contextPath+noValidUrl)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
}
