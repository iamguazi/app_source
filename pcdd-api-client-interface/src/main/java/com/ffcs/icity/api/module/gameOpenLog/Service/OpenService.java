package com.ffcs.icity.api.module.gameOpenLog.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffcs.icity.api.module.gameOpenLog.dao.IGameOpenLogDao;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;
import com.ffcs.icity.api.module.otherParams.dao.IOtherParamsDao;
import com.ffcs.icity.api.module.otherParams.entity.OtherParams;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.pointChangeLog.entity.PointChangeLog;
import com.ffcs.icity.api.module.userChoiceLog.dao.IUserChoiceLogDao;
import com.ffcs.icity.api.module.userChoiceLog.entity.UserChoiceLog;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;

@Service
public class OpenService {
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private IUserChoiceLogDao userChoiceLogDao;
	
	@Autowired
	private IPointChangeLogDao pointChangeLogDao;
	
	@Autowired
	private IOtherParamsDao otherParamsDao;
	
	public void openJiang(String gameNum ,String gameType){
		GameOpenLog gameOpenLog=new GameOpenLog();
		gameOpenLog.setGameNum(gameNum);
		gameOpenLog.setGameType(Integer.parseInt(gameType));
		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByNum(gameOpenLog);
		if(openLog.getIsGive()==1){
			return;
		}
		UserChoiceLog userChoiceLog=new UserChoiceLog();
		userChoiceLog.setChoiceResult(openLog.getGameResult()+"");
		userChoiceLog.setGameType(openLog.getGameType());
		userChoiceLog.setChoiceNo(openLog.getGameNum());
		userChoiceLog.setRealResult(openLog.getGameResult()+"");
		userChoiceLog.setResultType(openLog.getResultType());
		
		
		double allPoint=0;
		
		//初始化
		double chuji_zuhe_bili = 0;
		double chuji_daxiao_bili = 0;
		double zhongji_zuhe_bili = 0;
		double zhongji_daxiao_bili = 0;
		double gaoji_zuhe_bili = 0;
		double gaoji_daxiao_bili = 0;
		
		if(openLog.getGameResult()==13||openLog.getGameResult()==14){
			List<OtherParams> paramsList = otherParamsDao.getOtherParamsByParams("open");
			Map<String, OtherParams> paramsMap = getMap(paramsList);
			chuji_zuhe_bili=Double.parseDouble(paramsMap.get("chuji_zuhe_bili").getParamsValue());
			chuji_daxiao_bili=Double.parseDouble(paramsMap.get("chuji_daxiao_bili").getParamsValue());
			zhongji_zuhe_bili=Double.parseDouble(paramsMap.get("zhongji_zuhe_bili").getParamsValue());
			zhongji_daxiao_bili=Double.parseDouble(paramsMap.get("zhongji_daxiao_bili").getParamsValue());
			gaoji_zuhe_bili=Double.parseDouble(paramsMap.get("gaoji_zuhe_bili").getParamsValue());
			gaoji_daxiao_bili=Double.parseDouble(paramsMap.get("gaoji_daxiao_bili").getParamsValue());
		}
		
		
		//如果是豹子  单独开奖
		if(openLog.getIsBaozi()==1){
			UserChoiceLog userChoiceLogQuery=new UserChoiceLog();
			userChoiceLogQuery.setChoiceResult("-1");
			userChoiceLogQuery.setGameType(openLog.getGameType());
			userChoiceLogQuery.setChoiceNo(openLog.getGameNum());
			
			List<UserChoiceLog> baoziList = userChoiceLogDao.findByCondition(userChoiceLogQuery);
			for(UserChoiceLog vo:baoziList){
				double getPoint =vo.getBili()*vo.getPoint();
				allPoint=allPoint+getPoint;
				vo.setGetPoint(getPoint);
				vo.setRealResult(openLog.getGameResult()+"");
				vo.setResultType("豹子");
				vo.setIsZhong(1);
				userChoiceLogDao.updateUserChoiceLog(vo);
				
				//添加中奖账变记录
				PointChangeLog pointChangeLog=new PointChangeLog();
				pointChangeLog.setPoint(getPoint);
				pointChangeLog.setPointDesc("中奖");
				pointChangeLog.setUserId(vo.getUserId());
				pointChangeLogDao.insertPointChangeLog(pointChangeLog);
				
				UserInfo userInfo =new UserInfo();
				userInfo.setId(vo.getUserId());
				userInfo.setPoint(getPoint);
				userInfoDao.updateUserInfoByPoint(userInfo);
			}
		}
		
		
		List<UserChoiceLog> list = userChoiceLogDao.getZhongJiangList(userChoiceLog);
		for(UserChoiceLog vo:list){
			double getPoint =vo.getBili()*vo.getPoint();
			/**
			 * 中级房，开13，14
				大小单双赔率1.5
				组合倍率2.5倍
				
				
				初级房，开13，14
				大小单双赔率1
				组合倍率1倍
			 */
			if(openLog.getGameResult()==13||openLog.getGameResult()==14){
				
				if("大".equals(vo.getChoiceName())||"小".equals(vo.getChoiceName())||"单".equals(vo.getChoiceName())||"双".equals(vo.getChoiceName())){
					if(vo.getAreaId()==1||vo.getAreaId()==4){
						
						//后台设置0 就按着原有设置来
						if(chuji_daxiao_bili!=-1){
							//初级房大小单双 倍率
							getPoint=vo.getPoint()*chuji_daxiao_bili;
						}
					}else if(vo.getAreaId()==2||vo.getAreaId()==5){
						//后台设置0 就按着原有设置来
						if(zhongji_daxiao_bili!=-1){
							//中级房大小单双 倍率
							getPoint=vo.getPoint()*zhongji_daxiao_bili;
						}
						 
					}else if(vo.getAreaId()==3||vo.getAreaId()==6){
						//后台设置0 就按着原有设置来
						if(gaoji_daxiao_bili!=-1){
							//高级房大小单双 倍率
							getPoint=vo.getPoint()*gaoji_daxiao_bili;
						}
						 
					}
					
					
				}else if("大双".equals(vo.getChoiceName())||"小双".equals(vo.getChoiceName())||"小单".equals(vo.getChoiceName())||"大单".equals(vo.getChoiceName())){
					if(vo.getAreaId()==1||vo.getAreaId()==4){
						
						//后台设置0 就按着原有设置来
						if(chuji_zuhe_bili!=-1){
							//初级房大小单双 倍率
							getPoint=vo.getPoint()*chuji_zuhe_bili;
						}
					}else if(vo.getAreaId()==2||vo.getAreaId()==5){
						//后台设置0 就按着原有设置来
						if(zhongji_zuhe_bili!=-1){
							//中级房大小单双 倍率
							getPoint=vo.getPoint()*zhongji_zuhe_bili;
						}
						 
					}else if(vo.getAreaId()==3||vo.getAreaId()==6){
						//后台设置0 就按着原有设置来
						if(gaoji_zuhe_bili!=-1){
							//高级房大小单双 倍率
							getPoint=vo.getPoint()*gaoji_zuhe_bili;
						}
						 
					}
				}
				
			} 
			
			allPoint=allPoint+getPoint;
			//添加中奖账变记录
			PointChangeLog pointChangeLog=new PointChangeLog();
			pointChangeLog.setPoint(getPoint);
			pointChangeLog.setPointDesc("中奖");
			pointChangeLog.setUserId(vo.getUserId());
			pointChangeLogDao.insertPointChangeLog(pointChangeLog);
			UserInfo userInfo =new UserInfo();
			userInfo.setId(vo.getUserId());
			userInfo.setPoint(getPoint);
			userInfoDao.updateUserInfoByPoint(userInfo);
		}
		//13 14特殊处理
		if(openLog.getGameResult()==13||openLog.getGameResult()==14){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("realResult",openLog.getGameResult()+"");
			params.put("resultType", openLog.getResultType());
			params.put("choiceNo", openLog.getGameNum());
			params.put("gameType", openLog.getGameType());
			params.put("choiceResult", openLog.getGameResult()+"");
			//初级房开奖
			params.put("areaId", "1");
			
			//后台设置0 就按着原有设置来
			if(chuji_daxiao_bili!=-1){
				//初级房大小单双 倍率
				params.put("bili", chuji_daxiao_bili);
				params.put("type", "1");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
			if(chuji_zuhe_bili!=-1){
				//初级房组合比例  
				params.put("bili", chuji_zuhe_bili);
				params.put("type", "2");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
			
			
			//中级房开奖
			params.put("areaId", "2");
			//后台设置0 就按着原有设置来
			if(zhongji_daxiao_bili!=-1){
				//中级房大小单双 倍率
				params.put("bili", zhongji_daxiao_bili);
				params.put("type", "1");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
			if(zhongji_zuhe_bili!=-1){
				//中级房组合比例  
				params.put("bili", zhongji_zuhe_bili);
				params.put("type", "2");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
			
			
			//高级房开奖
			params.put("areaId", "3");
			//后台设置0 就按着原有设置来
			if(gaoji_daxiao_bili!=-1){
				//高级房大小单双 倍率
				params.put("bili", gaoji_daxiao_bili);
				params.put("type", "1");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
			if(gaoji_zuhe_bili!=-1){
				//高级房组合比例  
				params.put("bili", gaoji_zuhe_bili);
				params.put("type", "2");//1 大小单双开奖  2组合比例 开奖
				userChoiceLogDao.updateUserChoiceLogBy1314(params);
			}
		}
		
		userChoiceLogDao.updateUserChoiceLogByZhong(userChoiceLog);
		userChoiceLogDao.updateUserChoiceLogByUnZhong(userChoiceLog);
		
		openLog.setIsGive(1);
		openLog.setTotalPoint(userChoiceLogDao.getSumByNo(userChoiceLog));
		openLog.setXhibitPoint(openLog.getTotalPoint()-allPoint);
		gameOpenLogDao.updateGameOpenLog(openLog);
	}
	
	public Map<String, OtherParams> getMap(List<OtherParams> paramsList){
		Map<String, OtherParams> result=new HashMap<String, OtherParams>();
		for(OtherParams param:paramsList){
			result.put(param.getParamsKey(), param);
		}
		
		return result;
	}
}
