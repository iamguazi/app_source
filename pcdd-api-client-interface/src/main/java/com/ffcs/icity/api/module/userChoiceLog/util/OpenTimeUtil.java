package com.ffcs.icity.api.module.userChoiceLog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ffcs.icity.api.module.gameOpenLog.dao.IGameOpenLogDao;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;

@Service
public class OpenTimeUtil {
	//开盘时间 北京  每隔五分钟开奖一次  这里设定 下一期开奖时间15:00:00   15:05:00  实际开奖会在此基础上延迟10几秒 
	//所以要加上最大延迟时间  如果超出这个时间  可能是系统异常 返回停售   
	@Value("${beijingStartTime}")
	public int  beijingStartTime;//北京快8 提前封盘时间 （单位 秒）
	
	@Value("${beijingEndTime}")
	public int  beijingEndTime;//北京快8  允许开盘的最大延迟时间 （单位 秒）
	
	@Value("${beijingTime}")
	public int  beijingTime;//北京快8 间隔开奖时间
	
	@Value("${JNDStartTime}")
	public int  JNDStartTime;//加拿大快8 提前封盘时间 （单位 秒）
	
	@Value("${JNDEndTime}")
	public int  JNDEndTime;//加拿大快8 提前封盘时间 （单位 秒）
	
