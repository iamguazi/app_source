package com.ffcs.icity.mvc.backstage.fenxiao.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.mvc.backstage.fenxiao.dao.UserFenxiaoLogDao;
import com.ffcs.icity.mvc.backstage.fenxiao.entity.UserFenxiaoLog;
import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.dao.UserAndNoticeDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.backstage.point.dao.PointChangeLogDao;
import com.ffcs.icity.mvc.backstage.point.entity.PointChangeLog;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;

@Service
@Transactional(rollbackFor={Exception.class})
public class UserFenxiaoLogService {
	@Autowired
	private UserFenxiaoLogDao userFenxiaoLogDao;
	
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
		int userId = 0;
		double point = 0;
		double fenxiaoPoint = 0;
		int userFenxiaoLogSuccess = 0,updataPointSuccess = 0;
		String friendName = null;
		String createTime = null;
//		String friendId = null;
		
		boolean hasParamsKey = false;
		PointChangeLog pointChangeLog = null;
		UserFenxiaoLog userFenxiaoLog = null;
		
		//确认分销
		if("1".equals(status)){
			StringBuilder sb = null;
			for(Map<String,Object> param : params){
				
				
				
				hasParamsKey = param.containsKey("id") && param.containsKey("status") && param.containsKey("userId")  && param.containsKey("point") ;
				
				if(hasParamsKey){
					
					sb = new StringBuilder("");
					
					userFenxiaoLog = userFenxiaoLogDao.get(Long.parseLong(param.get("id").toString()));
					
					if(userFenxiaoLog.getStatus() != 0 ){
						return ;
					}
					
					userFenxiaoLogSuccess = userFenxiaoLogDao.updateStatus(param);
					
					userId = Integer.parseInt(param.get("userId").toString());
					point = Double.parseDouble(param.get("point").toString());
					fenxiaoPoint = Double.parseDouble(param.get("fenxiaoPoint").toString());
					friendName = param.get("friendName").toString();
					createTime = param.get("createTime").toString();
					
					//将下注金额    改为分销金额  
					param.put("point", fenxiaoPoint);
					if(userFenxiaoLogSuccess>0){
						updataPointSuccess = userInfoDao.updatePoint(param);
					}
					
					if(updataPointSuccess>0){
						pointChangeLog = new PointChangeLog();
						pointChangeLog.setUserId(userId);
						pointChangeLog.setPoint(fenxiaoPoint);
						pointChangeLog.setPointDesc("分销金额");
						pointChangeLogDao.insert(pointChangeLog);
						
						noticeInfo = new NoticeInfo();
						noticeInfo.setUserId(userId);
						noticeInfo.setNoticeType(2);
						noticeInfo.setTitle("分销审核通过通知");
						sb.append("您的好友【").append(friendName).append("】,在").append(createTime)
						.append("下注").append(fmt.format(point)).append("元,您获得").append(fmt.format(fenxiaoPoint))
						.append("元佣金,请注意查收");
						noticeInfo.setContent(sb.toString());
						noticeInfoDao.insert(noticeInfo);
						
					}
				}
			}
		}else{
			//拒绝分销
			int[] ids = new int[params.size()];
			Map<String,Object> param = null;
			for(int i=0,len=params.size();i<len;i++){
				ids[i] = Integer.parseInt(params.get(i).get("id").toString()) ;
				param = params.get(i);
				hasParamsKey = param.containsKey("id") && param.containsKey("status") && param.containsKey("userId")  && param.containsKey("point") ;
				userId = Integer.parseInt(param.get("userId").toString());
				point = Double.parseDouble(param.get("point").toString());
				fenxiaoPoint = Double.parseDouble(param.get("fenxiaoPoint").toString());
				
				if(hasParamsKey){
					userId = Integer.parseInt(param.get("userId").toString());
					noticeInfo = new NoticeInfo();
					noticeInfo.setUserId(userId);
					noticeInfo.setNoticeType(2);
					noticeInfo.setTitle("分销审核未通过通知");
					noticeInfo.setContent("您申请的"+fmt.format(fenxiaoPoint)+"元分销,未通过审核");
					noticeInfoDao.insert(noticeInfo);
				}
			}
			userFenxiaoLogDao.batchUpdateStatus(ids,Integer.parseInt(status));
		}
	}
}
