package com.ffcs.icity.api.module.pointChangeLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class PointChangeLog  {
	
	
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
     * 积分增减       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 积分变动描述       db_column: POINT_DESC 
     */ 	
	private java.lang.String pointDesc;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	//0后台充值 1支付宝  2微信  3mo宝 4爱益
	private Integer payType;
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public PointChangeLog(){
	}

	public PointChangeLog(
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
	
		
	public void setPoint(java.lang.Double value) {
		this.point = value;
	}
	
	public java.lang.Double getPoint() {
		return this.point;
	}		
	
		
	public void setPointDesc(java.lang.String value) {
		this.pointDesc = value;
	}
	
	public java.lang.String getPointDesc() {
		return this.pointDesc;
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

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayType() {
		return payType;
	}
}

