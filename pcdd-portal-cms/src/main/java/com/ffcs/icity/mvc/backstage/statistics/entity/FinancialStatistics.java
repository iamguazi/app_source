package com.ffcs.icity.mvc.backstage.statistics.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class FinancialStatistics extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer allUser;  //用户总数
	private Integer dayActiveUser; //今日活跃会员
	private Integer dayUser; //今日新增会员
	private Integer weekUser; //周新增会员
	private Integer monthUser;  //月新增会员
	private Integer onlineUser; //在线人数
	private Double platformWinOrLoseFee;  //平台总输赢
	private Double dishAllFee;  //盘内总额
	private Double givingAllFee; //赠送总额
	private Double toDayFloatExhibit;//今日浮动盈亏
	private Double dayPayFee;  //今日充值
	private Double dayOnlinePayFee; //线上充值
	private Double dayOfflinePayFee; //线下转账
	private Double dayWithdrawalsFee; //今日提现
	private Double dayWinOrLoseFee; //今日总输赢
	private Double totalPoint; //会员总余额
	public Integer getAllUser() {
		return allUser;
	}
	public void setAllUser(Integer allUser) {
		this.allUser = allUser;
	}
	public Integer getDayActiveUser() {
		return dayActiveUser;
	}
	public void setDayActiveUser(Integer dayActiveUser) {
		this.dayActiveUser = dayActiveUser;
	}
	public Integer getDayUser() {
		return dayUser;
	}
	public void setDayUser(Integer dayUser) {
		this.dayUser = dayUser;
	}
	public Integer getWeekUser() {
		return weekUser;
	}
	public void setWeekUser(Integer weekUser) {
		this.weekUser = weekUser;
	}
	public Integer getMonthUser() {
		return monthUser;
	}
	public void setMonthUser(Integer monthUser) {
		this.monthUser = monthUser;
	}
	public Integer getOnlineUser() {
		return onlineUser;
	}
	public void setOnlineUser(Integer onlineUser) {
		this.onlineUser = onlineUser;
	}
	public Double getPlatformWinOrLoseFee() {
		return platformWinOrLoseFee;
	}
	public void setPlatformWinOrLoseFee(Double platformWinOrLoseFee) {
		this.platformWinOrLoseFee = platformWinOrLoseFee;
	}
	public Double getDishAllFee() {
		return dishAllFee;
	}
	public void setDishAllFee(Double dishAllFee) {
		this.dishAllFee = dishAllFee;
	}
	public Double getGivingAllFee() {
		return givingAllFee;
	}
	public void setGivingAllFee(Double givingAllFee) {
		this.givingAllFee = givingAllFee;
	}
	public Double getToDayFloatExhibit() {
		return toDayFloatExhibit;
	}
	public void setToDayFloatExhibit(Double toDayFloatExhibit) {
		this.toDayFloatExhibit = toDayFloatExhibit;
	}
	public Double getDayPayFee() {
		return dayPayFee;
	}
	public void setDayPayFee(Double dayPayFee) {
		this.dayPayFee = dayPayFee;
	}
	public Double getDayOnlinePayFee() {
		return dayOnlinePayFee;
	}
	public void setDayOnlinePayFee(Double dayOnlinePayFee) {
		this.dayOnlinePayFee = dayOnlinePayFee;
	}
	public Double getDayWithdrawalsFee() {
		return dayWithdrawalsFee;
	}
	public void setDayWithdrawalsFee(Double dayWithdrawalsFee) {
		this.dayWithdrawalsFee = dayWithdrawalsFee;
	}
	public Double getDayWinOrLoseFee() {
		return dayWinOrLoseFee;
	}
	public void setDayWinOrLoseFee(Double dayWinOrLoseFee) {
		this.dayWinOrLoseFee = dayWinOrLoseFee;
	}
	public Double getDayOfflinePayFee() {
		return dayOfflinePayFee;
	}
	public void setDayOfflinePayFee(Double dayOfflinePayFee) {
		this.dayOfflinePayFee = dayOfflinePayFee;
	}
	public Double getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Double totalPoint) {
		this.totalPoint = totalPoint;
	}
	
}
