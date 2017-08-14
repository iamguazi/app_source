package com.ffcs.icity.mvc.backstage.account.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.account.dao.UserAccountLogDao;
import com.ffcs.icity.mvc.backstage.account.entity.UserAccountLog;
import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.backstage.point.dao.PointChangeLogDao;
import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;

@Service
@Transactional(rollbackFor={Exception.class})
public class UserAccountLogService {
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	@Autowired
	private PointChangeLogDao pointChangeLogDao;
	
	public void updateStatus(Long id, Integer status,Long userId,Double point){
		UserAccountLog userAccoutLog = userAccountLogDao.get(id);

		if (userAccoutLog == null) {
			return ;
		}
		
		userAccountLogDao.updateStatus(id, status);
		DecimalFormat  fmt = new DecimalFormat("#0.00");
		NoticeInfo noticeInfo = new NoticeInfo();
		noticeInfo.setUserId(userId.intValue());
		noticeInfo.setNoticeType(2);
		
		noticeInfo.setTitle("线下转账"+fmt.format(point)+"元审核未通过通知");
		noticeInfo.setContent(userAccoutLog.getAccount()+"账号线下转账"+fmt.format(point)+"审核未通过。");
		if (status == 1){
			//确认转账
			noticeInfo.setTitle("线下转账"+fmt.format(point)+"元到账通知");
			noticeInfo.setContent(userAccoutLog.getAccount()+"账号线下转账"+fmt.format(point)+"元已到账。请注意查收");

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("point", point);
			userInfoDao.updatePoint(params);
			PointChangeLog pointChangeLog = new PointChangeLog();
			pointChangeLog.setUserId(userId.intValue());
			pointChangeLog.setPoint(point);
			pointChangeLog.setPointDesc("线下转账");
			pointChangeLogDao.insert(pointChangeLog);
		}
		
		noticeInfoDao.insert(noticeInfo);
	}
}
