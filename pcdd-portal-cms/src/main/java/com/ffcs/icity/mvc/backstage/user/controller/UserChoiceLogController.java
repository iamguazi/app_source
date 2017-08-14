package com.ffcs.icity.mvc.backstage.user.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.datetime.JDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.constants.DBConstants;
import com.ffcs.icity.mvc.backstage.user.dao.UserChoiceLogDao;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.user.entity.UserChoiceLog;
import com.ffcs.icity.mvc.backstage.user.entity.UserChoiceLogXls;
import com.ffcs.icity.mvc.common.XLSExportWrap;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mvc.support.RequestContextHolder;
import com.ffcs.icity.mvc.view.json.JSONModelAndView;
import com.ffcs.icity.mybatis.Page;


@RequestMapping("backstage/userChoiceLog")
@Controller
public class UserChoiceLogController extends AsynController<UserChoiceLog>{

	@Autowired
	private UserInfoDao userInfoDao;
	
	private UserChoiceLogDao userChoiceLogDao;

	@Override
	protected CRUDDao<UserChoiceLog> getDao() {
		return this.userChoiceLogDao;
	}

	@Autowired
	public void setUserChoiceLogDao(UserChoiceLogDao userChoiceLogDao) {
		this.userChoiceLogDao = userChoiceLogDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/user_choice";
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
                    	Result res = Result.getSuccessForNoData();
                    	res.setEntity(0);
                    	return new JSONModelAndView(res);
                    } else {
                    	Result res = Result.getSuccessForData(page);
                    	res.setEntity(userChoiceLogDao.getTotalXhibitPoint(queryParams));
                        return new JSONModelAndView(res);
                    }
                } catch (Exception e) {
                    return new JSONModelAndView(Result.getSuccessForNoData());
                }
            }else{
                return new JSONModelAndView(Result.getServiceError());
            }
        }
    }
    
	@RequestMapping("exportExcel")
    public void exposeExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Page<UserChoiceLog> page = new Page<UserChoiceLog>(extractPageNo(request), extractPageSize(request));
    	Map<String,Object> queryParams = new HashMap<String,Object>();
    	JDateTime dateTime = new JDateTime();
    	queryParams.put("createTimeEnd", dateTime.toString("YYYY-MM-DD"));
    	dateTime.subDay(7);
    	queryParams.put("createTimeBegin", dateTime.toString("YYYY-MM-DD"));
    	JDateTime formatDate = new JDateTime();
    	List<UserChoiceLog> list = userChoiceLogDao.query(queryParams, page);
    	List<UserChoiceLogXls> outputList = new ArrayList<UserChoiceLogXls>();
    	DecimalFormat format = new DecimalFormat("#.00");
    	UserChoiceLogXls userChoiceLogXls = null;
    	StringBuilder sb = new StringBuilder("");
    	for(UserChoiceLog userChoiceLog : list){
    		userChoiceLogXls = new UserChoiceLogXls();
    		userChoiceLogXls.setUserId(userChoiceLog.getUserId().toString());
    		userChoiceLogXls.setAccount(userChoiceLog.getAccount());
    		userChoiceLogXls.setNickName(userChoiceLog.getNickName());
    		userChoiceLogXls.setUserPhoto(userChoiceLog.getUserPhoto());
    		userChoiceLogXls.setGameType(DBConstants.GAME_TYPE[userChoiceLog.getGameType()]);
    		userChoiceLogXls.setAreaName(userChoiceLog.getAreaName());
    		userChoiceLogXls.setRoomName(userChoiceLog.getRoomName());
    		userChoiceLogXls.setPoint(format.format(userChoiceLog.getPoint()));
    		userChoiceLogXls.setChoiceName(userChoiceLog.getChoiceName());
    		userChoiceLogXls.setChoiceNo(userChoiceLog.toString());
    		if(userChoiceLog.getIsZhong() == -1){
    			userChoiceLogXls.setResultType("未中奖");
			}else{
				sb.setLength(0);
				sb.append(userChoiceLog.getRealResult()).append(" ").append(userChoiceLog.getResultType());
				userChoiceLogXls.setResultType(sb.toString());
			}
    		userChoiceLogXls.setChoiceNo(userChoiceLog.getChoiceNo());
    		userChoiceLogXls.setXhibitPoint(format.format(userChoiceLog.getXhibitPoint()));
    		formatDate.setDateTime(userChoiceLog.getCreateTime());
    		userChoiceLogXls.setCreateTime(formatDate.toString("YYYY-MM-DD hh:mm"));
    		outputList.add(userChoiceLogXls);
    	}
    	
    	
    	
    	XLSExportWrap<UserChoiceLogXls> ex = new XLSExportWrap<UserChoiceLogXls>();
    	
    	String path = request.getSession().getServletContext().getRealPath(File.separator) + "temp" + File.separatorChar + 
                "expose_" + System.currentTimeMillis() + ".xls";
        File f = new File(path);
        if(!f.exists()){
        	f.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(f);
        ex.exportExcel("下注记录数据", new String[]{"用户ID","账号", "昵称","头像","游戏类型","房间类型","房间名称","下注金额","下注类型","开奖结果","期数","盈亏金额","下注时间"}, 
        		new String[]{"userId","account","nickName","userPhoto","gameType","areaName","roomName", "point","choiceName","resultType","choiceNo","xhibitPoint","createTime"}, outputList, os);
        os.flush();
        os.close();
        XLSExportWrap.downloadXls(path,request, response);
    }
}
