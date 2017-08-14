package com.ffcs.icity.api.module.userLevel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserLevel  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * levelName       db_column: LEVEL_NAME 
     */ 	
	private java.lang.String levelName;
	
    /**
     * rechargeFee       db_column: RECHARGE_FEE 
     */ 	
	private java.lang.Double rechargeFee;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * 等级图标       db_column: LEVEL_ICON 
     */ 	
	private java.lang.String levelIcon;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public UserLevel(){
	}

	public UserLevel(
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
	
		
	public void setLevelName(java.lang.String value) {
		this.levelName = value;
	}
	
	public java.lang.String getLevelName() {
		return this.levelName;
	}		
	
		
	public void setRechargeFee(java.lang.Double value) {
		this.rechargeFee = value;
	}
	
	public java.lang.Double getRechargeFee() {
		return this.rechargeFee;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setLevelIcon(java.lang.String value) {
		this.levelIcon = value;
	}
	
	public java.lang.String getLevelIcon() {
		return this.levelIcon;
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
}

