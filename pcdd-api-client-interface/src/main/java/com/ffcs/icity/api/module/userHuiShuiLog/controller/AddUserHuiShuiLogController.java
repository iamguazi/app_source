package com.ffcs.icity.api.module.userHuiShuiLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.huiShuiRule.dao.IHuiShuiRuleDao;
import com.ffcs.icity.api.module.huiShuiRule.entity.HuiShuiRule;
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

public class AddUserHuiShuiLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IUserHuiShuiLogDao userHuiShuiLogDao;
	
	@Autowired
	private IHuiShuiRuleDao huiShuiRuleDao;
	
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
		String type="";
		for(int i=1;i<=3;i++){
			type=i+"";
			int count=userHuiShuiLogDao.getHuishuiCount(Integer.parseInt(type));
			//判断是否改过
			if(count>0){
				continue;
			}
			HuiShuiRule huiShuiRule=new HuiShuiRule();
			huiShuiRule.setAreaType(Integer.parseInt(type));
			List<HuiShuiRule> list = huiShuiRuleDao.findByCondition(huiShuiRule);
			if(list!=null&&list.size()>0){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("areaId", type);
				params.put("startPoint", list.get(0).getStartPoint());
				List<HuiShuiVoLog> userList = userHuiShuiLogDao.getUserList(params);
				for(HuiShuiVoLog log:userList){
					
					HuiShuiRule lowestVo = getLowest(list, log.getPoint());
					if(lowestVo==null){
						continue;
					}else{
						UserHuiShuiLog userHuiShuiLogModel=new UserHuiShuiLog();
						//如果组合比例低于20% 不回水
						if(log.getZuhePoint()/log.getPoint()<0.2){
							continue;
						}
						//下注总数不低于10注
						if(log.getPointNum()<10){
							continue;
						}
						
						userHuiShuiLogModel.setBili(lowestVo.getBili());
						userHuiShuiLogModel.setHuiShuiPoint(lowestVo.getBili()*log.getPoint());
						userHuiShuiLogModel.setPoint(log.getAddPoint());
						userHuiShuiLogModel.setStatus(0);
						userHuiShuiLogModel.setType(Integer.parseInt(type));
						userHuiShuiLogModel.setUserId(log.getUserId());
						userHuiShuiLogModel.setZuhePoint(log.getZuhePoint());
						userHuiShuiLogModel.setPointNum(log.getPointNum());
						userHuiShuiLogModel.setGetPoint(log.getGetPoint());
						userHuiShuiLogModel.setXhibitPoint(-log.getPoint());
						userHuiShuiLogDao.insertUserHuiShuiLog(userHuiShuiLogModel);
					}
					
				}
			}
		}
		
		
		 
		return "ok";
	}
	
	public HuiShuiRule getLowest(List<HuiShuiRule> list,double point){
		HuiShuiRule vo=null;
		for(HuiShuiRule rule:list){
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

