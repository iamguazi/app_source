package com.ffcs.icity.api.module.userPayLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserPayLog  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 支付宝或者微信的订单号       db_column: OUT_TRADE_NO 
     */ 	
	private java.lang.String outTradeNo;
	
    /**
     * 本地订单号       db_column: ORDER_NO 
     */ 	
	private java.lang.String orderNo;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
    /**
     * 支付结果       db_column: RESULT_CODE 
     */ 	
	private java.lang.String resultCode;
	
    /**
     * totalFee       db_column: TOTAL_FEE 
     */ 	
	private java.lang.Double totalFee;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * updateTime       db_column: UPDATE_TIME 
     */ 	
	private java.util.Date updateTime;
	
    /**
     * 支付方式 1 余额 2微信 3 支付宝       db_column: PAY_TYPE 
     */ 	
	private java.lang.Integer payType;
	
    /**
     * 0 创建 1 成功付款       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 充值赠送金额       db_column: FREE_FEE 
     */ 	
	private java.lang.Double freeFee;
	
    /**
     * 1充值 2付款       db_column: ORDER_TYPE 
     */ 	
	private java.lang.Integer orderType;
	
    /**
     * 预支付id       db_column: PRE_PAY_ID 
     */ 	
	private java.lang.String prePayId;
	
    /**
     * resultFee       db_column: RESULT_FEE 
     */ 	
	private java.lang.Double resultFee;
	
	private String payDesc;
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	//columns END

	public UserPayLog(){
	}

	public UserPayLog(
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
	
		
	public void setOutTradeNo(java.lang.String value) {
		this.outTradeNo = value;
	}
	
	public java.lang.String getOutTradeNo() {
		return this.outTradeNo;
	}		
	
		
	public void setOrderNo(java.lang.String value) {
		this.orderNo = value;
	}
	
	public java.lang.String getOrderNo() {
		return this.orderNo;
	}		
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}		
	
		
	public void setResultCode(java.lang.String value) {
		this.resultCode = value;
	}
	
	public java.lang.String getResultCode() {
		return this.resultCode;
	}		
	
		
	public void setTotalFee(java.lang.Double value) {
		this.totalFee = value;
	}
	
	public java.lang.Double getTotalFee() {
		return this.totalFee;
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
	
	
		
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
	public java.lang.Integer getPayType() {
		return this.payType;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setFreeFee(java.lang.Double value) {
		this.freeFee = value;
	}
	
	public java.lang.Double getFreeFee() {
		return this.freeFee;
	}		
	
		
	public void setOrderType(java.lang.Integer value) {
		this.orderType = value;
	}
	
	public java.lang.Integer getOrderType() {
		return this.orderType;
	}		
	
		
	public void setPrePayId(java.lang.String value) {
		this.prePayId = value;
	}
	
	public java.lang.String getPrePayId() {
		return this.prePayId;
	}		
	
		
	public void setResultFee(java.lang.Double value) {
		this.resultFee = value;
	}
	
	public java.lang.Double getResultFee() {
		return this.resultFee;
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

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	public String getPayDesc() {
		return payDesc;
	}		
	

	
}

