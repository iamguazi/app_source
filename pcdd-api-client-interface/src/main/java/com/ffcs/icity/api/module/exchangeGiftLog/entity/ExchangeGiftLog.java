package com.ffcs.icity.api.module.exchangeGiftLog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ExchangeGiftLog  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * giftId       db_column: GIFT_ID 
     */ 	
	private java.lang.Integer giftId;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
    /**
     * 礼品内容       db_column: GIFT_NAME 
     */ 	
	private java.lang.String giftName;
	
    /**
     * 兑换数量       db_column: GIFT_COUNT 
     */ 	
	private java.lang.Integer giftCount;
	
    /**
     * 昵称       db_column: NICK_NAME 
     */ 	
	private java.lang.String nickName;
	
    /**
     * 0 未受理             1已受理       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 兑换时间       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * 联系人       db_column: REAL_NAME 
     */ 	
	private java.lang.String realName;
	
    /**
     * 联系电话       db_column: MOBILE 
     */ 	
	private java.lang.String mobile;
	
    /**
     * 联系地址       db_column: ADDRESS 
     */ 	
	private java.lang.String address;
	
    /**
     * 用户帐号       db_column: USER_ACCOUNT 
     */ 	
	private java.lang.String userAccount;
	
    /**
     * cityId       db_column: CITY_ID 
     */ 	
	private java.lang.Integer cityId;
	
    /**
     * 消耗积分       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * 处理时间       db_column: UPDATE_TIME 
     */ 	
	private java.util.Date updateTime;
	
    /**
     * 兑换单价       db_column: GIFT_POINT 
     */ 	
	private java.lang.Double giftPoint;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public ExchangeGiftLog(){
	}

	public ExchangeGiftLog(
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
	
		
	public void setGiftId(java.lang.Integer value) {
		this.giftId = value;
	}
	
	public java.lang.Integer getGiftId() {
		return this.giftId;
	}		
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}		
	
		
	public void setGiftName(java.lang.String value) {
		this.giftName = value;
	}
	
	public java.lang.String getGiftName() {
		return this.giftName;
	}		
	
		
	public void setGiftCount(java.lang.Integer value) {
		this.giftCount = value;
	}
	
	public java.lang.Integer getGiftCount() {
		return this.giftCount;
	}		
	
		
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
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
	
		
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}		
	
		
	public void setUserAccount(java.lang.String value) {
		this.userAccount = value;
	}
	
	public java.lang.String getUserAccount() {
		return this.userAccount;
	}		
	
		
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	public java.lang.Integer getCityId() {
		return this.cityId;
	}		
	
		
	 	
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
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

	public void setGiftPoint(java.lang.Double giftPoint) {
		this.giftPoint = giftPoint;
	}

	public java.lang.Double getGiftPoint() {
		return giftPoint;
	}

	public void setPoint(java.lang.Double point) {
		this.point = point;
	}

	public java.lang.Double getPoint() {
		return point;
	}
}

