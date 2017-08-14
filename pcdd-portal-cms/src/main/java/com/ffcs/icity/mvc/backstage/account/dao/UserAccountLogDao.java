package com.ffcs.icity.mvc.backstage.account.dao;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mvc.backstage.account.entity.UserAccountLog;

@MyBatisRepository
public interface UserAccountLogDao extends CRUDDao<UserAccountLog> {
	public int updateStatus(@Param("id")Long id,@Param("status")Integer status);
	public int countStatus(Integer status);
}