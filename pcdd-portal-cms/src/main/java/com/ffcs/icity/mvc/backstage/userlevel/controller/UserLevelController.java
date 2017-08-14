package com.ffcs.icity.mvc.backstage.userlevel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.userlevel.dao.UserLevelDao;
import com.ffcs.icity.mvc.backstage.userlevel.entity.UserLevel;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/userLevel")
@Controller
public class UserLevelController extends AsynController<UserLevel>{

	private UserLevelDao userLevelDao;

	@Override
	protected CRUDDao<UserLevel> getDao() {
		return this.userLevelDao;
	}

	@Autowired
	public void setUserLevelDao(UserLevelDao userLevelDao) {
		this.userLevelDao = userLevelDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/userLevel";
	}
	
	@ResponseBody
	@RequestMapping("/getUserLevels")
	public Object getUserLevels(HttpServletRequest request,HttpServletResponse response){
		List<UserLevel> userLevels = null;
		try {
			
			userLevels = userLevelDao.query(null, null);
			if(CollectionUtils.isEmpty(userLevels)){
				return Result.getSuccessForNoData();
			}
			return Result.getSuccessForData(userLevels);
		} catch (NumberFormatException e) {
			return Result.getParamError();
		}
	}
}

