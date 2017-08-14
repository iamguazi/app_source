package com.ffcs.icity.api.module.userAndNotice.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.userAndNotice.entity.*;
import com.ffcs.icity.api.module.userAndNotice.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IUserAndNoticeDao {
	
	public List<UserAndNotice> findAll();
	
	public List<UserAndNotice> findByCondition(UserAndNotice userAndNotice);
	
	public UserAndNotice getUserAndNoticeById(int id);
	
	public void insertUserAndNotice(UserAndNotice userAndNotice);
	
	public void updateUserAndNotice(UserAndNotice userAndNotice);
	
	public void deleteUserAndNotice(long id);
	
	public UserAndNotice getUserAndNoticeByParams(UserAndNotice userAndNotice);
	
}
