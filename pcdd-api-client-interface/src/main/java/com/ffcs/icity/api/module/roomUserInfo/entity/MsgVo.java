package com.ffcs.icity.api.module.roomUserInfo.entity;

import java.util.Date;

import org.apache.log4j.chainsaw.Main;

import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.util.JSONHelper;

public class MsgVo {
	private Integer noticeType;//消息类型  1进出房间消息   2下注  3开奖信息  4封盘信息

	
	private String gameCount; //期数
	
	private String gameType;  //投注类型
	
	private Double point;  //金额
	
	private String imAccount;//用户环信号

	
	private String nickName;//头像
	
	private String userPhoto;//昵称
	
	/**
     * 等级       db_column: LEVEL 
     */ 	
	private java.lang.Integer level;
	
	private java.lang.Long seconds;//剩余时间
	
	private String extContent;
	
	private Date createTime;
	
	private Integer biliId;

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	 

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public String getImAccount() {
		return imAccount;
	}

	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getExtContent() {
		return extContent;
	}

	public void setExtContent(String extContent) {
		this.extContent = extContent;
	}
	
	public MsgVo( ) {
		super();
		this.noticeType = 1;
		this.gameType = "极大";
		this.point = 125.0;
		this.imAccount = "u_37";
		this.nickName = "昵称";
		this.userPhoto = "头像";
		this.extContent = "消息";
		this.createTime=new Date();
	}
	
	public MsgVo(UserInfo userInfo ) {
		super();
		 
		this.imAccount = userInfo.getImAccount();
		this.nickName = userInfo.getNickName();
		this.userPhoto = userInfo.getUserPhoto();
		this.createTime=new Date();
		this.level=userInfo.getLevel();
	}

	public static void main(String[] args) {
		MsgVo vo=new MsgVo();
		
		try {
			System.out.println(JSONHelper.toJSONString(vo,true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public java.lang.Integer getLevel() {
		return level;
	}

	public void setSeconds(java.lang.Long seconds) {
		this.seconds = seconds;
	}

	public java.lang.Long getSeconds() {
		return seconds;
	}

	public void setGameCount(String gameCount) {
		this.gameCount = gameCount;
	}

	public String getGameCount() {
		return gameCount;
	}

	public void setBiliId(Integer biliId) {
		this.biliId = biliId;
	}

	public Integer getBiliId() {
		return biliId;
	}
}
