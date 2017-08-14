package com.ffcs.icity.mvc.backstage.gift.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class ExchangeGiftLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer giftId;
    
    private Integer userId;
    
    private String giftName;
    
    private Integer giftCount;
    
    private String nickName;
    
    private Integer status;
    
    private Date createTime;
    
    private String realName;
    
    private String mobile;
    
    private String address;
    
    private String userAccount;
    
    private Integer cityId;
    
    private Integer point;
    
    private Integer giftPoint;
    
    private Date updateTime;
    
    public Integer getGiftId() {
        return this.giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    public Integer getGiftCount() {
        return this.giftCount;
    }

    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }
    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getPoint() {
        return this.point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

	public Integer getGiftPoint() {
		return giftPoint;
	}

	public void setGiftPoint(Integer giftPoint) {
		this.giftPoint = giftPoint;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}