package com.ffcs.icity.api.module.userAccountLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ffcs.icity.api.module.accountInfo.entity.AccountInfo;


public class UserAccountLog  {
	
	
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
     * 金额       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 0待确认 1确认收到 2没收到       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	private String addType;
	
	private Integer accountId;
	
	private AccountInfo accountInfo;
	//columns END

	public UserAccountLog(){
	}

	public UserAccountLog(
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
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
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

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getAddType() {
		return addType;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
}

