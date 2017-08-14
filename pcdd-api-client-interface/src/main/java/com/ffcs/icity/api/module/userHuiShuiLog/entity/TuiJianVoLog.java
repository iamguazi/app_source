package com.ffcs.icity.api.module.userHuiShuiLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class TuiJianVoLog  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer userId;
	
	
    /**
     * 下注金额       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
	private java.lang.Double zuhePoint;
    
	private int pointNum;
	
	private java.lang.Double getPoint;
    
	private java.lang.Double addPoint;
	
	private String code;
    
	//columns END

	public TuiJianVoLog(){
	}

	 	
	
		
	 
	
		
	public void setPoint(java.lang.Double value) {
		this.point = value;
	}
	
	public java.lang.Double getPoint() {
		return this.point;
	}







	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}







	public java.lang.Integer getUserId() {
		return userId;
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







	public void setAddPoint(java.lang.Double addPoint) {
		this.addPoint = addPoint;
	}







	public java.lang.Double getAddPoint() {
		return addPoint;
	}







	public void setCode(String code) {
		this.code = code;
	}







	public String getCode() {
		return code;
	}





 	
	
	 
}

