package com.ffcs.icity.api.module.otherParams.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.otherParams.entity.*;
import com.ffcs.icity.api.module.otherParams.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IOtherParamsDao {
	
	public List<OtherParams> findAll();
	
	public List<OtherParams> findByCondition(OtherParams otherParams);
	
	public OtherParams getOtherParamsById(int id);
	
	public void insertOtherParams(OtherParams otherParams);
	
	public void updateOtherParams(OtherParams otherParams);
	
	public void deleteOtherParams(long id);
	
	
	public List<OtherParams> getOtherParamsByParams(String paramsGroup);
}
