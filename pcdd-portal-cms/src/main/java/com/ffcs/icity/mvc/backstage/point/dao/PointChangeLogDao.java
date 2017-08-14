package com.ffcs.icity.mvc.backstage.point.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mybatis.Page;

@MyBatisRepository
public interface PointChangeLogDao extends CRUDDao<PointChangeLog> {
	
	List<PointChangeLog> queryViewIndex(Map<String, Object> parameters,Page<PointChangeLog> page);

	@Select("SELECT  POINT_DESC  FROM POINT_CHANGE_LOG GROUP BY POINT_CHANGE_LOG.`POINT_DESC`")
	List<String> getPointDesc();
}