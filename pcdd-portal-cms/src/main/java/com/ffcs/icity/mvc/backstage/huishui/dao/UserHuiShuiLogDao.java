package com.ffcs.icity.mvc.backstage.huishui.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mvc.backstage.huishui.entity.UserHuiShuiLog;

@MyBatisRepository
public interface UserHuiShuiLogDao extends CRUDDao<UserHuiShuiLog> {
	public int updateStatus(Map<String,Object> params);
	public void batchUpdateStatus(@Param("ids") int[] ids,@Param("status") int status);
}