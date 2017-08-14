package com.web.act.job;


import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.web.act.utils.Config;
import com.web.act.utils.OpenTimeUtil;
import com.web.act.utils.UrlUtil;
import com.web.act.vo.*;
import com.web.act.dao.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("OpenCloseJob")
public class OpenCloseJob {
	
	@Autowired(required = true)
	private GameOpenLogDao gameOpenLogDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseJob.class);
	
 
	
	public void run(){
		LOGGER.warn("=====================定时发送北京开盘 封盘消息开始===========================");
	 
		
		  if(Config.beiJingNextTime==null&&Config.beiJingNextSeconds==0){
			  GameOpenLog gameOpenLogQuery=new GameOpenLog();
			  gameOpenLogQuery.setGameType(1);
			  GameOpenLog gameOpenLog=gameOpenLogDao.getGameOpenLogByLast(gameOpenLogQuery);
			  Date date = OpenTimeUtil.getNextOpenTimeByBJKL(gameOpenLog);
			 
			  if(date==null){
				  Config. beiJingNextSeconds=60L;
			  }else{
				  Config.beiJingNum=Long.parseLong(gameOpenLog.getGameNum())+1;
				  if(new Date().getTime()-date.getTime()>0){
					  date=new Date(date.getTime()+Config.beijingTime*1000L);
					  Config.beiJingNum++;
				  }
				  Config.beiJingNextTime= date;
				  System.out.println("下次开奖时间"+date.toString());
			  }
			  
		  }else if(Config.beiJingNextSeconds!=0){
			  Config.beiJingNextSeconds--;
			  System.out.println("倒计时："+ Config.beiJingNextSeconds);
		  } else if(Config.beiJingNextTime!=null){
			  
			  if(Config.beiJingNextTime.getTime()-new Date().getTime()<-200){
				  Config.beiJingNextTime=null;
				  Config.beiJingNum=0;
			  }else{
				  System.out.println(Math.abs(Config.beiJingNextTime.getTime()-new Date().getTime()));
				   if(Math.abs(Config.beiJingNextTime.getTime()-new Date().getTime()-Config.beijingStartTime*1000L)<500){
					  System.out.println("发送封盘消息");
					  UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/open?game_type=1&is_close=1&game_num="+Config.beiJingNum);
//					  NumCon.beiJingNextTime=null;
//					  NumCon.beiJingNum=0;
				  }else
				  if(Math.abs(Config.beiJingNextTime.getTime()-new Date().getTime())<500){
					  System.out.println("发送开盘消息");
					  Config.beiJingNextTime=null;
					  UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/open?game_type=1&is_close=0&game_num="+(Config.beiJingNum));
					  Config.beiJingNum=0;
				  }
			  }
			  
			  
		  }
		  LOGGER.warn("=====================定时发送北京开盘 封盘消息结束===========================");
		  LOGGER.warn("=====================定时发送加拿大开盘 封盘消息开始===========================");
			 
		  if(Config.jiaNaDaNextTime==null&&Config.jiaNaDaNextSeconds==0){
			  GameOpenLog gameOpenLogQuery=new GameOpenLog();
			  gameOpenLogQuery.setGameType(2);
			  GameOpenLog gameOpenLog=gameOpenLogDao.getGameOpenLogByLast(gameOpenLogQuery);
			  Date date = OpenTimeUtil.getNextOpenTimeByJND(gameOpenLog);
			  if(date==null){
				  Config. jiaNaDaNextSeconds=60L;
			  }else{
				  
				  Config.jiaNaDaNum=Long.parseLong(gameOpenLog.getGameNum())+1;
				  if(new Date().getTime()-date.getTime()>0){
					  date=new Date(date.getTime()+Config.JNDTime*1000L);
					  Config.jiaNaDaNum++;
				  }
				  Config.jiaNaDaNextTime= date;
				  System.out.println("下次开奖时间"+date.toString());
			  }
			  
		  }else if(Config.jiaNaDaNextSeconds!=0){
			  Config.jiaNaDaNextSeconds--;
			  System.out.println("倒计时："+ Config.jiaNaDaNextSeconds);
		  } else if(Config.jiaNaDaNextTime!=null){
			  if(Config.jiaNaDaNextTime.getTime()-new Date().getTime()<-200){
				  Config.jiaNaDaNextTime=null;
				  Config.jiaNaDaNum=0;
			  }else{
				  System.out.println(Math.abs(Config.jiaNaDaNextTime.getTime()-new Date().getTime()));
				   if(Math.abs(Config.jiaNaDaNextTime.getTime()-new Date().getTime()-Config.JNDStartTime*1000L)<500){
					  System.out.println("发送封盘消息");
					  UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/open?game_type=2&is_close=1&game_num="+Config.jiaNaDaNum);
				  }else  if(Math.abs(Config.jiaNaDaNextTime.getTime()-new Date().getTime())<500){
					  System.out.println("发送开盘消息");
					  Config.jiaNaDaNextTime=null;
					  UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/open?game_type=2&is_close=0&game_num="+(Config.jiaNaDaNum));
					  Config.jiaNaDaNum=0;
				  }
			  }
			 
		  }
		  LOGGER.warn("=====================定时发送加拿大开盘 封盘消息结束===========================");
			
	}
	
	 public static int getRandomNum(int min,int max) {
	        
	        Random random = new Random();
	        				 
	        int s = random.nextInt(max)%(max-min+1) + min;
	        return s;
	  }
	 public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println(getRandomNum(1, 10));;
		}
	}
}
