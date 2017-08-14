package com.ffcs.icity.mvc.backstage.user.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.backstage.point.dao.PointChangeLogDao;
import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;

@Transactional(rollbackFor={Exception.class})
@Service
public class UserRechargeService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private PointChangeLogDao pointChangeLogDao;
	
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	public void recharge(Integer userId,Double rechargePoint,String desc){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("point", rechargePoint);
			userInfoDao.updatePointAndAllPoint(params);
			PointChangeLog pointChangeLog = new PointChangeLog();
			pointChangeLog.setUserId(userId);
			pointChangeLog.setPoint(rechargePoint);
			if(StringUtils.isNotBlank(desc)){
				pointChangeLog.setPointDesc(desc);
			}else{
				if(rechargePoint <= 0){
					pointChangeLog.setPointDesc("后台手动扣除");
				}else{
					pointChangeLog.setPointDesc("后台手动充值");
				}
			}
			
			pointChangeLogDao.insert(pointChangeLog);
			
			DecimalFormat  fmt = new DecimalFormat("#0.00");
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setUserId(userId);
			noticeInfo.setNoticeType(2);
			if(rechargePoint <= 0){
				noticeInfo.setTitle("后台手动扣除"+fmt.format(rechargePoint)+"元通知");
				noticeInfo.setContent("后台手动扣除"+fmt.format(rechargePoint)+"元。请注意查收");
			}else{
				noticeInfo.setTitle("后台充值"+fmt.format(rechargePoint)+"元到账通知");
				noticeInfo.setContent("您后台充值的"+fmt.format(rechargePoint)+"元已到账。请注意查收");
			}
			
			noticeInfoDao.insert(noticeInfo);
	}
}
