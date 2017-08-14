package com.ffcs.icity.mvc.backstage.statistics.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffcs.icity.mvc.asyn.constant.BaseConstant;
import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.backstage.constants.DBConstants;
import com.ffcs.icity.mvc.backstage.statistics.dao.PreviewFinancialStatisticsDao;
import com.ffcs.icity.mvc.backstage.statistics.entity.PreviewFinancialStatisticsXls;
import com.ffcs.icity.mvc.common.XLSExportWrap;
import com.ffcs.icity.mvc.utils.RequestUtils;


@RequestMapping("backstage/previewFinancialStatistics")
@Controller
public class PreviewFinancialStatisticsController {
	
	private final int defaultShowDay = 10; //默认显示的数据
	
	private final int defaultIntervalDay = defaultShowDay-1; //间隔9天 即获取10天数据
	
	@Autowired
	private PreviewFinancialStatisticsDao previewfinancialStatisticsDao;
	
	
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "backstage/statistics/preview/list";
	} 
	
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response){
		try{
			Map<String,Object> params = RequestUtils.resolveParams(request);
			String createTimeBegin = null,createTimeEnd = null;;
			if (params.containsKey("createTimeBegin")) {
				createTimeBegin = params.get("createTimeBegin").toString();
			}
			if (params.containsKey("createTimeEnd")){
				createTimeEnd = params.get("createTimeEnd").toString();
			}
			
			String[] startTimeAndEndTime  = getSearchStartTimeAndEndTime(createTimeBegin,createTimeEnd);
			String startTime = startTimeAndEndTime[0], endTime = startTimeAndEndTime[1];
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");  
			int intervalDays = Days.daysBetween(DateTime.parse(startTime, format),DateTime.parse(endTime, format)).getDays(); 
			
			List<String> searchDateList =  createSearchDateList(endTime, intervalDays, null);
			List<Map<String,Object>> results = new ArrayList<Map<String,Object>>(); 
			Map<String,Object> result = null;
			for(String searchTime : searchDateList){
				params.put("searchTime", searchTime);
				result = previewfinancialStatisticsDao.queryForMap(params);
				result.put("time",searchTime);
				results.add(result);
			}
			
			Map<String,Object> resultMap  = new HashMap<String,Object>();
			resultMap.put("status",BaseConstant.SUCCESS_FOR_DATA);
			resultMap.put("desc",BaseConstant.SUCCESS_FOR_DATA_DESC);
			resultMap.put("dataList",results);
			return resultMap; 
		}catch(Exception e){
			return Result.getServerError(e.getLocalizedMessage());
		}
	}

	
	private String[] getSearchStartTimeAndEndTime(String createTimeBegin, String createTimeEnd){
		String[] result = new String[2];
		Date date = new Date();
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");  
		result[0] = new DateTime(date).minusDays(defaultIntervalDay).toString("yyyy-MM-dd");
		result[1] = new DateTime(date).toString(format);  
        
		if (StringUtils.isBlank(createTimeBegin)  && StringUtils.isBlank(createTimeEnd)) {
			return result;
		} 
		
		if (StringUtils.isNotBlank(createTimeBegin)  && StringUtils.isNotBlank(createTimeEnd)) {
			
			result[0] = createTimeBegin;
//			DateTime end = DateTime.parse(createTimeEnd, format); 
//			DateTime now = DateTime.parse(new DateTime(date).toString(format),format);
//			if (end.getMillis() > now.getMillis()){
//				result[1] = now.toString(format);
//			}else{
			result[1] = createTimeEnd;
//			}
			
			return result;
			
		} 
		
		if (StringUtils.isNotBlank(createTimeBegin)) {
			//时间解析  
	        DateTime begin = DateTime.parse(createTimeBegin, format); 
	        result[0] = createTimeBegin;
	        result[1] = begin.plusDays(defaultIntervalDay).toString(format);
			return result;
		}
		
		
		if (StringUtils.isNotBlank(createTimeEnd)) {
			//时间解析  
	        DateTime end = DateTime.parse(createTimeEnd, format); 
	        
	        result[0] = end.minusDays(defaultIntervalDay).toString(format);
	        result[1] = createTimeEnd;
	        return result;
		}
		
		return result;
	}
	
	private List<String> createSearchDateList(String createTimeEndStr, int intervalDay,String orderByType){
		List<String> descDateList = new ArrayList<String>(10);
		descDateList.add(createTimeEndStr);
		if (intervalDay <=0 ){
			return descDateList;
		
		}
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");  
		DateTime createTimeEnd = DateTime.parse(createTimeEndStr, format); 
		
		if (StringUtils.isNotBlank(orderByType)  && "asc".equalsIgnoreCase(orderByType)){
			for (int i = 0; i < intervalDay ; i++) {
				descDateList.add(createTimeEnd.plusDays((i+1)).toString(format));
			}
		} else {
			for (int i = 0; i < intervalDay ; i++) {
				descDateList.add(createTimeEnd.minusDays((i+1)).toString(format));
			}
		}
		
		return descDateList;
	}
	
	@RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String exportDataStr = request.getParameter("exportData");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, PreviewFinancialStatisticsXls.class);
		List<PreviewFinancialStatisticsXls> exportData = mapper.readValue(exportDataStr, javaType);
    	
		XLSExportWrap<PreviewFinancialStatisticsXls> ex = new XLSExportWrap<PreviewFinancialStatisticsXls>();
    	
    	String path = request.getSession().getServletContext().getRealPath(File.separator) + "temp" + File.separatorChar + 
                "expose_" + System.currentTimeMillis() + ".xls";
        File f = new File(path);
        if(!f.exists()){
        	f.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(f);
        String[] title = new String[DBConstants.GAME_TYPE.length-1+5];
        title[0] = "时间";
        title[1] = "新用户";
        for(int i=1,len = DBConstants.GAME_TYPE.length; i<len; i++){
        	title[i+1] = DBConstants.GAME_TYPE[i];
        }
        title[DBConstants.GAME_TYPE.length-1+2+1] = "回水";
        title[DBConstants.GAME_TYPE.length-1+2+2] = "提现";
        title[DBConstants.GAME_TYPE.length-1+2+3] = "充值";
        
        
        String[] key = new String[DBConstants.GAME_TYPE.length-1+5];
        key[0] = "time";
        key[1] = "newUser";
        for(int i=1,len = DBConstants.GAME_TYPE.length; i<len; i++){
        	key[i+1] = "gameType"+i;
        }
        
        key[DBConstants.GAME_TYPE.length-1+2+1] = "backWater";
        key[DBConstants.GAME_TYPE.length-1+2+2] = "withdrawalFee";
        key[DBConstants.GAME_TYPE.length-1+2+3] = "rechargeFee";
        
        ex.exportExcel("统计预览", title, 
        		key, exportData, os);
        os.flush();
        os.close();
        XLSExportWrap.downloadXls(path,request, response);
    }
    
}
