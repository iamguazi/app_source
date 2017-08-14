package com.ffcs.icity.mvc.backstage.gift.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.gift.dao.ExchangeGiftLogDao;
import com.ffcs.icity.mvc.backstage.gift.entity.ExchangeGiftLog;
import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;

@Service
@Transactional(rollbackFor={Exception.class})
public class ExchangeGiftLogService {
	
	@Autowired
	private ExchangeGiftLogDao exchangeGiftLogDao;
	
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	public void  updateStatus(String[] ids,Integer status) throws Exception{
		if (ids == null || ids.length <= 0){
			return ;
		}
		
		exchangeGiftLogDao.batchUpdateStatus(ids,status);
		
		ExchangeGiftLog exchangeGiftLog = null;
		NoticeInfo noticeInfo = new NoticeInfo();
		for (int i =0, len = ids.length; i < len; i++){
			exchangeGiftLog = exchangeGiftLogDao.get(Long.parseLong(ids[i]));
			if (exchangeGiftLog != null){
				noticeInfo.setUserId(exchangeGiftLog.getUserId());
				noticeInfo.setNoticeType(2);
				noticeInfo.setTitle("【"+exchangeGiftLog.getGiftName()+"】礼物,审核通过");
				noticeInfo.setContent("您申请的【"+exchangeGiftLog.getGiftName()+"】礼物,审核通过");
				noticeInfoDao.insert(noticeInfo);
			}
		}
	}
}
