package com.ffcs.icity.mvc.backstage.account.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserAccountLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer accountId;
    
    private String account;
    
    private String accountInfo;
    
    private Integer accountType;
    
    private Integer collectionType;
    
    private String realName;
    
    private String bankName;
    
    private Double point;
    
    private Integer status;
    
    private Date createTime;
    
    private Integer userId;
    
    private String addType;
    

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public Double getPoint() {
        return this.point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAddType() {
        return this.addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public Integer getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}

}