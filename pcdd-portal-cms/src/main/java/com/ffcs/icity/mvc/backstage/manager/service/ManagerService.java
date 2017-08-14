package com.ffcs.icity.mvc.backstage.manager.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.manager.dao.BackstageMenuDao;
import com.ffcs.icity.mvc.backstage.manager.dao.ManagerMenuDao;
import com.ffcs.icity.mvc.backstage.manager.dao.ManagerDao;
import com.ffcs.icity.mvc.backstage.manager.entity.Manager;

@Transactional(rollbackFor = {Exception.class})
@Service
public class ManagerService {
	
	@Autowired
	private BackstageMenuDao backstageMenuDao;
	
	@Autowired
	private ManagerMenuDao managerMenuDao;
	
	@Autowired
	private ManagerDao managerDao;
	
	
	public void saveService(Manager manager,String menuIds) throws Exception{
		managerDao.insert(manager);
		if(StringUtils.isNotBlank(menuIds)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("menuIds",parseMenuIdsToInt(menuIds));
			params.put("managerId",manager.getId());
			managerMenuDao.batchInsert(params);
		}
	}

	private int[] parseMenuIdsToInt(String menuIds){
		String[] menuIdGroup = menuIds.split(",");
		int[] intMenuIds = new int[menuIdGroup.length];
		for(int i=0,len=menuIdGroup.length;i<len;i++){
			intMenuIds[i]=Integer.parseInt(menuIdGroup[i]);
		}
		return intMenuIds;
	}       
	
	public void updateService(Manager manager,String menuIds) throws Exception{
		managerDao.update(manager);
		//删除管理员权限
		managerMenuDao.deleteByManagerIds(new Long[]{manager.getId()});
		
		if(StringUtils.isNotBlank(menuIds)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("menuIds",parseMenuIdsToInt(menuIds));
			params.put("managerId",manager.getId());
			managerMenuDao.batchInsert(params);
		}
	}
	
	
	public void deleteService(Long[] ids) throws Exception{
		managerDao.deletes(ids);
		managerMenuDao.deleteByManagerIds(ids);
	}
}
