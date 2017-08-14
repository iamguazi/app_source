package com.ffcs.icity.api.module.banner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Banner  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * bannerName       db_column: BANNER_NAME 
     */ 	
	private java.lang.String bannerName;
	
    /**
     * bannerImgurl       db_column: BANNER_IMGURL 
     */ 	
	private java.lang.String bannerImgurl;
	
    /**
     * content       db_column: CONTENT 
     */ 	
	private java.lang.String content;
	
    /**
     * bannerOrder       db_column: BANNER_ORDER 
     */ 	
	private java.lang.Integer bannerOrder;
	
    /**
     * 是否跳转       db_column: IS_GO 
     */ 	
	private java.lang.Integer isGo;
	
    /**
     * 0创建 1正常（申请通过）2申请中 3申请失败       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * 1app 2网站首页       db_column: BANNER_PLACE 
     */ 	
	private java.lang.Integer bannerPlace;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * url       db_column: URL 
     */ 	
	private java.lang.String url;
	
	//columns END

	public Banner(){
	}

	public Banner(
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
	
		
	public void setBannerName(java.lang.String value) {
		this.bannerName = value;
	}
	
	public java.lang.String getBannerName() {
		return this.bannerName;
	}		
	
		
	public void setBannerImgurl(java.lang.String value) {
		this.bannerImgurl = value;
	}
	
	public java.lang.String getBannerImgurl() {
		return this.bannerImgurl;
	}		
	
		
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}		
	
		
	public void setBannerOrder(java.lang.Integer value) {
		this.bannerOrder = value;
	}
	
	public java.lang.Integer getBannerOrder() {
		return this.bannerOrder;
	}		
	
		
	public void setIsGo(java.lang.Integer value) {
		this.isGo = value;
	}
	
	public java.lang.Integer getIsGo() {
		return this.isGo;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setBannerPlace(java.lang.Integer value) {
		this.bannerPlace = value;
	}
	
	public java.lang.Integer getBannerPlace() {
		return this.bannerPlace;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}		
	

	
}

