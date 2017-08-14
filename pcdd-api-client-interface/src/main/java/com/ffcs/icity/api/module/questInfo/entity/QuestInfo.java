package com.ffcs.icity.api.module.questInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class QuestInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * title       db_column: TITLE 
     */ 	
	private java.lang.String title;
	
    /**
     * content       db_column: CONTENT 
     */ 	
	private java.lang.String content;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public QuestInfo(){
	}

	public QuestInfo(
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
	
		
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}		
	
		
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
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

