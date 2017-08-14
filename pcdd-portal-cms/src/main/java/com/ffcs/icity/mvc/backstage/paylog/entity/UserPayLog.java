package com.ffcs.icity.mvc.backstage.paylog.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserPayLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String outTradeNo;
    
    private String orderNo;
    
    private Integer userId;
    
    private String resultCode;
    
    private Double totalFee;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer payType;
    
    private Integer status;
    
    private Double freeFee;
    
    private Integer orderType;
    
    private String prePayId;
    
    private Double resultFee;
    
    private String payDesc;
    
    private String nickName;

    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public Double getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getPayType() {
        return this.payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Double getFreeFee() {
        return this.freeFee;
    }

    public void setFreeFee(Double freeFee) {
        this.freeFee = freeFee;
    }
    public Integer getOrderType() {
        return this.orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public String getPrePayId() {
        return this.prePayId;
    }

    public void setPrePayId(String prePayId) {
        this.prePayId = prePayId;
    }
    public Double getResultFee() {
        return this.resultFee;
    }

    public void setResultFee(Double resultFee) {
        this.resultFee = resultFee;
    }
    public String getPayDesc() {
        return this.payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}