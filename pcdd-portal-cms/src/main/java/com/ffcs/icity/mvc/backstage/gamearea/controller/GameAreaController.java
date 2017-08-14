package com.ffcs.icity.mvc.backstage.gamearea.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.gamearea.dao.GameAreaDao;
import com.ffcs.icity.mvc.backstage.gamearea.entity.GameArea;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/gameArea")
@Controller
public class GameAreaController extends AsynController<GameArea>{

	private GameAreaDao gameAreaDao;

	@Override
	protected CRUDDao<GameArea> getDao() {
		return this.gameAreaDao;
	}

	@Autowired
	public void setGameAreaDao(GameAreaDao gameAreaDao) {
		this.gameAreaDao = gameAreaDao;
	}
	
	
	@Override
	protected String getEntityName() {
		return "backstage/game_area";
	}
	
	@ResponseBody
	@RequestMapping("/getAreaTypes")
	public Object getAreaTypes(HttpServletRequest request,HttpServletResponse response){
		List<GameArea> areaTypes = null;
		try {
			String gameType = request.getParameter("gameType");
			Map<String,Object> params = new HashMap<String,Object>();
			if(StringUtils.isNotBlank(gameType)){
				params.put("gameType", Integer.parseInt(gameType));
			}
			params.put("status", 1);
			areaTypes = gameAreaDao.query(params, null);
			if(CollectionUtils.isEmpty(areaTypes)){
				return Result.getSuccessForNoData();
			}
			return Result.getSuccessForData(areaTypes);
		} catch (NumberFormatException e) {
			return Result.getParamError();
		}
		
	}
}
