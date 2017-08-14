package com.ffcs.icity.api.module.userFenxiaoLog.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserFenxiaoLog  {
	
	
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
     * 下注金额       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 分销金额       db_column: FENXIAO_POINT 
     */ 	
	private String fenxiaoPoint;
	
    /**
     * 0待确认 1确认 2未满足       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 组合下注金额       db_column: ZUHE_POINT 
     */ 	
	private java.lang.Double zuhePoint;
	
    /**
     * 投注总数       db_column: POINT_NUM 
     */ 	
	private java.lang.Integer pointNum;
	
    /**
     * 赢了多少       db_column: GET_POINT 
     */ 	
	private java.lang.Double getPoint;
	
    /**
     * 积分亏损       db_column: XHIBIT_POINT 
     */ 	
	private java.lang.Double xhibitPoint;
	
	private Integer fenxiaoUserId;
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	private Date createTime;
	
	private String nickName;
	//columns END

	public UserFenxiaoLog(){
	}

	public UserFenxiaoLog(
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
	
		
	 
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setZuhePoint(java.lang.Double value) {
		this.zuhePoint = value;
	}
	
	public java.lang.Double getZuhePoint() {
		return this.zuhePoint;
	}		
	
		
	public void setPointNum(java.lang.Integer value) {
		this.pointNum = value;
	}
	
	public java.lang.Integer getPointNum() {
		return this.pointNum;
	}		
	
		
	public void setGetPoint(java.lang.Double value) {
		this.getPoint = value;
	}
	
	public java.lang.Double getGetPoint() {
		return this.getPoint;
	}		
	
		
	public void setXhibitPoint(java.lang.Double value) {
		this.xhibitPoint = value;
	}
	
	public java.lang.Double getXhibitPoint() {
		return this.xhibitPoint;
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

	public void setFenxiaoUserId(Integer fenxiaoUserId) {
		this.fenxiaoUserId = fenxiaoUserId;
	}

	public Integer getFenxiaoUserId() {
		return fenxiaoUserId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setFenxiaoPoint(String fenxiaoPoint) {
		this.fenxiaoPoint = fenxiaoPoint;
	}

	public String getFenxiaoPoint() {
		return fenxiaoPoint;
	}
}

