package com.ffcs.icity.api.module.util;

public class PayParam {
	private String timestamp;
	
	private String nonceStr;
	
	private String packageStr;
	
	private String signType;
	
	private String paySign;
	
	private String appid;

	private String partnerid;
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppid() {
		return appid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPartnerid() {
		return partnerid;
	}
	
	
	
}
