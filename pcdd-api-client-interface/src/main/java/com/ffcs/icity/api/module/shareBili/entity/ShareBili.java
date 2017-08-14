package com.ffcs.icity.api.module.shareBili.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ShareBili  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 等级       db_column: LEVEL 
     */ 	
	private java.lang.String level;
	
    /**
     * 累计起始分数       db_column: START_POINT 
     */ 	
	private java.lang.Double startPoint;
	
    /**
     * 累计截止分数       db_column: END_POINT 
     */ 	
	private java.lang.Double endPoint;
	
    /**
     * 佣金       db_column: GET_POINT 
     */ 	
	private java.lang.Double getPoint;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public ShareBili(){
	}

	public ShareBili(
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
	
		
	public void setLevel(java.lang.String value) {
		this.level = value;
	}
	
	public java.lang.String getLevel() {
		return this.level;
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

