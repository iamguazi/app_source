package com.ffcs.icity.api.module.userInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserInfo  {
	
	
	//columns START
    /**
     * id=100不能删除       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 帐号       db_column: ACCOUNT 
     */ 	
	private java.lang.String account;
	
    /**
     * 密码       db_column: PASSWORD 
     */ 	
	private java.lang.String password;
	
    /**
     * 头像       db_column: USER_PHOTO 
     */ 	
	private java.lang.String userPhoto;
	
    /**
     * 昵称       db_column: NICK_NAME 
     */ 	
	private java.lang.String nickName;
	
    /**
     * 性别 1男 2女       db_column: SEX 
     */ 	
	private java.lang.Integer sex;
	
    /**
     * 电话       db_column: MOBILE 
     */ 	
	private java.lang.String mobile;
	
    /**
     * 印票       db_column: POINT 
     */ 	
	private java.lang.Double point;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
    /**
     * 绑定识别id （openid）       db_column: BAND_ID 
     */ 	
	private java.lang.String bandId;
	
    /**
     * 绑定类型 0 无绑定 2微信  3qq       db_column: BAND_TYPE 
     */ 	
	private java.lang.Integer bandType;
	
    /**
     * 绑定手机  (弃用)     db_column: BAND_MOBILE 
     */ 	
	@JsonIgnore
	private java.lang.String bandMobile;
	
    /**
     * 极光设备id（待定）       db_column: REGISTRATION_ID 
     */ 	
	private java.lang.String registrationId;
	
    /**
     * 邀请码（待定）       db_column: CODE 
     */ 	
	private java.lang.String code;
	
    /**
     * 1正常 0拉黑       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * IM帐号       db_column: IM_ACCOUNT 
     */ 	
	private java.lang.String imAccount;
	
    /**
     * 等级       db_column: LEVEL 
     */ 	
	private java.lang.Integer level;
	
    /**
     * 1普通用户 2机器人       db_column: USER_TYPE 
     */ 	
	private java.lang.Integer userType;
	
    /**
     * 最后登录时间       db_column: LOGIN_TIME 
     */ 	
	private java.util.Date loginTime;
	
	@JsonIgnore
	private Integer start;

	@JsonIgnore
	private Integer pageSize;
	
	private String personalSign;
	
	 /**
     * 提现密码       db_column: WITHDRAWALS_PASSWORD 
     */ 	
	private java.lang.String withdrawalsPassword;
	
    /**
     * 开户姓名       db_column: REAL_NAME 
     */ 	
	private java.lang.String realName;
	
    /**
     * 银行名称       db_column: BANK_NAME 
     */ 	
	private java.lang.String bankName;
	
    /**
     * 银行卡号       db_column: BANK_NO 
     */ 	
	private java.lang.String bankNo;
	
    /**
     * 开户地址       db_column: OPEN_CARD_ADDRESS 
     */ 	
	private java.lang.String openCardAddress;
	
	private Double allPoint;

	private String ipAddr;
	//columns END

	public UserInfo(){
	}

	public UserInfo(
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
	
		
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}		
	
		
	public void setUserPhoto(java.lang.String value) {
		this.userPhoto = value;
	}
	
	public java.lang.String getUserPhoto() {
		return this.userPhoto;
	}		
	
		
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}		
	
		
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	public java.lang.Integer getSex() {
		return this.sex;
	}		
	
		
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}		
	
		
	public void setPoint(java.lang.Double value) {
		this.point = value;
	}
	
	public java.lang.Double getPoint() {
		return this.point;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
		
	public void setBandId(java.lang.String value) {
		this.bandId = value;
	}
	
	public java.lang.String getBandId() {
		return this.bandId;
	}		
	
		
	public void setBandType(java.lang.Integer value) {
		this.bandType = value;
	}
	
	public java.lang.Integer getBandType() {
		return this.bandType;
	}		
	
		
	public void setBandMobile(java.lang.String value) {
		this.bandMobile = value;
	}
	
	public java.lang.String getBandMobile() {
		return this.bandMobile;
	}		
	
		
	public void setRegistrationId(java.lang.String value) {
		this.registrationId = value;
	}
	
	public java.lang.String getRegistrationId() {
		return this.registrationId;
	}		
	
		
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
		
	public void setImAccount(java.lang.String value) {
		this.imAccount = value;
	}
	
	public java.lang.String getImAccount() {
		return this.imAccount;
	}		
	
		
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
	public java.lang.Integer getLevel() {
		return this.level;
	}		
	
		
	public void setUserType(java.lang.Integer value) {
		this.userType = value;
	}
	
	public java.lang.Integer getUserType() {
		return this.userType;
	}		
	
	public void setLoginTime(java.util.Date value) {
		this.loginTime = value;
	}
	
	public java.util.Date getLoginTime() {
		return this.loginTime;
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

	public void setPersonalSign(String personalSign) {
		this.personalSign = personalSign;
	}

	public String getPersonalSign() {
		return personalSign;
	}

	public java.lang.String getWithdrawalsPassword() {
		return withdrawalsPassword;
	}

	public void setWithdrawalsPassword(java.lang.String withdrawalsPassword) {
		this.withdrawalsPassword = withdrawalsPassword;
	}

	public java.lang.String getRealName() {
		return realName;
	}

	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	public java.lang.String getBankName() {
		return bankName;
	}

	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}

	public java.lang.String getBankNo() {
		return bankNo;
	}

	public void setBankNo(java.lang.String bankNo) {
		this.bankNo = bankNo;
	}

	public java.lang.String getOpenCardAddress() {
		return openCardAddress;
	}

	public void setOpenCardAddress(java.lang.String openCardAddress) {
		this.openCardAddress = openCardAddress;
	}

	public void setAllPoint(Double allPoint) {
		this.allPoint = allPoint;
	}

	public Double getAllPoint() {
		return allPoint;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getIpAddr() {
		return ipAddr;
	}


}
	
	


