package com.ffcs.icity.mvc.backstage.withdrawalslogs.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.backstage.withdrawalslogs.entity.WithdrawalsLogs;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WithdrawalsLogsDao extends CRUDDao<WithdrawalsLogs> {
	public int updateStatus(Map<String,Object> params);
	public void batchUpdateStatus(@Param("ids") int[] ids,@Param("status") int status);
	int countStatus(Integer status);
}