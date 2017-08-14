package com.ffcs.icity.mvc.backstage.givelog.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserGiveLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
    
    private Integer giveUserId;
    
    private Integer giftId;
    
    private String giftName;
    
    private Integer count;
    
    private Double allFee;
    
    private Date createTime;
    
    private Integer roomId;
    
    private Double perFee;
    
    private String userNickName;
    
    private String hostNickName;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGiveUserId() {
        return this.giveUserId;
    }

    public void setGiveUserId(Integer giveUserId) {
        this.giveUserId = giveUserId;
    }
    public Integer getGiftId() {
        return this.giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }
    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    public Double getAllFee() {
        return this.allFee;
    }

    public void setAllFee(Double allFee) {
        this.allFee = allFee;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    public Double getPerFee() {
        return this.perFee;
    }

    public void setPerFee(Double perFee) {
        this.perFee = perFee;
    }

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getHostNickName() {
		return hostNickName;
	}

	public void setHostNickName(String hostNickName) {
		this.hostNickName = hostNickName;
	}

}