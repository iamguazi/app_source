package com.ffcs.icity.api.module.noticeInfo.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.noticeInfo.entity.*;
import com.ffcs.icity.api.module.noticeInfo.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface INoticeInfoDao {
	
	public List<NoticeInfo> findAll();
	
	public List<NoticeInfo> findByCondition(NoticeInfo noticeInfo);
	
	public NoticeInfo getNoticeInfoById(int id);
	
	public void insertNoticeInfo(NoticeInfo noticeInfo);
	
	public void updateNoticeInfo(NoticeInfo noticeInfo);
	
	public void deleteNoticeInfo(long id);
	
	public List<NoticeInfo> getMyList(NoticeInfo noticeInfo);
	
	public int getUnreadCount(int userId);
	
	public int getUnreadMyCount(int userId);
	
}
