package com.ffcs.icity.api.module.versionInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class VersionInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 版本号       db_column: VERSION_NO 
     */ 	
	private java.lang.String versionNo;
	
	@JsonIgnore
	private Integer versionCode;
	
    /**
     * 要替换的版本号       db_column: UPDATE_VERSION_NO 
     */ 	
	@JsonIgnore
	private java.lang.String updateVersionNo;
	
    /**
     * 更新内容       db_column: UPDATE_CONTENT 
     */ 	
	private java.lang.String updateContent;
	
    /**
     * 下载地址       db_column: VERSION_URL 
     */ 	
	private java.lang.String versionUrl;
	
    /**
     * 1强制更新 0推荐更新       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	@JsonIgnore
	private java.util.Date createTime;
	
	@JsonIgnore
	private java.lang.Integer client;
	
	//columns END

	public VersionInfo(){
	}

	public VersionInfo(
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
	
		
	public void setVersionNo(java.lang.String value) {
		this.versionNo = value;
	}
	
	public java.lang.String getVersionNo() {
		return this.versionNo;
	}		
	
		
	public void setUpdateVersionNo(java.lang.String value) {
		this.updateVersionNo = value;
	}
	
	public java.lang.String getUpdateVersionNo() {
		return this.updateVersionNo;
	}		
	
		
	public void setUpdateContent(java.lang.String value) {
		this.updateContent = value;
	}
	
	public java.lang.String getUpdateContent() {
		return this.updateContent;
	}		
	
		
	public void setVersionUrl(java.lang.String value) {
		this.versionUrl = value;
	}
	
	public java.lang.String getVersionUrl() {
		return this.versionUrl;
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

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setClient(java.lang.Integer client) {
		this.client = client;
	}

	public java.lang.Integer getClient() {
		return client;
	}
	
	

	
}

