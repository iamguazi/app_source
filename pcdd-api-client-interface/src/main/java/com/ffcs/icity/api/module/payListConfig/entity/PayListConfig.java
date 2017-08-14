package com.ffcs.icity.api.module.payListConfig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class PayListConfig  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 支付名称       db_column: NAME 
     */ 	
	private java.lang.String name;
	
    /**
     * 备注       db_column: REMARKS 
     */ 	
	@JsonIgnore
	private java.lang.String remarks;
	
    /**
     * 类型 1线上支付 2线下支付       db_column: TYPE 
     */ 	
	@JsonIgnore
	private java.lang.Integer type;
	
    /**
     * 1开启 0禁用       db_column: STATUS 
     */ 	
	
	@JsonIgnore
	private java.lang.Integer status;
	
    /**
     * 排序  数字越大越靠前       db_column: PAY_ORDER 
     */ 	
	@JsonIgnore
	private java.lang.Integer payOrder;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	
	@JsonIgnore
	private java.util.Date createTime;
	
    /**
     * 对应的支付       db_column: TYPE_KEY 
     */ 	
	private java.lang.String typeKey;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public PayListConfig(){
	}

	public PayListConfig(
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
	
		
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}		
	
		
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}		
	
		
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setPayOrder(java.lang.Integer value) {
		this.payOrder = value;
	}
	
	public java.lang.Integer getPayOrder() {
		return this.payOrder;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setTypeKey(java.lang.String value) {
		this.typeKey = value;
	}
	
	public java.lang.String getTypeKey() {
		return this.typeKey;
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

