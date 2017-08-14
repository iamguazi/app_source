package com.ffcs.icity.mvc.backstage.manager.dao;

import java.util.List;
import java.util.Map;

import com.ffcs.icity.mvc.backstage.manager.entity.Manager;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ManagerDao extends CRUDDao<Manager> {
	
	public List<Manager> getManagerByNameAndPwd(Map<String, Object> paramsMap);
	
	public int getManagerNameCount(Map<String,Object> params);
	
	public void updateStatus(Map<String,Object> params);
	
	public void updateLoginTime(Manager manger);
}
