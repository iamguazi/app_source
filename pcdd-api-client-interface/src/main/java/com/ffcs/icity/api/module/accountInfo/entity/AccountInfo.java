package com.ffcs.icity.api.module.accountInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class AccountInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 帐号       db_column: ACCOUNT 
     */ 	
	private java.lang.String account;
	
    /**
     * 1银行卡 2支付宝       db_column: ACCOUNT_TYPE 
     */ 	
	private java.lang.Integer accountType;
	
    /**
     * 开户名       db_column: REAL_NAME 
     */ 	
	private java.lang.String realName;
	
    /**
     * 银行名称       db_column: BANK_NAME 
     */ 	
	private java.lang.String bankName;
	
    /**
     * 开户行       db_column: OPEN_CARD_ADDRESS 
     */ 	
	private java.lang.String openCardAddress;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	private String photo;
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public AccountInfo(){
	}

	public AccountInfo(
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
	
		
	public void setAccount(java.lang.String value) {
		this.account = value;
	}
	
	public java.lang.String getAccount() {
		return this.account;
	}		
	
		
	public void setAccountType(java.lang.Integer value) {
		this.accountType = value;
	}
	
	public java.lang.Integer getAccountType() {
		return this.accountType;
	}		
	
		
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}		
	
		
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}		
	
		
	public void setOpenCardAddress(java.lang.String value) {
		this.openCardAddress = value;
	}
	
	public java.lang.String getOpenCardAddress() {
		return this.openCardAddress;
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

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}
}

