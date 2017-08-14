package com.ffcs.icity.api.module.userHuiShuiLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserHuiShuiLog  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 1初级 2中级 3高级       db_column: TYPE 
     */ 	
	private java.lang.Integer type;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
    /**
     * 下注金额       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 回水比例       db_column: BILI 
     */ 	
	private java.lang.Double bili;
	
    /**
     * 回水金额       db_column: HUI_SHUI_POINT 
     */ 	
	private java.lang.Double huiShuiPoint;
	
    /**
     * 0待确认 1确认 2未满足       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	private java.lang.Double zuhePoint;
	
	private java.lang.Double getPoint;
	
	private java.lang.Double xhibitPoint;
	
	
	private int pointNum;
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public UserHuiShuiLog(){
	}

	public UserHuiShuiLog(
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
	
		
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}		
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}		
	
		
	public void setPoint(java.lang.Double value) {
		this.point = value;
	}
	
	public java.lang.Double getPoint() {
		return this.point;
	}		
	
		
	public void setBili(java.lang.Double value) {
		this.bili = value;
	}
	
	public java.lang.Double getBili() {
		return this.bili;
	}		
	
		
	public void setHuiShuiPoint(java.lang.Double value) {
		this.huiShuiPoint = value;
	}
	
	public java.lang.Double getHuiShuiPoint() {
		return this.huiShuiPoint;
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

	public void setZuhePoint(java.lang.Double zuhePoint) {
		this.zuhePoint = zuhePoint;
	}

	public java.lang.Double getZuhePoint() {
		return zuhePoint;
	}

	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}

	public int getPointNum() {
		return pointNum;
	}

	public void setGetPoint(java.lang.Double getPoint) {
		this.getPoint = getPoint;
	}

	public java.lang.Double getGetPoint() {
		return getPoint;
	}

	public void setXhibitPoint(java.lang.Double xhibitPoint) {
		this.xhibitPoint = xhibitPoint;
	}

	public java.lang.Double getXhibitPoint() {
		return xhibitPoint;
	}
}

