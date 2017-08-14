package com.ffcs.icity.mvc.backstage.withdrawalslogs.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.withdrawalslogs.dao.WithdrawalsLogsDao;

@Service
@Transactional(rollbackFor={Exception.class})
public class WithdrawalsLogsService {
	
	@Autowired
	private WithdrawalsLogsDao withdrawalsLogsDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	public void  updateStatus(List<Map<String,Object>> params,String status) throws Exception{

		if(CollectionUtils.isEmpty(params)){
			return ;
		}
		
		DecimalFormat  fmt = new DecimalFormat("#0.00");
		NoticeInfo noticeInfo = null;
		Integer userId = null;
		//拒绝提现
		if("2".equals(status)){
			int success = 0;
			for(Map<String,Object> param : params){
				success = withdrawalsLogsDao.updateStatus(param);
				if(success>0){
					userInfoDao.updatePoint(param);
					userId = Integer.parseInt(param.get("userId").toString());
					noticeInfo = new NoticeInfo();
					noticeInfo.setUserId(userId);
					noticeInfo.setNoticeType(2);
					noticeInfo.setTitle(param.get("title").toString());
					noticeInfo.setContent(param.get("content").toString());
					noticeInfoDao.insert(noticeInfo);
				}
			}
		}else if("1".equals(status)){
			int[] ids = new int[params.size()];
			for(int i=0,len=params.size();i<len;i++){
				ids[i] = Integer.parseInt(params.get(i).get("id").toString()) ;
				
				userId = Integer.parseInt(params.get(i).get("userId").toString());
				noticeInfo = new NoticeInfo();
				noticeInfo.setUserId(userId);
				noticeInfo.setNoticeType(2);
				noticeInfo.setTitle("提现审核通过通知");
				noticeInfo.setContent("您申请的"+fmt.format(Double.parseDouble(params.get(i).get("point").toString()))+"元提现,已通过审核。请注意查收");
				noticeInfoDao.insert(noticeInfo);
			}
			withdrawalsLogsDao.batchUpdateStatus(ids,Integer.parseInt(status));
			
		}else {
			//异常处理
			int success = 0;
			for(Map<String,Object> param : params){
				success = withdrawalsLogsDao.updateStatus(param);
				if(success>0){
					userId = Integer.parseInt(param.get("userId").toString());
					noticeInfo = new NoticeInfo();
					noticeInfo.setUserId(userId);
					noticeInfo.setNoticeType(2);
					noticeInfo.setTitle("提现审核异常通知");
					noticeInfo.setContent("您申请的"+fmt.format(Double.parseDouble(param.get("point").toString()))+"元提现,发现异常。");
					noticeInfoDao.insert(noticeInfo);
				}
			}
		}
	}
	
}
