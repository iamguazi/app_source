package com.ffcs.icity.mvc.backstage.user.controller;

import static com.ffcs.icity.mvc.utils.RequestUtils.resolveParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.datetime.JDateTime;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.user.entity.UserInfo;
import com.ffcs.icity.mvc.backstage.user.service.UserInfoService;
import com.ffcs.icity.mvc.backstage.user.service.UserRechargeService;
import com.ffcs.icity.mvc.common.CodingUtil;
import com.ffcs.icity.mvc.common.HttpRequestUtil;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;

@RequestMapping("backstage/userInfo")
@Controller
public class UserInfoController extends AsynController<UserInfo>{

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserRechargeService userRechargeService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Value("${shieldingUrl}")
	private String shieldingUrl;
	
	private UserInfoDao userInfoDao;

	@Override
	protected CRUDDao<UserInfo> getDao() {
		return this.userInfoDao;
	}

	@Autowired
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/user";
	}

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
    	request.setAttribute("code", request.getParameter("parentId"));
    	request.setAttribute("account",request.getParameter("account"));
    	Object nickName = request.getParameter("nickName");
    	request.setAttribute("nickName",nickName);
    	
    	if (nickName != null){
    		try {
				request.setAttribute("isoNickName",CodingUtil.changeIso2Utf(nickName.toString()));
			} catch (UnsupportedEncodingException e) {
			}
    	}
    	request.setAttribute("mobile",request.getParameter("mobile"));
    	request.setAttribute("imgShowRoot", imgShowRoot);
        return super.getFullViewName(super.LIST_VIEW_NAME); 
    }
    
    @Override
    protected Object doQuery(HttpServletRequest req, HttpServletResponse resp,
    		ModelAndView model) {
    	Object view = null;
		try {
			Map<String, Object> queryParams = extractQueryParams(req);
			if( queryParams.containsKey("searchByTime") && StringUtils.isNotBlank(queryParams.get("searchByTime").toString())){
				String searchByTime = queryParams.get("searchByTime").toString();
				setSearchByTime(queryParams,searchByTime);
			}
			
			Page<UserInfo> page = getPage(queryParams, req);
			model.addObject("page", page);
			view = getQueryView(true, null, queryParams, model);
		} catch (Throwable throwable) {

			logger.error("failure to query in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getQueryView(false, throwable, null, model);
		}
		return view;
    }
    
    private String setSearchByTime(Map<String,Object> params, String type){
    	JDateTime jDateTime = new JDateTime();
    	params.put("createTimeEnd", jDateTime.toString("YYYY-MM-DD"));
    	if ("yesterday".equals(type)){
    		jDateTime.addDay(-1);
    		params.put("createTimeEnd", jDateTime.toString("YYYY-MM-DD"));
    	} else if ("this_week".equals(type)){
    		jDateTime.subDay(jDateTime.getDayOfWeek()-1);
    	} else if ("this_month".equals(type)){
    		jDateTime.subDay(jDateTime.getDayOfMonth()-1);
    	}
    	params.put("createTimeBegin", jDateTime.toString("YYYY-MM-DD"));
    	return jDateTime.toString("YYYY-MM-DD");
    }
    
	protected Object doSave(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			UserInfo entity = extractEntity(req);
			
			verifyEntity(entity, model);

			boolean isSuccess = userInfoService.saveOrUpdate(entity);
			
			view = getSaveView(isSuccess, null, entity, model);
			
		} catch (Throwable throwable) {

			logger.error("failure to save in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getSaveView(false, throwable, null, model);
		}
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("ids");
		if(ids.length <= 0 ){
			return false;
		}
		String status = request.getParameter("status");
		String responseText = "";
	    JSONObject json = new JSONObject();
	    json.put("id", Long.parseLong(ids[0]));
	    json.put("status", Long.parseLong(status));
	    try {
	    	responseText = HttpRequestUtil.post(shieldingUrl, json.toString());
	    	logger.debug(responseText);
			JSONObject resJson = JSONObject.fromObject(responseText);
			if (resJson.getInt("result_code") == 0) {
				return  true;
			} else {
				return false;
			}
	    } catch (IOException e) {
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("/rechargePoint")
	public Object rechargePoint(HttpServletRequest request,HttpServletResponse response){
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Double rechargePoint = Double.parseDouble(request.getParameter("rechargePoint"));
		try {
			String remark = request.getParameter("remark");
			userRechargeService.recharge(userId, rechargePoint, remark);
			return Result.getSuccessForNoData();
		} catch (Exception e) {
			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.getServerError(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("/modifyParentId")
	public Object modifyParentId(HttpServletRequest request,HttpServletResponse response){
		try {
			Integer parentId = Integer.parseInt(request.getParameter("parentId"));
			
			Integer userId = Integer.parseInt(request.getParameter("userId"));

			if(userId.intValue() == parentId.intValue()){
				return Result.getServerError("不能绑定自己的ID");
			}
			
			if(userInfoDao.get(parentId.longValue()) == null){
				return Result.getServerError("要绑定的ID不存在");
			}
			
			//判断该用户是否有下级
			if(userInfoDao.countUserChild(userId,parentId) > 0){
				return Result.getServerError("不能绑定您的下级");
			}
			
			userInfoDao.updateCodeById(userId, parentId);
			
			
			
			return Result.getSuccessForNoData();
		} catch (Exception e) {
			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.getServerError(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping("/resetPassword")
	public Object resetPassword(HttpServletRequest request,HttpServletResponse response){
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			userInfoService.resetPassword(id);
			return Result.getSuccessForNoData();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServiceError();
		}
	}
	
	@RequestMapping("isAccountExits")
	@ResponseBody
	public Object isAccountExits(HttpServletRequest request,HttpServletResponse response){
		String account = request.getParameter("account");
    	String id = request.getParameter("id");
    	boolean isExits = userInfoService.isAccountExits(account, StringUtils.isBlank(id) ? null : Long.parseLong(id));
    	return isExits;
	}
	
	public static void main(String[] args) {
		JDateTime jd = new JDateTime();
		jd.subDay(7);
		System.out.println(jd.toString());
	}
}
