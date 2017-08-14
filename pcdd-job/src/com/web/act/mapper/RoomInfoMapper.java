package com.web.act.mapper;

import com.web.act.dao.*;
import com.web.act.vo.*;
import java.util.List;


public interface RoomInfoMapper {
	
	public List<RoomInfo> findAll();
	
	public List<RoomInfo> findByCondition(RoomInfo roomInfo);
	
	public RoomInfo getRoomInfoById(int id);
	
	public void insertRoomInfo(RoomInfo roomInfo);
	
	public void updateRoomInfo(RoomInfo roomInfo);
	
	public void deleteRoomInfo(long id);
}
