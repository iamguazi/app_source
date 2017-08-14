package com.web.act.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GameOpenLog  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
    /**
     * 1北京快乐8 2加拿大快乐8       db_column: GAME_TYPE 
     */ 	
	private java.lang.Integer gameType;
	
    /**
     * 开奖日期       db_column: OPEN_TIME 
     */ 	
	private java.util.Date openTime;
	
    /**
     * 开奖期数       db_column: GAME_NUM 
     */ 	
	private java.lang.String gameNum;
	
    /**
     * 开奖结果 （数字）       db_column: GAME_RESULT 
     */ 	
	private java.lang.Integer gameResult;
	
    /**
     * 开奖结果 （含式子）       db_column: GAME_RESULT_DESC 
     */ 	
	private java.lang.String gameResultDesc;
	
    /**
     * 开奖类型       db_column: RESULT_TYPE 
     */ 	
	private java.lang.String resultType;
	
    /**
     * 是否豹子       db_column: IS_BAOZI 
     */ 	
	private java.lang.Integer isBaozi;
	
    /**
     * 积分亏盈       db_column: XHIBIT_POINT 
     */ 	
	private java.lang.Double xhibitPoint;
	
    /**
     * 总积分       db_column: TOTAL_POINT 
     */ 	
	private java.lang.Double totalPoint;
	
    /**
     * 是否派奖       db_column: IS_GIVE 
     */ 	
	private java.lang.Integer isGive;
	
    /**
     * 派发总积分       db_column: GIVE_POINT 
     */ 	
	private java.lang.Double givePoint;
	
	//columns END

	public GameOpenLog(){
	}

	public GameOpenLog(
		java.lang.Integer id
	){
		this.id = id;
	}

		
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}		
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}		
	
		
	public void setGameType(java.lang.Integer value) {
		this.gameType = value;
	}
	
	public java.lang.Integer getGameType() {
		return this.gameType;
	}		
	
	public void setOpenTime(java.util.Date value) {
		this.openTime = value;
	}
	
	public java.util.Date getOpenTime() {
		return this.openTime;
	}
	
	
		
	public void setGameNum(java.lang.String value) {
		this.gameNum = value;
	}
	
	public java.lang.String getGameNum() {
		return this.gameNum;
	}		
	
		
	public void setGameResult(java.lang.Integer value) {
		this.gameResult = value;
	}
	
	public java.lang.Integer getGameResult() {
		return this.gameResult;
	}		
	
		
	public void setGameResultDesc(java.lang.String value) {
		this.gameResultDesc = value;
	}
	
	public java.lang.String getGameResultDesc() {
		return this.gameResultDesc;
	}		
	
		
	public void setResultType(java.lang.String value) {
		this.resultType = value;
	}
	
	public java.lang.String getResultType() {
		return this.resultType;
	}		
	
		
	public void setIsBaozi(java.lang.Integer value) {
		this.isBaozi = value;
	}
	
	public java.lang.Integer getIsBaozi() {
		return this.isBaozi;
	}		
	
		
	public void setXhibitPoint(java.lang.Double value) {
		this.xhibitPoint = value;
	}
	
	public java.lang.Double getXhibitPoint() {
		return this.xhibitPoint;
	}		
	
		
	public void setTotalPoint(java.lang.Double value) {
		this.totalPoint = value;
	}
	
	public java.lang.Double getTotalPoint() {
		return this.totalPoint;
	}		
	
		
	public void setIsGive(java.lang.Integer value) {
		this.isGive = value;
	}
	
	public java.lang.Integer getIsGive() {
		return this.isGive;
	}		
	
		
	public void setGivePoint(java.lang.Double value) {
		this.givePoint = value;
	}
	
	public java.lang.Double getGivePoint() {
		return this.givePoint;
	}		
	

	
}

