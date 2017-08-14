package com.ffcs.icity.api.module.payParams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class PayParams  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 商户id       db_column: MCH_ID 
     */ 	
	private java.lang.String mchId;
	
    /**
     * 商户key       db_column: MCH_KEY 
     */ 	
	private java.lang.String mchKey;
	
    /**
     * noticeUrl       db_column: NOTICE_URL 
     */ 	
	private java.lang.String noticeUrl;
	
    /**
     * callbackUrl       db_column: CALLBACK_URL 
     */ 	
	private java.lang.String callbackUrl;
	
    /**
     * 商户名       db_column: MCH_TYPE 
     */ 	
	private java.lang.String mchType;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public PayParams(){
	}

	public PayParams(
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
	
		
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}		
	
		
	public void setMchKey(java.lang.String value) {
		this.mchKey = value;
	}
	
	public java.lang.String getMchKey() {
		return this.mchKey;
	}		
	
		
	public void setNoticeUrl(java.lang.String value) {
		this.noticeUrl = value;
	}
	
	public java.lang.String getNoticeUrl() {
		return this.noticeUrl;
	}		
	
		
	public void setCallbackUrl(java.lang.String value) {
		this.callbackUrl = value;
	}
	
	public java.lang.String getCallbackUrl() {
		return this.callbackUrl;
	}		
	
		
	public void setMchType(java.lang.String value) {
		this.mchType = value;
	}
	
	public java.lang.String getMchType() {
		return this.mchType;
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

