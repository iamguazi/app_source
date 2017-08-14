package com.ffcs.icity.mvc.backstage.gift.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mvc.backstage.gift.entity.ExchangeGiftLog;

@MyBatisRepository
public interface ExchangeGiftLogDao extends CRUDDao<ExchangeGiftLog> {
	public int updateStatus(Map<String,Object> params);
	public void batchUpdateStatus(@Param("ids") String[] ids,@Param("status") int status);
	int countStatus(Integer status);
}