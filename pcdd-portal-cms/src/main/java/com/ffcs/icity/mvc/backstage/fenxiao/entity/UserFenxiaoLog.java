package com.ffcs.icity.mvc.backstage.fenxiao.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserFenxiaoLog extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
    
    private Double point;
    
    private Double fenxiaoPoint;
    
    private Integer status;
    
    private Double zuhePoint;
    
    private Integer pointNum;
    
    private Double getPoint;
    
    private Double xhibitPoint;
    
    private Date createTime;
    
    private Integer fenxiaoUserId;
    
    private String nickName;
    
    private String friendName;
    
    private String account;
    
    private String mobile;
    

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Double getPoint() {
        return this.point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
    public Double getFenxiaoPoint() {
        return this.fenxiaoPoint;
    }

    public void setFenxiaoPoint(Double fenxiaoPoint) {
        this.fenxiaoPoint = fenxiaoPoint;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Double getZuhePoint() {
        return this.zuhePoint;
    }

    public void setZuhePoint(Double zuhePoint) {
        this.zuhePoint = zuhePoint;
    }
    public Integer getPointNum() {
        return this.pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }
    public Double getGetPoint() {
        return this.getPoint;
    }

    public void setGetPoint(Double getPoint) {
        this.getPoint = getPoint;
    }
    public Double getXhibitPoint() {
        return this.xhibitPoint;
    }

    public void setXhibitPoint(Double xhibitPoint) {
        this.xhibitPoint = xhibitPoint;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getFenxiaoUserId() {
        return this.fenxiaoUserId;
    }

    public void setFenxiaoUserId(Integer fenxiaoUserId) {
        this.fenxiaoUserId = fenxiaoUserId;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}