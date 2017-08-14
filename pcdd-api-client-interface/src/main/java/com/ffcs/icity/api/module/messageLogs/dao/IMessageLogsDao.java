package com.ffcs.icity.api.module.messageLogs.dao;

import java.util.List;

import com.ffcs.icity.api.module.messageLogs.entity.MessageLogs;
import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IMessageLogsDao {
	
	public List<MessageLogs> findAll();
	
	public List<MessageLogs> findByCondition(MessageLogs messageLogs);
	
	public MessageLogs getMessageLogsById(Integer id);
	
	public void insertMessageLogs(MessageLogs messageLogs);
	
	public void updateMessageLogs(MessageLogs messageLogs);
	
	public void deleteMessageLogs(long id);
	
	public long count(MessageLogs messageLogs);
}
