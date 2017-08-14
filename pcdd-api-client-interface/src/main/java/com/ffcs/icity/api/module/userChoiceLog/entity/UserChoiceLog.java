package com.ffcs.icity.api.module.userChoiceLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserChoiceLog  {
	
	
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
     * 投资的房间       db_column: ROOM_ID 
     */ 	
	private java.lang.Integer roomId;
	
    /**
     * 房间等级       db_column: ROOM_LEVEL 
     */ 	
	private java.lang.Integer areaId;
	
    /**
     * 选择的彩票期数       db_column: CHOICE_NO 
     */ 	
	private java.lang.String choiceNo;
	
    /**
     * 选择的开奖结果      1,2 db_column: CHOICE_RESULT 
     */ 	
	private java.lang.String choiceResult;
	
	/**
	 * 选择的开奖名称   大,小
	 */
	private java.lang.String choiceName;
	
    /**
     * 开奖比例       db_column: BILI 
     */ 	
	private java.lang.Double bili;
	
    /**
     * 下注积分       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 实际开奖结果       db_column: REAL_RESULT 
     */ 	
	private java.lang.String realResult;
	
    /**
     * 开奖结果类型       db_column: RESULT_TYPE 
     */ 	
	private java.lang.String resultType;
	
    /**
     * 实际获取积分       db_column: GET_POINT 
     */ 	
	private java.lang.Double getPoint;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * 1北京快乐8 2加拿大快乐8       db_column: GAME_TYPE 
     */ 	
	private java.lang.Integer gameType;
	
    /**
     * 是否中奖       db_column: IS_ZHONG 
     */ 	
	private java.lang.Integer isZhong;
	
    /**
     * biliId       db_column: BILI_ID 
     */ 	
	private java.lang.Integer biliId;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	@JsonIgnore
	private java.lang.String startTime;
	
	@JsonIgnore
	private java.lang.String endTime;
	
	private double allFee;
	
	//columns END

	public UserChoiceLog(){
	}

	public UserChoiceLog(
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
	
		
	public void setRoomId(java.lang.Integer value) {
		this.roomId = value;
	}
	
	public java.lang.Integer getRoomId() {
		return this.roomId;
	}		
	
		
 	
	
		
	public void setChoiceNo(java.lang.String value) {
		this.choiceNo = value;
	}
	
	public java.lang.String getChoiceNo() {
		return this.choiceNo;
	}		
	
		
	public void setChoiceResult(java.lang.String value) {
		this.choiceResult = value;
	}
	
	public java.lang.String getChoiceResult() {
		return this.choiceResult;
	}		
	
		
	public void setBili(java.lang.Double value) {
		this.bili = value;
	}
	
	public java.lang.Double getBili() {
		return this.bili;
	}		
	
		
	public void setPoint(java.lang.Double value) {
		this.point = value;
	}
	
	public java.lang.Double getPoint() {
		return this.point;
	}		
	
		
	public void setRealResult(java.lang.String value) {
		this.realResult = value;
	}
	
	public java.lang.String getRealResult() {
		return this.realResult;
	}		
	
		
	public void setResultType(java.lang.String value) {
		this.resultType = value;
	}
	
	public java.lang.String getResultType() {
		return this.resultType;
	}		
	
		
	public void setGetPoint(java.lang.Double value) {
		this.getPoint = value;
	}
	
	public java.lang.Double getGetPoint() {
		return this.getPoint;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setGameType(java.lang.Integer value) {
		this.gameType = value;
	}
	
	public java.lang.Integer getGameType() {
		return this.gameType;
	}		
	
		
	public void setIsZhong(java.lang.Integer value) {
		this.isZhong = value;
	}
	
	public java.lang.Integer getIsZhong() {
		return this.isZhong;
	}		
	
		
	public void setBiliId(java.lang.Integer value) {
		this.biliId = value;
	}
	
	public java.lang.Integer getBiliId() {
		return this.biliId;
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

	public void setChoiceName(java.lang.String choiceName) {
		this.choiceName = choiceName;
	}

	public java.lang.String getChoiceName() {
		return choiceName;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

	public void setAreaId(java.lang.Integer areaId) {
		this.areaId = areaId;
	}

	public java.lang.Integer getAreaId() {
		return areaId;
	}

	public void setAllFee(double allFee) {
		this.allFee = allFee;
	}

	public double getAllFee() {
		return allFee;
	}
}

