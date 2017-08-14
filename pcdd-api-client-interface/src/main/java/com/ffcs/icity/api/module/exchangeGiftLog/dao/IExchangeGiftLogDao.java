package com.ffcs.icity.api.module.exchangeGiftLog.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.exchangeGiftLog.entity.*;
import com.ffcs.icity.api.module.exchangeGiftLog.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IExchangeGiftLogDao {
	
	public List<ExchangeGiftLog> findAll();
	
	public List<ExchangeGiftLog> findByCondition(ExchangeGiftLog exchangeGiftLog);
	
	public ExchangeGiftLog getExchangeGiftLogById(int id);
	
	public void insertExchangeGiftLog(ExchangeGiftLog exchangeGiftLog);
	
	public void updateExchangeGiftLog(ExchangeGiftLog exchangeGiftLog);
	
	public void deleteExchangeGiftLog(long id);
}
