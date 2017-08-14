package com.ffcs.icity.api.module.gameArea.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.gameArea.entity.*;
import com.ffcs.icity.api.module.gameArea.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IGameAreaDao {
	
	public List<GameArea> findAll();
	
	public List<GameArea> findByCondition(GameArea gameArea);
	
	public GameArea getGameAreaById(int id);
	
	public void insertGameArea(GameArea gameArea);
	
	public void updateGameArea(GameArea gameArea);
	
	public void deleteGameArea(long id);
}
