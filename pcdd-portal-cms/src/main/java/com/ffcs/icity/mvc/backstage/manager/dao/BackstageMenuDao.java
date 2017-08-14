package com.ffcs.icity.mvc.backstage.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.backstage.manager.entity.BackstageMenu;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BackstageMenuDao extends CRUDDao<BackstageMenu> {
	public List<BackstageMenu>  getPermissions(@Param("id") Long id);
}