	@Value("${JNDTime}")
	public int  JNDTime;//加拿大快8间隔开奖时间 
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	/**
	 * 机器人是否可以下注  1正常 
	 * 				2封盘  (开奖时间的前15秒 以及后20秒)
	 * 				3停售 （北京   09：00 -23：55） (加拿大  21：00到次日20：00)
	 * @param openLog
	 */
	public   int RebotIsCanAdd(GameOpenLog openLog){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		
		openLog=getFormatCurTime(openLog);
		String openFormat=sdf1.format(openLog.getOpenTime());
		//北京快8
//		return 1;
		if(openLog!=null&&openLog.getGameType()==1){
			 
			//判断上期时间20秒之后才能开始下注
			long dateTime=openLog.getOpenTime().getTime() ;
			if(new Date().getTime()- dateTime>beijingTime*1000L){
				//离上一次开奖时间超出5分钟返回停售
				System.out.println("离上一次开奖时间超出5分钟返回停售");
				return 3;
			}
			else{
				//20秒之外 判断离下一次还有多少时间
				dateTime=openLog.getOpenTime().getTime()+beijingTime*1000L ;
				if(dateTime-new Date().getTime()<beijingStartTime*1000L){
					System.out.println("离下一期开奖时间45秒之内 返回封盘");
					return 2;
				} else{
					try {
						//超出停售时间 停售
						if(sdf.parse(openFormat+" 23:55").getTime()<new Date().getTime() ){
							System.out.println("超出停售时间 停售");
							return 3;
						}else{
							return 1;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
			
			
		}else if(openLog!=null&&openLog.getGameType()==2){
			//判断上期时间20秒之后才能开始下注
			long dateTime=openLog.getOpenTime().getTime() ;
			//20秒之内 返回封盘
			if(new Date().getTime()- dateTime<20*1000L){
				System.out.println("20秒之内 返回封盘");
				return 2;
			}
//			else if(new Date().getTime()- dateTime>210*1000L){
//				//离上一次开奖时间超出5分钟返回停售
//				System.out.println("离上一次开奖时间超出5分钟返回停售");
//				return 3;
//			}
			else{
				//20秒之外 判断离下一次还有多少时间
				dateTime=openLog.getOpenTime().getTime()+JNDTime*1000L ;
				//离下一期开奖时间15秒之内 返回封盘
				if(dateTime-new Date().getTime()<JNDStartTime*1000L){
					System.out.println("离下一期开奖时间35秒之内 返回封盘");
					return 2;
				} else{
					try {
						//超出停售时间 停售
						if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:06").getTime()>new Date().getTime()){
							System.out.println("超出停售时间 停售");
							return 3;
						}else{
							return 1;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
			
			
		}
		
		return 2;
	}
	
	
	
	
	
	
	
	

	/**
	 * 是否可以下注  1正常 
	 * 				2封盘  (开奖时间的前15秒 以及后20秒)
	 * 				3停售 （北京   09：00 -23：55） (加拿大  21：00到次日20：00)
	 * @param openLog
	 */
	public   int isCanAdd(GameOpenLog openLog){
		openLog=getFormatCurTime(openLog);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		//北京快8
//		return 1;
		if(openLog!=null&&openLog.getGameType()==1){
			 
			long dateTime= openLog.getOpenTime().getTime()+beijingTime*1000L ;
			//离下一期开奖时间15秒之内 返回封盘
			if(dateTime-new Date().getTime()<beijingStartTime*1000L){
				System.out.println("离下一期开奖时间45秒之内 返回封盘");
				return 2;
			} else{
				try {
					//超出停售时间 停售
					if(sdf.parse(openFormat+" 23:55").getTime()<new Date().getTime() ){
						System.out.println("超出停售时间 停售");
						return 3;
					}else{
						return 1;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}else if(openLog!=null&&openLog.getGameType()==2){
			long dateTime=openLog.getOpenTime().getTime() ;
			//20秒之外 判断离下一次还有多少时间
			dateTime=openLog.getOpenTime().getTime()+JNDTime*1000L ;
			//离下一期开奖时间15秒之内 返回封盘
			if(dateTime-new Date().getTime()<JNDStartTime*1000L){
				System.out.println("离下一期开奖时间45秒之内 返回封盘");
				return 2;
			} else{
				try {
					//超出停售时间 停售
					if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:04").getTime()>new Date().getTime()){
						System.out.println("超出停售时间 停售");
						return 3;
					}else{
						return 1;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		return 2;
	}
	
	
	
	
	/**
	 * 当前游戏状态     1正常 
	 * 				2封盘  (开奖时间的前15秒 以及后20秒)
	 * 				3停售 （北京   09：00 -23：55） (加拿大  21：00到次日20：00)
	 * 				4 正常开盘 上期结果未显示
	 * @param openLog
	 */
	public   int curGameStatus(GameOpenLog openLog){
		openLog=getFormatCurTime(openLog);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		//北京快8
//		return 1;
		if(openLog!=null&&openLog.getGameType()==1){
			 
			long dateTime= openLog.getOpenTime().getTime()+beijingTime*1000L ;
			//离下一期开奖时间45秒之内 且未超过开奖时间 返回封盘
			if(0<=dateTime-new Date().getTime()&&dateTime-new Date().getTime()<beijingStartTime*1000L){
				System.out.println("离下一期开奖时间45秒之内 返回封盘");
				return 2;
			}else if(new Date().getTime()-dateTime>0&&new Date().getTime()-dateTime<beijingEndTime*1000L){
				//超过开奖时间  未开奖
				return 4;
			}else if(new Date().getTime()-dateTime>=beijingEndTime*1000){
				//超过开奖时间  5分半未开奖  封盘
				return 3;
			}else{
				try {
					//超出停售时间 停售
					if(sdf.parse(openFormat+" 23:55").getTime()<new Date().getTime()){
						System.out.println("超出停售时间 停售");
						return 3;
					}else{
						return 1;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}else if(openLog!=null&&openLog.getGameType()==2){
			long dateTime=openLog.getOpenTime().getTime() ;
			//20秒之外 判断离下一次还有多少时间
			dateTime=openLog.getOpenTime().getTime()+JNDTime*1000L ;
			
			//离下一期开奖时间45秒之内 且未超过开奖时间 返回封盘
			if(0<=dateTime-new Date().getTime()&&dateTime-new Date().getTime()<JNDStartTime*1000L){
				System.out.println("离下一期开奖时间45秒之内 返回封盘");
				return 2;
			}else if(new Date().getTime()-dateTime>0&&new Date().getTime()-dateTime<JNDEndTime*1000L){
				//超过开奖时间  未开奖
				System.out.println("超过开奖时间  未开奖");
				return 4;
			}else if(new Date().getTime()-dateTime>=JNDEndTime*1000){
				//超过开奖时间  5分半未开奖  封盘
				System.out.println("超过开奖时间  5分半未开奖  封盘");
				return 3;
			}else{
				try {
					//超出停售时间 停售
					if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:06").getTime()>new Date().getTime()){
						System.out.println("超出停售时间 停售");
						return 3;
					}else{
						return 1;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		return 2;
	}
	
//	/**
//	 * 获取北京快乐8 下一期开奖时间 （单位秒）
//	 * @param date
//	 * @param n
//	 * @return
//	 */
//	public  Long getNextOpenTime(Date date) {
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
//		return (c.getTime().getTime()-new Date().getTime()) /1000;
//	}
	
	
	/**
	 * 规范化本期开奖时间
	 * 北京     15:05:16  -->15:05:00
	 * 加拿大 15:05:16-->15:05:10      15:05:45-->15:05:40
	 */
	public GameOpenLog getFormatCurTime(GameOpenLog openLog){
		
		Calendar c = Calendar.getInstance();
		if(openLog.getGameType()==1){
			//规范化 北京快乐8本期开奖时间
			c.setTime(openLog.getOpenTime());
			//当前秒数
			c.set(Calendar.SECOND, 0);
		}else if(openLog.getGameType()==2){
			//规范化加拿大本期开奖时间
			c.setTime(openLog.getOpenTime());
			//当前秒数
			int curSeconds=c.get(Calendar.SECOND);
			if(curSeconds<30){
				c.set(Calendar.SECOND, 10);
			}else{
				c.set(Calendar.SECOND, 40);
			}
			
		}
		openLog.setOpenTime(c.getTime());
		return openLog;
	}
	 
	/**
	 * 规范化本期开奖时间
	 * 北京     15:05:16  -->15:05:00
	 * 加拿大 15:05:16-->15:05:10      15:05:45-->15:05:40
	 */
	public GameOpenLog getFormatCurTime(int gameType){
		GameOpenLog gameOpenLog=new GameOpenLog();
		gameOpenLog.setGameType(gameType);
		GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByLast(gameOpenLog);
		
		Calendar c = Calendar.getInstance();
		if(gameType==1){
			//规范化 北京快乐8本期开奖时间
			c.setTime(openLog.getOpenTime());
			//当前秒数
			c.set(Calendar.SECOND, 0);
		}else if(gameType==2){
			//规范化加拿大本期开奖时间
			c.setTime(openLog.getOpenTime());
			//当前秒数
			int curSeconds=c.get(Calendar.SECOND);
			if(curSeconds<30){
				c.set(Calendar.SECOND, 10);
			}else{
				c.set(Calendar.SECOND, 40);
			}
			
		}
		openLog.setOpenTime(c.getTime());
		return openLog;
	}
	
	/**
	 * 获取北京快乐8 下一期开奖时间（单位秒）
	 * @param date
	 * @param n
	 * @return
	 */
	public  Long getNextOpenTimeByBJKL() {
		 
		GameOpenLog openLog = getFormatCurTime(1);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		long dateTime=openLog.getOpenTime().getTime() ;
		if(new Date().getTime()- dateTime>beijingTime*1000L+beijingEndTime*1000L){
			//离上一次开奖时间超出5分钟返回停售
			System.out.println("离上一次开奖时间超出5分半钟返回停售");
			return 0L;
		}
		
		
		try {
			if(sdf.parse(openFormat+" 23:55").getTime()<new Date().getTime()){
				System.out.println("超出停售时间 停售");
				return 0L;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long seconds=openLog.getOpenTime().getTime()+beijingTime*1000L;
		if(new Date().getTime()-seconds>0&&new Date().getTime()-seconds<30*1000L){
			seconds=seconds+beijingTime*1000L;
		}
				
		return (seconds-new Date().getTime()) /1000;
	}
	
	/**
	 * 获取加拿大快乐8 下一期开奖时间（单位秒）
	 * @param date
	 * @param n
	 * @return
	 */
	public  Long getNextOpenTimeByJND() {
		GameOpenLog openLog = getFormatCurTime(2);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		long dateTime=openLog.getOpenTime().getTime() ;
		if(new Date().getTime()- dateTime>JNDTime*1000L+JNDEndTime*1000L){
			//离上一次开奖时间超出4分钟返回停售
			System.out.println("离上一次开奖时间超出4分钟返回停售");
			return 0L;
		}
		
		
		try {
			if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:06").getTime()>new Date().getTime()){
				System.out.println("超出停售时间 停售");
				return 0L;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long seconds=openLog.getOpenTime().getTime()+JNDTime*1000L;
		
		if(new Date().getTime()-seconds>0&&new Date().getTime()-seconds<30*1000L){
			seconds=seconds+JNDTime*1000L;
		}	
		return (seconds-new Date().getTime()) /1000;
	}
	
}
