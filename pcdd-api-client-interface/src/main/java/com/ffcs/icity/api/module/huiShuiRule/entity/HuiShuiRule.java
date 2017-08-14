package com.ffcs.icity.api.module.huiShuiRule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class HuiShuiRule  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 1初级 2中级 3高级       db_column: AREA_TYPE 
     */ 	
	private java.lang.Integer areaType;
	
    /**
     * bili       db_column: BILI 
     */ 	
	private java.lang.Double bili;
	
    /**
     * startPoint       db_column: START_POINT 
     */ 	
	private java.lang.Double startPoint;
	
    /**
     * endPoint       db_column: END_POINT 
     */ 	
	private java.lang.Double endPoint;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public HuiShuiRule(){
	}

	public HuiShuiRule(
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
	
		
	public void setAreaType(java.lang.Integer value) {
		this.areaType = value;
	}
	
	public java.lang.Integer getAreaType() {
		return this.areaType;
	}		
	
		
	public void setBili(java.lang.Double value) {
		this.bili = value;
	}
	
	public java.lang.Double getBili() {
		return this.bili;
	}		
	
		
	public void setStartPoint(java.lang.Double value) {
		this.startPoint = value;
	}
	
	public java.lang.Double getStartPoint() {
		return this.startPoint;
	}		
	
		
	public void setEndPoint(java.lang.Double value) {
		this.endPoint = value;
	}
	
	public java.lang.Double getEndPoint() {
		return this.endPoint;
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
}

