package com.ffcs.icity.api.module.pointChangeLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.pointChangeLog.entity.*;
import com.ffcs.icity.api.module.pointChangeLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IPointChangeLogDao {
	
	public List<PointChangeLog> findAll();
	
	public List<PointChangeLog> findByCondition(PointChangeLog pointChangeLog);
	
	public PointChangeLog getPointChangeLogById(int id);
	
	public void insertPointChangeLog(PointChangeLog pointChangeLog);
	
	public void updatePointChangeLog(PointChangeLog pointChangeLog);
	
	public void deletePointChangeLog(long id);
}
