package com.ffcs.icity.mvc.backstage.user.controller;

import static com.ffcs.icity.mvc.utils.RequestUtils.resolveParams;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.user.entity.UserInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;

@RequestMapping("backstage/userBankInfos")
@Controller
public class UserBankController extends AsynController<UserInfo>{
	
	private static final Logger logger = LoggerFactory.getLogger(UserBankController.class);
	
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
		return "backstage/user_bank";
	}

	@Value("${imgShowRoot}")
    private String imgShowRoot;         // 图片显示的服务端根路径
    
	
	@Override
	public String toIndex(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("imgShowRoot", imgShowRoot);
		request.setAttribute("userId", request.getParameter("userId"));
		
        return super.getFullViewName(LIST_VIEW_NAME); 
	}
	
	protected Page<UserInfo> getPage(Map<String, Object> params, HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
		userInfoDao.queryUserBankInfos(params, page);
		return page;
	}
	
	protected Object doSave(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			UserInfo entity = extractEntity(req);
			
			verifyEntity(entity, model);
			
			userInfoDao.updateUserBankInfos(entity);
			
			view = getSaveView(true, null, entity, model);
		} catch (Throwable throwable) {

			logger.error("failure to save in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getSaveView(false, throwable, null, model);
		}
		return view;
	}
	
	protected Object doDelete(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			String[] origIds = req.getParameterValues("ids");
			Long[] ids = null;
			if (!ArrayUtils.isEmpty(origIds)) {
				ids = toLongArray(origIds);
				userInfoDao.batchCleanUserBankInfos(ids);
			}
			view = getDeleteView(true, null, ids, model);
		} catch (Throwable throwable) {

			logger.error("failure to delete in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getDeleteView(false, throwable, null, model);
		}
		return view;
	}
}
