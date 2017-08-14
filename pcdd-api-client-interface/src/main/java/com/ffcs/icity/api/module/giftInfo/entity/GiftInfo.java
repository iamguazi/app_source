package com.ffcs.icity.api.module.giftInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GiftInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 礼物       db_column: GIFT_NAME 
     */ 	
	private java.lang.String giftName;
	
    /**
     * 所需金额       db_column: GIFT_POINT 
     */ 	
	private java.lang.Double giftPoint;
	
    /**
     * 图片       db_column: GIFT_PHOTO 
     */ 	
	private java.lang.String giftPhoto;
	
    /**
     * 1正常 0隐藏       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 排序  越大越考前       db_column: GIFT_ORDER 
     */ 	
	private java.lang.Integer giftOrder;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public GiftInfo(){
	}

	public GiftInfo(
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
	
		
	public void setGiftName(java.lang.String value) {
		this.giftName = value;
	}
	
	public java.lang.String getGiftName() {
		return this.giftName;
	}		
	
		
	public void setGiftPoint(java.lang.Double value) {
		this.giftPoint = value;
	}
	
	public java.lang.Double getGiftPoint() {
		return this.giftPoint;
	}		
	
		
	public void setGiftPhoto(java.lang.String value) {
		this.giftPhoto = value;
	}
	
	public java.lang.String getGiftPhoto() {
		return this.giftPhoto;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setGiftOrder(java.lang.Integer value) {
		this.giftOrder = value;
	}
	
	public java.lang.Integer getGiftOrder() {
		return this.giftOrder;
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

