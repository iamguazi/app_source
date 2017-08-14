package com.ffcs.icity.api.module.otherInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.otherInfo.entity.*;
import com.ffcs.icity.api.module.otherInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IOtherInfoDao {
	
	public List<OtherInfo> findAll();
	
	public List<OtherInfo> findByCondition(OtherInfo otherInfo);
	
	public OtherInfo getOtherInfoById(int id);
	
	public void insertOtherInfo(OtherInfo otherInfo);
	
	public void updateOtherInfo(OtherInfo otherInfo);
	
	public void deleteOtherInfo(long id);
	
	public OtherInfo getOtherInfoByKey(String key);
}
