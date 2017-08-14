package com.ffcs.icity.mvc.backstage.gamearea.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.gamearea.entity.GameArea;

@MyBatisRepository
public interface GameAreaDao extends CRUDDao<GameArea> {
	
}