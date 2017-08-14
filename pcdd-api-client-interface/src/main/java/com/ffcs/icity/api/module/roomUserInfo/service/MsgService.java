package com.ffcs.icity.api.module.roomUserInfo.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobMessages;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.roomUserInfo.entity.MsgVo;
import com.ffcs.icity.api.module.userChoiceLog.entity.UserChoiceLog;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.util.JSONHelper;

@Service
public class MsgService {
	@Autowired
	private IRoomInfoDao roomInfoDao;
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	
	//进入房间
	public void sendJoinMsg(String roomId,int userId){
		UserInfo userInfo = userInfoDao.getUserInfoById(userId);
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		MsgVo vo=new MsgVo(userInfo);
		vo.setNoticeType(1);
		sendMsg(roomId, vo  ,userInfo.getImAccount());
	}
	
	//下注
	public void sendAddPointMsg(String roomId,int userId,UserChoiceLog choiceLog){
		UserInfo userInfo = userInfoDao.getUserInfoById(userId);
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		MsgVo vo=new MsgVo(userInfo);
		vo.setNoticeType(2);
		vo.setGameCount(choiceLog.getChoiceNo());//期数
		vo.setPoint(choiceLog.getPoint());//押注积分
		vo.setGameType(choiceLog.getChoiceName());//选择的开奖名称
		vo.setBiliId(choiceLog.getBiliId());
		sendMsg(roomId, vo  ,userInfo.getImAccount());
	}
	

	//开奖
	public void sendOpenMsg(GameOpenLog gameOpenLog){
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		MsgVo vo=new MsgVo();
		vo.setNoticeType(3);
		vo.setGameCount(gameOpenLog.getGameNum());//期数
		vo.setGameType(gameOpenLog.getGameType()+"");//选择的开奖名称
		vo.setExtContent(gameOpenLog.getGameResultDesc());
		if(gameOpenLog.getGameType()==1){
			
			for(int i=1;i<=12;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}else if(gameOpenLog.getGameType()==2){
			for(int i=12;i<=24;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}
		
	}
	
	//封盘
	public void closeMsg(String gameNum,int gameType){
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		MsgVo vo=new MsgVo();
		vo.setNoticeType(4);
		vo.setGameCount(gameNum);//期数
		vo.setGameType(gameType+"");//选择的开奖名称
		vo.setExtContent("");
		if(gameType==1){
			for(int i=1;i<=12;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}else if(gameType==2){
			for(int i=12;i<=24;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}
		
	}
	
	//开盘
	public void openMsg(String gameNum,int gameType){
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		MsgVo vo=new MsgVo();
		vo.setNoticeType(5);
		vo.setGameCount((Long.parseLong(gameNum)+1)+"");//期数
		vo.setGameType(gameType+"");//选择的开奖名称
		vo.setExtContent("单注1起,20000封顶，总注800000封顶");
		RoomInfo roomInfo=new RoomInfo();
		DecimalFormat decimalFormat = new DecimalFormat("##0.0");
		if(gameType==1){
			roomInfo.setGameType(gameType);
			List<RoomInfo> list = roomInfoDao.findByCondition(roomInfo);
			for(RoomInfo room:list){   
				vo.setExtContent("单注"+room.getPerMinPoint()+"起,"+decimalFormat.format(room.getPerMaxPoint())+"封顶，总注"+decimalFormat.format(room.getAllMaxPoint())+"封顶");
				sendMsg(room.getId()+"", vo, "admin");
			}
			 
		}else if(gameType==2){
			roomInfo.setGameType(gameType);
			List<RoomInfo> list = roomInfoDao.findByCondition(roomInfo);
			for(RoomInfo room:list){
				vo.setExtContent("单注"+room.getPerMinPoint()+"起,"+decimalFormat.format(room.getPerMaxPoint())+"封顶，总注"+decimalFormat.format(room.getAllMaxPoint())+"封顶");
				sendMsg(room.getId()+"", vo, "admin");
			}
		}
		
	}
	
	//60秒倒计时提示
	public void daojishiMsg(String gameNum,int gameType){
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息 5开盘 6倒计时
		MsgVo vo=new MsgVo();
		vo.setNoticeType(6);
		vo.setGameCount(gameNum);//期数
		vo.setGameType(gameType+"");//选择的开奖名称
		vo.setExtContent("距封盘还有60秒，请抓紧时间下注");
		if(gameType==1){
			for(int i=1;i<=12;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}else if(gameType==2){
			for(int i=12;i<=24;i++){
				sendMsg(i+"", vo  ,"admin");
			}
		}
		
	}
	/**
	 * 
	 * @param roomId      房间id
	 * @param msgContent  消息内容
	 * @param from        来自哪个帐号
	 * @param noticeType  消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
	 */
	public void sendMsg(String roomId,MsgVo vo,String from){
		//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息
		 ObjectNode txtmsg = factory.objectNode();
	        try {
				txtmsg.put("msg", JSONHelper.toJSONString(vo, true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        txtmsg.put("type","txt");
		RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
		 ObjectNode ext = factory.objectNode();
		 // 给一个群组发文本消息
        String targetTypegr = "chatgroups";
        ArrayNode targetgroup = factory.arrayNode();
        targetgroup.add(roomInfo.getImGourpId());
        ObjectNode sendTxtMessagegroupnode =EasemobMessages  . sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);
//		if (null != sendTxtMessagegroupnode) {
//			System.out.println("给一个群组发文本消息: " + sendTxtMessagegroupnode.toString());
//        }
	}
	
	public static void main(String[] args) {
		Double a=1234567819.0;
		DecimalFormat decimalFormat = new DecimalFormat("##0.0");
		System.out.println(decimalFormat.format(a));
	}
}
