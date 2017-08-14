package com.ffcs.icity.mvc.backstage.fenxiao.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.backstage.fenxiao.entity.UserFenxiaoLog;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserFenxiaoLogDao extends CRUDDao<UserFenxiaoLog> {
	int updateStatus(Map<String,Object> params);
	int batchUpdateStatus(@Param("ids") int[] ids, @Param("status")Integer status);
}