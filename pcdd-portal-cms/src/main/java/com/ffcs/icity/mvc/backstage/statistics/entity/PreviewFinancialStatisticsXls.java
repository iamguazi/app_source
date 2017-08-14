package com.ffcs.icity.mvc.backstage.statistics.entity;

import java.math.BigDecimal;

import com.ffcs.icity.mvc.entity.IdEntity;

public class PreviewFinancialStatisticsXls extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;
	private Integer newUser;
	private BigDecimal gameType1;
	private BigDecimal gameType2;
	private BigDecimal backWater;
	private BigDecimal withdrawalFee;
	private BigDecimal rechargeFee;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}
	public BigDecimal getGameType1() {
		return gameType1;
	}
	public void setGameType1(BigDecimal gameType1) {
		this.gameType1 = gameType1;
	}
	public BigDecimal getGameType2() {
		return gameType2;
	}
	public void setGameType2(BigDecimal gameType2) {
		this.gameType2 = gameType2;
	}
	public BigDecimal getBackWater() {
		return backWater;
	}
	public void setBackWater(BigDecimal backWater) {
		this.backWater = backWater;
	}
	public BigDecimal getWithdrawalFee() {
		return withdrawalFee;
	}
	public void setWithdrawalFee(BigDecimal withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}
	public BigDecimal getRechargeFee() {
		return rechargeFee;
	}
	public void setRechargeFee(BigDecimal rechargeFee) {
		this.rechargeFee = rechargeFee;
	}
	
}
