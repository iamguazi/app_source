package com.ffcs.icity.mvc.backstage.gift.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class GiftInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String giftName;
    
    private String giftPhoto;
    
    private Double giftPoint;
    
    private Date createTime;
    
    private Integer status;
    
    private Integer giftOrder;
    
    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    public String getGiftPhoto() {
        return this.giftPhoto;
    }

    public void setGiftPhoto(String giftPhoto) {
        this.giftPhoto = giftPhoto;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Double getGiftPoint() {
		return giftPoint;
	}

	public void setGiftPoint(Double giftPoint) {
		this.giftPoint = giftPoint;
	}

	public Integer getGiftOrder() {
		return giftOrder;
	}

	public void setGiftOrder(Integer giftOrder) {
		this.giftOrder = giftOrder;
	}

}