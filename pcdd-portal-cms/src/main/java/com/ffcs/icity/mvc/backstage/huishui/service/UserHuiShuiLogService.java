package com.ffcs.icity.mvc.backstage.huishui.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.huishui.dao.UserHuiShuiLogDao;
import com.ffcs.icity.mvc.backstage.huishui.entity.UserHuiShuiLog;
import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.dao.UserAndNoticeDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.backstage.point.dao.PointChangeLogDao;
import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;

@Service
@Transactional(rollbackFor={Exception.class})
public class UserHuiShuiLogService {
	
	@Autowired
	private UserHuiShuiLogDao userHuiShuiLogDao;
	
	@Autowired
	private PointChangeLogDao pointChangeLogDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	@Autowired
	private UserAndNoticeDao userAndNoticeDao;
	
	public void  updateStatus(List<Map<String,Object>> params,String status) throws Exception{
		
		if(CollectionUtils.isEmpty(params)){
			return ;
		}
		
		DecimalFormat  fmt = new DecimalFormat("#0.00");
		NoticeInfo noticeInfo = null;
		Integer userId = null;
		Double point = null;
		int huishuiLogSuccess = 0,updataPointSuccess = 0;
		boolean hasParamsKey = false;
		PointChangeLog pointChangeLog = null;
		UserHuiShuiLog userHuiShuiLog = null;
		
		//确认回水
		if("1".equals(status)){
			
			for(Map<String,Object> param : params){
				hasParamsKey = param.containsKey("id") && param.containsKey("status") && param.containsKey("userId")  && param.containsKey("point") ;
				
				if(hasParamsKey){
					
					userHuiShuiLog = userHuiShuiLogDao.get(Long.parseLong(param.get("id").toString()));
					
					if(userHuiShuiLog.getStatus() != 0 ){
						return ;
					}
					
					huishuiLogSuccess = userHuiShuiLogDao.updateStatus(param);
					
					if(huishuiLogSuccess>0){
						updataPointSuccess = userInfoDao.updatePoint(param);
					}
					
					userId = Integer.parseInt(param.get("userId").toString());
					point = Double.parseDouble(param.get("point").toString());
					
					if(updataPointSuccess>0){
						pointChangeLog = new PointChangeLog();
						pointChangeLog.setUserId(userId);
						pointChangeLog.setPoint(point);
						pointChangeLog.setPointDesc("回水金额");
						pointChangeLogDao.insert(pointChangeLog);
						
						noticeInfo = new NoticeInfo();
						noticeInfo.setUserId(userId);
						noticeInfo.setNoticeType(2);
						noticeInfo.setTitle("回水审核通过通知");
						noticeInfo.setContent("您申请的"+fmt.format(point)+"元回水,已通过审核。资金已到达您的账户,请注意查收");
						noticeInfoDao.insert(noticeInfo);
						
					}
				}
			}
		}else{
			//拒绝回水
			int[] ids = new int[params.size()];
			Map<String,Object> param = null;
			for(int i=0,len=params.size();i<len;i++){
				ids[i] = Integer.parseInt(params.get(i).get("id").toString()) ;
				param = params.get(i);
				hasParamsKey = param.containsKey("id") && param.containsKey("status") && param.containsKey("userId")  && param.containsKey("point") ;
				userId = Integer.parseInt(param.get("userId").toString());
				point = Double.parseDouble(param.get("point").toString());
				
				if(hasParamsKey){
					userId = Integer.parseInt(param.get("userId").toString());
					noticeInfo = new NoticeInfo();
					noticeInfo.setUserId(userId);
					noticeInfo.setNoticeType(2);
					noticeInfo.setTitle("回水审核未通过通知");
					noticeInfo.setContent("您申请的"+fmt.format(point)+"元回水,未通过审核");
					noticeInfoDao.insert(noticeInfo);
				}
			}
			userHuiShuiLogDao.batchUpdateStatus(ids,Integer.parseInt(status));
		}
	}
}
