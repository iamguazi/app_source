package com.ffcs.icity.api.module.gameOpenLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.gameOpenLog.entity.*;
import com.ffcs.icity.api.module.gameOpenLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IGameOpenLogDao {
	
	public List<GameOpenLog> findAll();
	
	public List<GameOpenLog> findByCondition(GameOpenLog gameOpenLog);
	
	public GameOpenLog getGameOpenLogById(int id);
	
	public void insertGameOpenLog(GameOpenLog gameOpenLog);
	
	public void updateGameOpenLog(GameOpenLog gameOpenLog);
	
	public void deleteGameOpenLog(long id);
	
	public GameOpenLog getGameOpenLogByNum(GameOpenLog gameOpenLog);
	
	//最近一期开奖结果
	public GameOpenLog getGameOpenLogByLast(GameOpenLog gameOpenLog);
	
	
}
