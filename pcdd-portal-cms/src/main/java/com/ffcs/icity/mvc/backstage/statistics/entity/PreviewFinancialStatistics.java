package com.ffcs.icity.mvc.backstage.statistics.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class PreviewFinancialStatistics extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;
	private Integer newUser;
	private Double beijing28Exhibit;
	private Double canada28Exhibit;
	private Double backWater;
	private Double withdrawalFee;
	private Double rechargeFee;
	private String account;
	private String nickName;
	private Double xhibitPoint;
	
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}
	
	public Double getBeijing28Exhibit() {
		return beijing28Exhibit;
	}
	public void setBeijing28Exhibit(Double beijing28Exhibit) {
		this.beijing28Exhibit = beijing28Exhibit;
	}
	public Double getWithdrawalFee() {
		return withdrawalFee;
	}
	public void setWithdrawalFee(Double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}
	public Double getRechargeFee() {
		return rechargeFee;
	}
	public void setRechargeFee(Double rechargeFee) {
		this.rechargeFee = rechargeFee;
	}
	public Double getCanada28Exhibit() {
		return canada28Exhibit;
	}
	public void setCanada28Exhibit(Double canada28Exhibit) {
		this.canada28Exhibit = canada28Exhibit;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getBackWater() {
		return backWater;
	}
	public void setBackWater(Double backWater) {
		this.backWater = backWater;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Double getXhibitPoint() {
		return xhibitPoint;
	}
	public void setXhibitPoint(Double xhibitPoint) {
		this.xhibitPoint = xhibitPoint;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
