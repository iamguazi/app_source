package com.ffcs.icity.api.module.withdrawalsLogs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class WithdrawalsLogs  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
  
	@JsonIgnore
	private java.lang.Integer userType;
	
    /**
     * 操作类型  1提现       db_column: TYPE 
     */ 
	@JsonIgnore
	private java.lang.Integer type;
	
    /**
     * 操作金额       db_column: FEE 
     */ 	
	private java.lang.Double fee;
	
    /**
     * 提现比例       db_column: BILI 
     */ 	
	private java.lang.Double bili;
	
    /**
     * 实到金额       db_column: REAL_FEE 
     */ 	
	private java.lang.Double realFee;
	
    /**
     * 银行名 （渠道为3有效）       db_column: BANK_NAME 
     */ 
	@JsonIgnore
	private java.lang.String bankName;
	
    /**
     * 提现帐号       db_column: ACCOUNT 
     */ 
	
	private java.lang.String account;
	
    /**
     * 状态 0未处理 1已处理 2处理失败       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 渠道1支付宝 2微信 3银行卡       db_column: SOURCE 
     */ 	
	private java.lang.String source;
	
    /**
     * 创建时间       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * updateTime       db_column: UPDATE_TIME 
     */ 	
	private java.util.Date updateTime;
	
    /**
     * 用户名       db_column: REAL_NAME 
     */ 	
	private java.lang.String realName;
	
    /**
     * mobile       db_column: MOBILE 
     */ 	
	private java.lang.String mobile;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	@JsonIgnore
	private String createBankName;
	//columns END

	public WithdrawalsLogs(){
	}

	public WithdrawalsLogs(
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
	
		
	public void setUserType(java.lang.Integer value) {
		this.userType = value;
	}
	
	public java.lang.Integer getUserType() {
		return this.userType;
	}		
	
		
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}		
	
		
	public void setFee(java.lang.Double value) {
		this.fee = value;
	}
	
	public java.lang.Double getFee() {
		return this.fee;
	}		
	
		
	public void setBili(java.lang.Double value) {
		this.bili = value;
	}
	
	public java.lang.Double getBili() {
		return this.bili;
	}		
	
		
	public void setRealFee(java.lang.Double value) {
		this.realFee = value;
	}
	
	public java.lang.Double getRealFee() {
		return this.realFee;
	}		
	
		
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}		
	
		
	public void setAccount(java.lang.String value) {
		this.account = value;
	}
	
	public java.lang.String getAccount() {
		return this.account;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setSource(java.lang.String value) {
		this.source = value;
	}
	
	public java.lang.String getSource() {
		return this.source;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	
		
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}		
	
		
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
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

	public void setCreateBankName(String createBankName) {
		this.createBankName = createBankName;
	}

	public String getCreateBankName() {
		return createBankName;
	}		
	

	
}

