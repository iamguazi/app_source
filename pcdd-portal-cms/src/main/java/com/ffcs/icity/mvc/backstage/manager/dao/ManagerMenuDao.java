package com.ffcs.icity.mvc.backstage.manager.dao;

import java.util.Map;

import com.ffcs.icity.mvc.backstage.manager.entity.ManagerMenu;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ManagerMenuDao extends CRUDDao<ManagerMenu> {
	public void deleteByManagerIds(Long[] ids);
	public void batchInsert(Map<String,Object> params);
}