package com.ffcs.icity.mvc.backstage.manager.controller;


import static com.ffcs.icity.mvc.utils.RequestUtils.resolveParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.manager.dao.BackstageMenuDao;
import com.ffcs.icity.mvc.backstage.manager.dao.ManagerDao;
import com.ffcs.icity.mvc.backstage.manager.entity.BackstageMenu;
import com.ffcs.icity.mvc.backstage.manager.entity.Manager;
import com.ffcs.icity.mvc.backstage.manager.service.ManagerService;
import com.ffcs.icity.mvc.common.SessionUtil;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mvc.entity.IdEntity;
import com.ffcs.icity.mybatis.Page;


@RequestMapping("backstage/manager")
@Controller
public class ManagerController extends AsynController<Manager>{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	
	@Autowired
	private BackstageMenuDao backstageMenuDao;
	
	@Autowired
	private ManagerService managerService; 
	
	@Override
	protected String getEntityName() {
		return "backstage/manager";
	}

	@Autowired
	private ManagerDao managerDao;

	@Override
	protected CRUDDao<Manager> getDao() {
		return  this.managerDao;
	}
	
	@Autowired
	public void setSManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	protected Page<Manager> getPage(Map<String, Object> params, HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<Manager> page = new Page<Manager>(pageNo, pageSize);
		CRUDDao<Manager> dao = getDao();
		Manager manager = SessionUtil.getManager(req);
		params.put("id",manager.getId());
		if (!"super".equals(manager.getType())){
			params.put("parentId", manager.getId());
		}
		dao.query(params, page);
		return page;
	}
	
	protected Object doSave(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			Manager entity = extractEntity(req);
			
			verifyEntity(entity, model);
			Manager manager = SessionUtil.getManager(req);
			entity.setParentId(manager.getId());
			if (((IdEntity) entity).getId() == null) {
				managerService.saveService(entity,entity.getMenuIds());
			} else {
				managerService.updateService(entity,entity.getMenuIds());
			}
			view = getSaveView(true, null, entity, model);
		} catch (Throwable throwable) {

			logger.error("failure to save in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getSaveView(false, throwable, null, model);
		}
		return view;
	}
	
	@Override
	protected Object doDelete(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			String[] origIds = req.getParameterValues("ids");
			Long[] ids = null;
			if (!ArrayUtils.isEmpty(origIds)) {
				ids = toLongArray(origIds);
				managerService.deleteService(ids);
			}
			view = getDeleteView(true, null, ids, model);
		} catch (Throwable throwable) {

			logger.error("failure to delete in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getDeleteView(false, throwable, null, model);
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/checkIsExist")
	public boolean checkIsExist(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id",id);
		params.put("name",name);
		
		return managerDao.getManagerNameCount(params) > 0 ?false:true;
	}
	
	@ResponseBody
	@RequestMapping("/getPermissions")
	public List<BackstageMenu> getManagerMenu(HttpServletRequest req, HttpServletResponse resp){
		Manager manager = (Manager)req.getSession().getAttribute("manager");
		if("super".equals(manager.getType())){
			return backstageMenuDao.query(new HashMap<String,Object>(),null);
		}else{
			return backstageMenuDao.getPermissions(manager.getId());
		}
	}
	
	@ResponseBody
	@RequestMapping("/getAllPermissions")
	public List<BackstageMenu> getAllPermissions(HttpServletRequest req, HttpServletResponse resp){
		return backstageMenuDao.query(new HashMap<String,Object>(),null);
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest req, HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("ids"));
		int status = Integer.parseInt(req.getParameter("status"));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("status", status);
		managerDao.updateStatus(params);
		return true;
	}
	
	@RequestMapping("resetPassword")
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	Result res = new Result();
    	
    	Manager manager = SessionUtil.getManager(request);
    	
    	if (manager != null) {
    		manager = managerDao.get(manager.getId());
    		if (oldPassword.equals(manager.getPasswd())) {
    			manager.setPasswd(newPassword);
    			managerDao.update(manager);
    			SessionUtil.setManager(request, manager);
//    			AdminInfoHolder.add(token, admin);
    			res.setStatus(1);
    			res.setDesc("修改密码成功!");
    			
    		} else {
    			res.setStatus(0);
    			res.setDesc("原密码错误！");
    		}
    	} else {
    		res.setStatus(0);
    		res.setDesc("帐号验证失败，无法修改密码！");
    	}
    	Result.returnResult(response, res);
    }
}
