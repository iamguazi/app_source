package com.ffcs.icity.api.module.wapInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class WapInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * wapTitle       db_column: WAP_TITLE 
     */ 	
	private java.lang.String wapTitle;
	
    /**
     * wapContent       db_column: WAP_CONTENT 
     */ 	
	private java.lang.String wapContent;
	
    /**
     * wapKey       db_column: WAP_KEY 
     */ 	
	private java.lang.String wapKey;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public WapInfo(){
	}

	public WapInfo(
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
	
		
	public void setWapTitle(java.lang.String value) {
		this.wapTitle = value;
	}
	
	public java.lang.String getWapTitle() {
		return this.wapTitle;
	}		
	
		
	public void setWapContent(java.lang.String value) {
		this.wapContent = value;
	}
	
	public java.lang.String getWapContent() {
		return this.wapContent;
	}		
	
		
	public void setWapKey(java.lang.String value) {
		this.wapKey = value;
	}
	
	public java.lang.String getWapKey() {
		return this.wapKey;
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

