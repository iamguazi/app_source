package com.web.act.mapper;

import com.web.act.dao.*;
import com.web.act.vo.*;
import java.util.List;


public interface GameOpenLogMapper {
	
	public List<GameOpenLog> findAll();
	
	public List<GameOpenLog> findByCondition(GameOpenLog gameOpenLog);
	
	public GameOpenLog getGameOpenLogById(int id);
	
	public void insertGameOpenLog(GameOpenLog gameOpenLog);
	
	public void updateGameOpenLog(GameOpenLog gameOpenLog);
	
	public void deleteGameOpenLog(long id);
	
	//最近一期开奖结果
	public GameOpenLog getGameOpenLogByLast(GameOpenLog gameOpenLog);
}
