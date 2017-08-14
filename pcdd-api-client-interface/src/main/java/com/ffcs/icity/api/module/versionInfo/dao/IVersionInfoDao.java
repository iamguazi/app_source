package com.ffcs.icity.api.module.versionInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.versionInfo.entity.*;
import com.ffcs.icity.api.module.versionInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IVersionInfoDao {
	
	public List<VersionInfo> findAll();
	
	public List<VersionInfo> findByCondition(VersionInfo versionInfo);
	
	public VersionInfo getVersionInfoById(int id);
	
	public void insertVersionInfo(VersionInfo versionInfo);
	
	public void updateVersionInfo(VersionInfo versionInfo);
	
	public void deleteVersionInfo(long id);
	
	public VersionInfo getVersionInfoByNew(int client);
	
	
}
