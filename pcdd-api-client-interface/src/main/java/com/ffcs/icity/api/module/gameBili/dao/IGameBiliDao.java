package com.ffcs.icity.api.module.gameBili.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.gameBili.entity.*;
import com.ffcs.icity.api.module.gameBili.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IGameBiliDao {
	
	public List<GameBili> findAll();
	
	public List<GameBili> findByCondition(GameBili gameBili);
	
	public GameBili getGameBiliById(int id);
	
	public void insertGameBili(GameBili gameBili);
	
	public void updateGameBili(GameBili gameBili);
	
	public void deleteGameBili(long id);
	
	public GameBili getGameBiliByAreaId(int areaId);
}
