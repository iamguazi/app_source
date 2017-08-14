package com.ffcs.icity.api.module.roomInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.roomInfo.entity.*;
import com.ffcs.icity.api.module.roomInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IRoomInfoDao {
	
	public List<RoomInfo> findAll();
	
	public List<RoomInfo> findByCondition(RoomInfo roomInfo);
	
	public RoomInfo getRoomInfoById(int id);
	
	public void insertRoomInfo(RoomInfo roomInfo);
	
	public void updateRoomInfo(RoomInfo roomInfo);
	
	public void deleteRoomInfo(long id);
}
