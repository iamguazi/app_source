package com.ffcs.icity.api.module.payParams.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.payParams.entity.*;
import com.ffcs.icity.api.module.payParams.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IPayParamsDao {
	
	public List<PayParams> findAll();
	
	public List<PayParams> findByCondition(PayParams payParams);
	
	public PayParams getPayParamsById(int id);
	
	public void insertPayParams(PayParams payParams);
	
	public void updatePayParams(PayParams payParams);
	
	public void deletePayParams(long id);
	
	public PayParams getPayParamsByType(String mchType);
}
