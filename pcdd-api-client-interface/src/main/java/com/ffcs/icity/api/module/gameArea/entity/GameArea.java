package com.ffcs.icity.api.module.gameArea.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GameArea  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 1北京28   2加拿大28       db_column: GAME_TYPE 
     */ 	
	private java.lang.Integer gameType;
	
    /**
     * 1初级 2中级 3高级       db_column: AREA_TYPE 
     */ 	
	private java.lang.Integer areaType;
	
    /**
     * 房间名称       db_column: AREA_NAME 
     */ 	
	private java.lang.String areaName;
	
    /**
     * 房间图片       db_column: AREA_PHOTO 
     */ 	
	private java.lang.String areaPhoto;
	
    /**
     * 回水描述       db_column: FEEDBACK_DESC 
     */ 	
	private java.lang.String feedbackDesc;
	
    /**
     * 进入所需最低余额       db_column: MIN_POINT 
     */ 	
	private java.lang.Double minPoint;
	
    /**
     * 1正常 0禁止       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	private int peopleCount;
	//columns END

	public GameArea(){
	}

	public GameArea(
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
	
		
	public void setGameType(java.lang.Integer value) {
		this.gameType = value;
	}
	
	public java.lang.Integer getGameType() {
		return this.gameType;
	}		
	
		
	public void setAreaType(java.lang.Integer value) {
		this.areaType = value;
	}
	
	public java.lang.Integer getAreaType() {
		return this.areaType;
	}		
	
		
	public void setAreaName(java.lang.String value) {
		this.areaName = value;
	}
	
	public java.lang.String getAreaName() {
		return this.areaName;
	}		
	
		
	public void setAreaPhoto(java.lang.String value) {
		this.areaPhoto = value;
	}
	
	public java.lang.String getAreaPhoto() {
		return this.areaPhoto;
	}		
	
		
	public void setFeedbackDesc(java.lang.String value) {
		this.feedbackDesc = value;
	}
	
	public java.lang.String getFeedbackDesc() {
		return this.feedbackDesc;
	}		
	
		
	public void setMinPoint(java.lang.Double value) {
		this.minPoint = value;
	}
	
	public java.lang.Double getMinPoint() {
		return this.minPoint;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
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

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public int getPeopleCount() {
		return peopleCount;
	}
}

