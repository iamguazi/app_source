package com.ffcs.icity.api.module.roomUserInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.roomUserInfo.entity.*;
import com.ffcs.icity.api.module.roomUserInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IRoomUserInfoDao {
	
	public List<RoomUserInfo> findAll();
	
	public List<RoomUserInfo> findByCondition(RoomUserInfo roomUserInfo);
	
	public RoomUserInfo getRoomUserInfoById(int id);
	
	public void insertRoomUserInfo(RoomUserInfo roomUserInfo);
	
	public void updateRoomUserInfo(RoomUserInfo roomUserInfo);
	
	public void deleteRoomUserInfo(long id);
	
	public RoomUserInfo getRoomUserInfoByParams(RoomUserInfo roomUserInfo);
}
