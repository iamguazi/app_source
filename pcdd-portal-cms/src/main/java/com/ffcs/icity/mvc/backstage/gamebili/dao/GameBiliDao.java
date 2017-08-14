package com.ffcs.icity.mvc.backstage.gamebili.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.gamebili.entity.GameBili;

@MyBatisRepository
public interface GameBiliDao extends CRUDDao<GameBili> {
	
}