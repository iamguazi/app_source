package com.ffcs.icity.api.module.wapInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.wapInfo.entity.*;
import com.ffcs.icity.api.module.wapInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IWapInfoDao {
	
	public List<WapInfo> findAll();
	
	public List<WapInfo> findByCondition(WapInfo wapInfo);
	
	public WapInfo getWapInfoById(int id);
	
	public void insertWapInfo(WapInfo wapInfo);
	
	public void updateWapInfo(WapInfo wapInfo);
	
	public void deleteWapInfo(long id);
	
	public WapInfo getWapInfoByKey(String wapKey);
}
