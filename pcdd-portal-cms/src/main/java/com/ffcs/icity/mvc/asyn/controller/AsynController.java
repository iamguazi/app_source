package com.ffcs.icity.mvc.asyn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.controller.SimpleController;
import com.ffcs.icity.mvc.entity.IdEntity;
import com.ffcs.icity.mvc.support.RequestContextHolder;
import com.ffcs.icity.mvc.view.json.JSONModelAndView;
import com.ffcs.icity.mybatis.Page;
/**
 * 异步控制器基类
 * @param <T>
 */
public abstract class AsynController<T extends IdEntity> extends SimpleController<T> {
    
    
    @Value("${imgShowRoot}")
    private String imgShowRoot;         // 图片显示的服务端根路径
    
    /**
     * 进入首页，即默认进入list.ftl
     * @param request
     * @param response
     * @return
     * @date: 2014-5-27 下午7:02:10
     * @version: 1.00.00
     * @history:
     */
    @RequestMapping("toIndex")
    @SuppressWarnings("static-access")
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("imgShowRoot", imgShowRoot);
        return super.getFullViewName(super.LIST_VIEW_NAME); 
    }
    
    /**
     * 扩展分页获取数据的方法，以支持异步获取数据，
     * 程序自动判断当前的请求是否为异步，是则返回一个json的JSONModelAndView，否则正常返回view
     */
    @SuppressWarnings("unchecked")
    protected Object getQueryView(boolean success, Throwable throwable, Map<String, Object> queryParams, ModelAndView model){
        
        boolean bIsAsyn = this.isAjaxRequest(RequestContextHolder.getRequest());
        if(!bIsAsyn){
            // 添加参数回写和系统环境类型
            model.addObject("queryParams", queryParams);
            model.addObject("environment", System.getProperty("spring.profiles.active"));
            return super.getQueryView(success, throwable, queryParams, model);
        }else{
            if(true == success){
                try {
                    Page<Object> page = (Page<Object>) model.getModel().get("page");
                    if(page.getResult() == null || page.getResult().size() == 0){
                        return new JSONModelAndView(Result.getSuccessForNoData());
                    } else {
                        return new JSONModelAndView(Result.getSuccessForData(page));
                    }
                } catch (Exception e) {
                    return new JSONModelAndView(Result.getSuccessForNoData());
                }
            }else{
                return new JSONModelAndView(Result.getServiceError());
            }
        }
    }
    /**
     * 扩展获取单条数据的方法，以支持数据异步获取，
     * 程序自动判断当前的请求是否为异步，是则返回一个json的JSONModelAndView，否则正常返回view
     */
    protected Object getFormView(boolean success, Throwable throwable, T entity, ModelAndView model) {
        boolean bIsAsyn = this.isAjaxRequest(RequestContextHolder.getRequest());
        if(!bIsAsyn){
            return super.getFormView(success, throwable, entity, model);
        }else{
            if(true == success){
                return new JSONModelAndView(Result.getSuccessForData(entity));
            }else{
                return new JSONModelAndView(Result.getServiceError());
            }
        }
    }
    @Override
    protected Object getSaveView(boolean success, Throwable throwable,
            T entity, ModelAndView model) {
        boolean bIsAsyn = this.isAjaxRequest(RequestContextHolder.getRequest());
        if(!bIsAsyn){
            return super.getSaveView(success, throwable, entity, model);
        }else{
            if(true == success){
                return new JSONModelAndView(Result.getSuccessForData(entity));
            }else{
                return new JSONModelAndView(Result.getServiceError());
            }
        }
    }

    @Override
    protected Object getDeleteView(boolean success, Throwable throwable,
            Long[] ids, ModelAndView model) {
        boolean bIsAsyn = this.isAjaxRequest(RequestContextHolder.getRequest());
        if(!bIsAsyn){
            return super.getDeleteView(success, throwable, ids, model);
        }else{
            if(true == success){
                   return new JSONModelAndView(Result.getSuccessForNoData());
            }else{
                return new JSONModelAndView(Result.getServiceError());
            }
        }
    }
    /**
     * 判断当前请求是否为ajax请求
     * @param request
     * @return
     * @date: 2014-5-27 下午7:05:32
     * @version: 1.00.00
     * @history:
     */
    public boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
        return isAjax;
    } 

    /**
     * 字符串转List返回
     * @param str
     * @param split
     * @return
     * @date: 2014-4-22 下午1:39:39
     * @version: 1.00.00
     * @history:
     */
    protected List<Object> toList(String str, String split){
        List<Object> resList = new ArrayList<Object>();
        if(StringUtils.isBlank(str)){
            return resList;
        }
        if(StringUtils.isBlank(split)){
            split = ";";
        }
        if(str.indexOf(split) == -1){
            resList.add(str);
            return resList;
        }
        String[] objs = str.split(split);
        for (int i = 0; i < objs.length; i++) {
            if(StringUtils.isBlank(objs[i]))   continue;
            resList.add(objs[i]);
        }
        return resList;
    }

}
