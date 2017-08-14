package com.ffcs.icity.api.module.userFenxiaoLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.userFenxiaoLog.dao.IUserFenxiaoLogDao;
import com.ffcs.icity.api.module.userFenxiaoLog.entity.UserFenxiaoLog;
import com.ffcs.icity.api.module.userHuiShuiLog.entity.*;
import com.ffcs.icity.api.module.userHuiShuiLog.dao.*;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.util.DateHelper;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class UserFenxiaoLogListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	
	@Autowired
	private IUserFenxiaoLogDao userFenxiaoLogDao;
	
	
	@Autowired
	private IUserHuiShuiLogDao userHuiShuiLogDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "page_no");
		assertNotEmpty(requestArgument, "page_size");
		assertNotEmpty(requestArgument, "user_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String pageNo=requestArgument.get("page_no").toString();
		String pageSize=requestArgument.get("page_size").toString();
		String userId=requestArgument.get("user_id").toString();

		UserFenxiaoLog userFenxiaoLog=new UserFenxiaoLog();
		userFenxiaoLog.setStart((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
		userFenxiaoLog.setPageSize(Integer.parseInt(pageSize));
		userFenxiaoLog.setUserId(Integer.parseInt(userId));
		List<UserFenxiaoLog> list = userFenxiaoLogDao.findByCondition(userFenxiaoLog);
		List<UserFenxiaoLog> result=new ArrayList<UserFenxiaoLog>();
		UserInfo userInfo=new UserInfo();
		userInfo.setCode(userId);
		  List<TuiJianVoLog> fenxiaoList = userFenxiaoLogDao.getAllUserListByCode(userId);
		for(TuiJianVoLog tuijian:fenxiaoList){
			UserFenxiaoLog resultVo=isHave(tuijian, list);
			if(resultVo!=null&&resultVo.getCreateTime().getTime()>=DateHelper.getEarlyDay(new Date(), 1).getTime()&&resultVo.getStatus()==1){
				result.add(resultVo);
			}else{
				UserFenxiaoLog fenxiaoLog=new UserFenxiaoLog();
				fenxiaoLog.setFenxiaoPoint("未满足");
				fenxiaoLog.setFenxiaoUserId(tuijian.getUserId());
				fenxiaoLog.setPoint(tuijian.getAddPoint());
				fenxiaoLog.setId(0);
				fenxiaoLog.setUserId(Integer.parseInt(userId));
				fenxiaoLog.setStatus(0);
				fenxiaoLog.setGetPoint(0.0);
				fenxiaoLog.setZuhePoint(0.0);
				fenxiaoLog.setXhibitPoint(0.0);
				fenxiaoLog.setCreateTime(DateHelper.getEarlyDay(new Date(), 1));
				UserInfo nickNameUser = userInfoDao.getUserInfoById(tuijian.getUserId());
				fenxiaoLog.setPointNum(tuijian.getPointNum());
				if(nickNameUser!=null){
					if(nickNameUser.getNickName()==null){
						fenxiaoLog.setNickName(nickNameUser.getAccount());
					}else{
						fenxiaoLog.setNickName(nickNameUser.getNickName());
					}
					
					result.add(fenxiaoLog);
				} 
				
				
			}
			
		}
		return result;
	}
	
	public UserFenxiaoLog isHave(TuiJianVoLog tuijian, List<UserFenxiaoLog> list  ){
		for(UserFenxiaoLog log:list){
			if(log.getFenxiaoUserId().toString().equals(tuijian.getUserId().toString())){
				return log;
			}
		}
		return null;
	}
	
 
}

