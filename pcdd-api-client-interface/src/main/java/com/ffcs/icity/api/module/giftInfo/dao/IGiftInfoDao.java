package com.ffcs.icity.api.module.giftInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.giftInfo.entity.*;
import com.ffcs.icity.api.module.giftInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IGiftInfoDao {
	
	public List<GiftInfo> findAll();
	
	public List<GiftInfo> findByCondition(GiftInfo giftInfo);
	
	public GiftInfo getGiftInfoById(int id);
	
	public void insertGiftInfo(GiftInfo giftInfo);
	
	public void updateGiftInfo(GiftInfo giftInfo);
	
	public void deleteGiftInfo(long id);
}
