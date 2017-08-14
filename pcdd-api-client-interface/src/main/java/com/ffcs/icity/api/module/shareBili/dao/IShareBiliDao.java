package com.ffcs.icity.api.module.shareBili.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.shareBili.entity.*;
import com.ffcs.icity.api.module.shareBili.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IShareBiliDao {
	
	public List<ShareBili> findAll();
	
	public List<ShareBili> findByCondition(ShareBili shareBili);
	
	public ShareBili getShareBiliById(int id);
	
	public void insertShareBili(ShareBili shareBili);
	
	public void updateShareBili(ShareBili shareBili);
	
	public void deleteShareBili(long id);
}
