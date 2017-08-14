package com.web.act.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.act.dao.GameOpenLogDao;
import com.web.act.vo.GameOpenLog;

public class OpenTimeUtil {
	//开盘时间 北京  每隔五分钟开奖一次  这里设定 下一期开奖时间15:00:00   15:05:00  实际开奖会在此基础上延迟10几秒 
	//所以要加上最大延迟时间  如果超出这个时间  可能是系统异常 返回停售   
 
   
	
	
	/**
	 * 规范化本期开奖时间
	 * 北京     15:05:16  -->15:05:00
	 * 加拿大 15:05:16-->15:05:10      15:05:45-->15:05:40
	 */
	public static GameOpenLog getFormatCurTime(GameOpenLog openLog){
		
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
	 * 获取北京快乐8 下一期开奖时间（单位秒）
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNextOpenTimeByBJKL(GameOpenLog gameOpenLog) {
		 
		GameOpenLog openLog = getFormatCurTime(gameOpenLog);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		long dateTime=openLog.getOpenTime().getTime() ;
		if(new Date().getTime()- dateTime>Config.beijingTime*1000L+Config.beijingEndTime*1000L){
			//离上一次开奖时间超出5分钟返回停售
			System.out.println("离上一次开奖时间超出5分半钟返回停售");
			return null;
		}
		
		
		try {
			if(sdf.parse(openFormat+" 06:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 06:04").getTime()>new Date().getTime()){
				System.out.println("超出停售时间 停售");
				return null;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(openLog.getOpenTime());
		c.set(Calendar.SECOND, c.get(Calendar.SECOND)+Config.beijingTime);
		 
		
		 
		return c.getTime();
	}
	
	
	/**
	 * 获取加拿大快乐8 下一期开奖时间（单位秒）
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNextOpenTimeByJND(GameOpenLog gameOpenLog) {
		GameOpenLog openLog = getFormatCurTime(gameOpenLog);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String openFormat=sdf1.format(openLog.getOpenTime());
		long dateTime=openLog.getOpenTime().getTime() ;
		if(new Date().getTime()- dateTime>Config.JNDTime*1000L+Config.JNDEndTime*1000L){
			//离上一次开奖时间超出4分钟返回停售
			System.out.println("离上一次开奖时间超出4分钟返回停售");
			return null;
		}
		
		
		try {
			if(sdf.parse(openFormat+" 20:00").getTime()<new Date().getTime()&&sdf.parse(openFormat+" 20:04").getTime()>new Date().getTime()){
				System.out.println("超出停售时间 停售");
				return null;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(openLog.getOpenTime());
		c.set(Calendar.SECOND, c.get(Calendar.SECOND)+Config.JNDTime);
		 
		
		 
		return c.getTime();
	}
	
}
