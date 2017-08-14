package com.ffcs.icity.mvc.asyn.constant;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ffcs.icity.mybatis.Page;

public class Result {
    
    private int status;
    
    private String desc;
    
    private Object entity;
    
    private List<Object> dataList;
    
    private Page<Object> page;
    
    
    public static Result getSuccessForData(Object entity){
        Result res = new Result();
        res.setStatus(BaseConstant.SUCCESS_FOR_DATA);
        res.setDesc(BaseConstant.SUCCESS_FOR_DATA_DESC);
        res.setEntity(entity);
        return res;
    }
    
    public static Result getSuccessForData(List<Object> dataList){
        Result res = new Result();
        res.setStatus(BaseConstant.SUCCESS_FOR_DATA);
        res.setDesc(BaseConstant.SUCCESS_FOR_DATA_DESC);
        res.setDataList(dataList);
        return res;
    }
    
    public static Result getSuccessForData(Page<Object> page,Object entity){
        Result res = new Result();
        res.setStatus(BaseConstant.SUCCESS_FOR_DATA);
        res.setDesc(BaseConstant.SUCCESS_FOR_DATA_DESC);
        res.setEntity(entity);
        res.setPage(page);
        return res;
    }
    
    public static Result getSuccessForData(Page<Object> page){
        Result res = new Result();
        res.setStatus(BaseConstant.SUCCESS_FOR_DATA);
        res.setDesc(BaseConstant.SUCCESS_FOR_DATA_DESC);
        res.setPage(page);
        return res;
    }
    
    public static Result getSuccessForNoData(){
        Result res = new Result();
        res.setStatus(BaseConstant.SUCCESS_FOR_NODATA);
        res.setDesc(BaseConstant.SUCCESS_FOR_NODATA_DESC);
        return res;
    }
    
    public static Result getServiceError(){
        Result res = new Result();
        res.setStatus(BaseConstant.SERVICE_EXCEPTION);
        res.setDesc(BaseConstant.SERVICE_EXCEPTION_DESC);
        return res;
    }
    
    public static Result getServerError(String desc){
        Result res = new Result();
        res.setStatus(BaseConstant.SERVER_EXCEPTION);
        res.setDesc(desc);
        return res;
    }
    
    public static Result getParamError(){
        Result res = new Result();
        res.setStatus(BaseConstant.PARAM_ERROR);
        res.setDesc(BaseConstant.PARAM_ERROR_DESC);
        return res;
    }
    
    public static void returnResult(HttpServletResponse response, Result res) throws IOException{
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(JSONObject.fromObject(res).toString());
    }
    
    public static void returnJSON(HttpServletResponse response, JSONObject resJson) throws IOException{
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(resJson.toString());
    }
    
    public static void returnBoolean(HttpServletResponse response, boolean value) throws IOException{
    	response.setContentType("text/html; charset=utf-8");
    	response.getWriter().write(value + "");
    }
    

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public Page<Object> getPage() {
        return page;
    }

    public void setPage(Page<Object> page) {
        this.page = page;
    }
    
    

}
