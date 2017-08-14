package com.ffcs.icity.api.module.gameOpenLog.entity;

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
	
	@JsonIgnore
	private Integer start;

	@JsonIgnore
	private Integer pageSize;
	
	private Double xhibitPoint;
	
	private Double totalPoint;
	
	private Integer isGive;
	
	private Double givePoint;
	
	 
	private String color;

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
	


	public Double getXhibitPoint() {
		return xhibitPoint;
	}

	public void setXhibitPoint(Double xhibitPoint) {
		this.xhibitPoint = xhibitPoint;
	}

	public Double getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Double totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getIsGive() {
		return isGive;
	}

	public void setIsGive(Integer isGive) {
		this.isGive = isGive;
	}

	public Double getGivePoint() {
		return givePoint;
	}

	public void setGivePoint(Double givePoint) {
		this.givePoint = givePoint;
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
	
		
	 		
	
		
	public void setIsBaozi(java.lang.Integer value) {
		this.isBaozi = value;
	}
	
	public java.lang.Integer getIsBaozi() {
		return this.isBaozi;
	}		
	

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setResultType(java.lang.String resultType) {
		this.resultType = resultType;
	}

	public java.lang.String getResultType() {
		return resultType;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}


}
	
	


