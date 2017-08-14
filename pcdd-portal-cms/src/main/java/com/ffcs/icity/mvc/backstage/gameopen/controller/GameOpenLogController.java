package com.ffcs.icity.mvc.backstage.gameopen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.BaseConstant;
import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.gameopen.dao.GameOpenLogDao;
import com.ffcs.icity.mvc.backstage.gameopen.entity.GameOpenLog;
import com.ffcs.icity.mvc.common.HttpRequestUtil;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/gameOpenLog")
@Controller
public class GameOpenLogController extends AsynController<GameOpenLog>{
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private int proxyPort = 7777;
	
	private String proxyIp = "192.168.13.19";
	
	@Value("${openGameUrl}")
	private String openGameUrl;
	
	@Value("${isProx:false}")
	private boolean isProx;
	
	private GameOpenLogDao gameOpenLogDao;

	@Override
	protected CRUDDao<GameOpenLog> getDao() {
		return this.gameOpenLogDao;
	}

	@Autowired
	public void setGameOpenLogDao(GameOpenLogDao gameOpenLogDao) {
		this.gameOpenLogDao = gameOpenLogDao;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "backstage/game_open";
	}
	
	@RequestMapping("openGame")
	@ResponseBody
	public Object openGame(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		json.put("open_id", Long.parseLong(request.getParameter("id").toString()));	
		
		String responseText;
		Result res;
		try {
			if(isProx){
				responseText = HttpRequestUtil.post(openGameUrl, json.toString(),proxyIp,proxyPort);
			}else{
				responseText = HttpRequestUtil.post(openGameUrl, json.toString());
			}
			
			log.debug(responseText);
			JSONObject resJson = JSONObject.fromObject(responseText);
			if (resJson.getInt("result_code") == 0) {
				res = Result.getSuccessForNoData();
			} else {
				res = new Result();
				res.setStatus(BaseConstant.SERVICE_EXCEPTION);
				res.setDesc(resJson.getString("result_desc"));
			}
		} catch (IOException e) {
			res = Result.getServerError(e.getMessage());
			log.error(e.getMessage());
		}
		
		return res;
	}
	
	
	/**
	 * 验证期数是否存在
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkGameNum")
	@ResponseBody
	public Object checkGameNum(HttpServletRequest request,HttpServletResponse response){
		try {
			String id = request.getParameter("id");
			String gameType = request.getParameter("gameType");
			String gameNum = request.getParameter("gameNum");
			Map<String,Object> params = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(id)){
				params.put("id", Integer.parseInt(id));
			}
			if (StringUtils.isNotBlank(gameType)){
				params.put("gameType", Integer.parseInt(gameType));
			}
			
			if (StringUtils.isNotBlank(gameNum)){
				params.put("gameNum", Integer.parseInt(gameNum));
			}
			
			
			int count = gameOpenLogDao.checkGameNum(params);
			if (count <= 0) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}
}
