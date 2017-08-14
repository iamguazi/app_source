package com.ffcs.icity.api.module.userFenxiaoLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.huiShuiRule.dao.IHuiShuiRuleDao;
import com.ffcs.icity.api.module.huiShuiRule.entity.HuiShuiRule;
import com.ffcs.icity.api.module.shareBili.dao.IShareBiliDao;
import com.ffcs.icity.api.module.shareBili.entity.ShareBili;
import com.ffcs.icity.api.module.userFenxiaoLog.dao.IUserFenxiaoLogDao;
import com.ffcs.icity.api.module.userFenxiaoLog.entity.UserFenxiaoLog;
import com.ffcs.icity.api.module.userHuiShuiLog.entity.*;
import com.ffcs.icity.api.module.userHuiShuiLog.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddUserFenxiaoLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserHuiShuiLogDao userHuiShuiLogDao;
	
	@Autowired
	private IHuiShuiRuleDao huiShuiRuleDao;
	
	@Autowired
	private IShareBiliDao shareBiliDao;
	
	@Autowired
	private IUserFenxiaoLogDao userFenxiaoLogDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		 
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
			int count=userFenxiaoLogDao.getFenxiaoCount();
			//判断是否改过
			if(count>0){
				return "ok";
			}
			 
			 List<ShareBili> list = shareBiliDao.findByCondition(null);
			if(list!=null&&list.size()>0){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("startPoint", list.get(0).getStartPoint());
				//下注总数不低于10注
				params.put("num", 10);
				List<TuiJianVoLog> userList = userHuiShuiLogDao.getUserListByCode(params);
				for(TuiJianVoLog log:userList){
					
					ShareBili lowestVo = getLowest(list, log.getAddPoint());
					if(lowestVo==null){
						continue;
					}else{
						//如果组合比例低于20% 不回水
						if(log.getZuhePoint()/log.getPoint()<0.2){
							continue;
						}
						
						UserFenxiaoLog userFenxiaoLog=new UserFenxiaoLog();
						userFenxiaoLog.setUserId(Integer.parseInt(log.getCode()));
						userFenxiaoLog.setPoint(log.getAddPoint());
						userFenxiaoLog.setFenxiaoPoint(lowestVo.getGetPoint().toString());
						userFenxiaoLog.setStatus(0);
						userFenxiaoLog.setZuhePoint(log.getZuhePoint());
						userFenxiaoLog.setPointNum(log.getPointNum());
						userFenxiaoLog.setGetPoint(log.getGetPoint());
						userFenxiaoLog.setXhibitPoint(-log.getPoint());
						userFenxiaoLog.setFenxiaoUserId(log.getUserId());
						userFenxiaoLogDao.insertUserFenxiaoLog(userFenxiaoLog);
					}
					
				}
			}
		
		
		 
		return "ok";
	}
	
	public ShareBili getLowest(List<ShareBili> list,double point){
		ShareBili vo=null;
		for(ShareBili rule:list){
			if(rule.getStartPoint()<=point){
				vo=rule;
			} 
		}
		System.out.println(vo.getStartPoint());
		return vo;
	}
	
	 @Override
	    public String[] getSignItems(Map<String, Object> requestArgument) {
	    	// TODO Auto-generated method stub
	    	return new String[] {"no_valid"};
	    }
}

