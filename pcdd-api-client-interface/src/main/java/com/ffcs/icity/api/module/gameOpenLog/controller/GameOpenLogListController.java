package com.ffcs.icity.api.module.gameOpenLog.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameOpenLog.dao.IGameOpenLogDao;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.userChoiceLog.util.OpenTimeUtil;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class GameOpenLogListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Autowired
	private OpenTimeUtil openTimeUtil;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "room_id");
		assertNotEmpty(requestArgument, "user_id");
		assertNotEmpty(requestArgument, "game_type");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String userId=requestArgument.get("user_id").toString();
		String gameType=requestArgument.get("game_type").toString();
		String roomId=requestArgument.get("room_id").toString();

		GameOpenLog gameOpenLog=new GameOpenLog();
		gameOpenLog.setGameType(Integer.parseInt(gameType));
		gameOpenLog.setUserId(Integer.parseInt(userId));
		gameOpenLog.setStart(0);
		gameOpenLog.setPageSize(10);

		Map<String, Object>result=new HashMap<String, Object>();
		List<GameOpenLog> list = gameOpenLogDao.findByCondition(gameOpenLog);
		result.put("open_time", list);

		UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
		result.put("point", userInfo.getPoint());
		
		GameOpenLog gameOpenLogQuery=new GameOpenLog();
		gameOpenLogQuery.setGameType(Integer.parseInt(gameType));
		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByLast(gameOpenLogQuery);
		int status=openTimeUtil.curGameStatus(openLog);
		if(status==4){
			status=1;
			result.put("status",1 );
			openLog.setGameNum((Long.parseLong(openLog.getGameNum())+1)+"");
			openLog.setGameResultDesc("?+?+?=?");
			openLog.setResultType("类型");
		}else{
			result.put("status",status );
		}
		
		
		//开奖期数
		if(list!=null&&list.size()>0){
			result.put("game_num",Long.parseLong(openLog.getGameNum())+1);
			result.put("first_result",openLog);
		}
		if("1".equals(gameType)){
			Long seconds= openTimeUtil.getNextOpenTimeByBJKL( );
			if(status==1){
				seconds=seconds-openTimeUtil.beijingStartTime;
				if(seconds<0){
					seconds=0L;
				}
			}
			
			result.put("seconds", seconds);
		}else if("2".equals(gameType)){
			Long seconds=openTimeUtil.getNextOpenTimeByJND( );
			if(status==1){
				seconds=seconds-openTimeUtil.JNDStartTime;
				if(seconds<0){
					seconds=0L;
				}
			}
			result.put("seconds",seconds);
		}
		
		RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
		if(roomInfo!=null){
			
			result.put("per_max_point",roomInfo.getPerMaxPoint());
			result.put("per_min_point",roomInfo.getPerMinPoint());
			result.put("all_max_point",roomInfo.getAllMaxPoint());
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		 
	}
	
	 
	 
//	public  Long getNextOpenTimeByJND(Date date) {
//		GameOpenLog gameOpenLog=new GameOpenLog();
//		gameOpenLog.setGameType(2);
//		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByLast(gameOpenLog);
//		
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
//		String openFormat=sdf1.format(openLog.getOpenTime());
//		long dateTime=openLog.getOpenTime().getTime() ;
//		if(new Date().getTime()- dateTime>210*1000L){
//			//离上一次开奖时间超出5分钟返回停售
//			System.out.println("离上一次开奖时间超出5分钟返回停售");
//			return 0L;
//		}
//		
//		
//		try {
//			if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:04").getTime()>new Date().getTime()){
//				System.out.println("超出停售时间 停售");
//				return 0L;
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Long seconds=openLog.getOpenTime().getTime()+210*1000L;
//		
//				
//		return (seconds-new Date().getTime()) /1000;
//	}
//	/**
//	 * 获取北京快乐8 下一期开奖时间
//	 * @param date
//	 * @param n
//	 * @return
//	 */
//	public  Date getNextOpenTime(Date date) {
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		//当前分钟数
//		int minute=c.get(Calendar.MINUTE);
//		//分钟十位数
//		int shiWei=minute/10;
//		//分钟个位数
//		int geWei=minute%10;
//		
//		//计算下一次开奖的分钟数
//		if(geWei>=5){
//			shiWei=shiWei*10+10;
//		}else{
//			shiWei=shiWei*10+5;
//		}
//		
//		 
//		c.set(Calendar.MINUTE, shiWei);
//		c.set(Calendar.SECOND, 0);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return c.getTime();
//	}
	
	
//	/**
//	 * 是否可以下注  1正常 
//	 * 				2封盘  (开奖时间的前15秒 以及后20秒)
//	 * 				3停售 （北京   09：00 -23：55） (加拿大  21：00到次日20：00)
//	 * @param openLog
//	 */
//	public static int isCanAdd(GameOpenLog openLog){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
//		String openFormat=sdf1.format(openLog.getOpenTime());
//		//北京快8
////		return 1;
//		if(openLog!=null&&openLog.getGameType()==1){
//			 
//			long dateTime=openLog.getOpenTime().getTime() ;
//			//20秒之外 判断离下一次还有多少时间
//			dateTime=openLog.getOpenTime().getTime()+5*60*1000L ;
//			//离下一期开奖时间15秒之内 返回封盘
//			if(dateTime-new Date().getTime()<45*1000L){
//				System.out.println("离下一期开奖时间45秒之内 返回封盘");
//				return 2;
//			} else{
//				try {
//					//超出停售时间 停售
//					if(sdf.parse(openFormat+" 23:55").getTime()<new Date().getTime()){
//						System.out.println("超出停售时间 停售");
//						return 3;
//					}else{
//						return 1;
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			
//		}else if(openLog!=null&&openLog.getGameType()==2){
//			long dateTime=openLog.getOpenTime().getTime() ;
//			//20秒之外 判断离下一次还有多少时间
//			dateTime=openLog.getOpenTime().getTime()+210*1000L ;
//			//离下一期开奖时间15秒之内 返回封盘
//			if(dateTime-new Date().getTime()<45*1000L){
//				System.out.println("离下一期开奖时间45秒之内 返回封盘");
//				return 2;
//			} else{
//				try {
//					//超出停售时间 停售
//					if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:04").getTime()>new Date().getTime()){
//						System.out.println("超出停售时间 停售");
//						return 3;
//					}else{
//						return 1;
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			
//		}
//		
//		return 2;
//	}
}

