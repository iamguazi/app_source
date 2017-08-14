package com.ffcs.icity.mvc.backstage.account.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class AccountInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;
    
    private Integer accountType;
    
    private String payName;
    
    private String realName;
    
    private String bankName;
    
    private String openCardAddress;
    
    private String photo;
    
    private Date createTime;
    

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
    public String getOpenCardAddress() {
        return this.openCardAddress;
    }

    public void setOpenCardAddress(String openCardAddress) {
        this.openCardAddress = openCardAddress;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}