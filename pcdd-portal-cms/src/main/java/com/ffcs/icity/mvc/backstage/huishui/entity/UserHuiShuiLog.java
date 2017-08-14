package com.ffcs.icity.mvc.backstage.huishui.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserHuiShuiLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer type;
    
    private Integer userId;
    
    private Double point;
    
    private Double bili;
    
    private Double huiShuiPoint;
    
    private Integer status;
    
    private Date createTime;
    
    private String nickName;
    
    private String account;
    
    private String mobile;
    
    private Double zuhePoint;
    
    private Integer pointNum;
    
    private Double xhibitPoint;
    
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
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
    public Double getBili() {
        return this.bili;
    }

    public void setBili(Double bili) {
        this.bili = bili;
    }
    public Double getHuiShuiPoint() {
        return this.huiShuiPoint;
    }

    public void setHuiShuiPoint(Double huiShuiPoint) {
        this.huiShuiPoint = huiShuiPoint;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public Double getZuhePoint() {
		return zuhePoint;
	}

	public void setZuhePoint(Double zuhePoint) {
		this.zuhePoint = zuhePoint;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public Double getXhibitPoint() {
		return xhibitPoint;
	}

	public void setXhibitPoint(Double xhibitPoint) {
		this.xhibitPoint = xhibitPoint;
	}

}