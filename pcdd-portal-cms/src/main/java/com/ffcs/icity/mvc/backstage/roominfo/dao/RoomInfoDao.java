package com.ffcs.icity.mvc.backstage.roominfo.dao;

import java.util.List;
import java.util.Map;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mybatis.Page;
import com.ffcs.icity.mvc.backstage.roominfo.entity.RoomInfo;

@MyBatisRepository
public interface RoomInfoDao extends CRUDDao<RoomInfo> {
	List<RoomInfo> queryRoomIndex(Map<String, Object> parameters,Page<RoomInfo> page);
	public void updateStatus(Map<String,Object> params);
	List<Map<String,Object>> queryRoomInfoItem(Map<String, Object> parameters,Page<RoomInfo> page);
}