package com.ffcs.icity.mvc.backstage.user.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;
    
    private String password;
    
    private String userPhoto;
    
    private String nickName;
    
    private Integer sex;
    
    private String mobile;
    
    private Double point;
    
    private Date createTime;
    
    private String bandId;
    
    private Integer bandType;
    
    private String registrationId;
    
    private String code;
    
    private Integer status;
    
    private String imAccount;
    
    private Integer level;
    
    private Integer userType;
    
    private Date loginTime;
    
    private Double xhibitPoint;
    
    private String levelName;

    private String realName;
    
    private String bankName;
    
    private String bankNo;
    
    private String openCardAddress;
    
    private String ipAddr;
    
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserPhoto() {
        return this.userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Double getPoint() {
        return this.point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getBandId() {
        return this.bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }
    public Integer getBandType() {
        return this.bandType;
    }

    public void setBandType(Integer bandType) {
        this.bandType = bandType;
    }
    public String getRegistrationId() {
        return this.registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getImAccount() {
        return this.imAccount;
    }

    public void setImAccount(String imAccount) {
        this.imAccount = imAccount;
    }
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public Double getXhibitPoint() {
        return this.xhibitPoint;
    }

    public void setXhibitPoint(Double xhibitPoint) {
        this.xhibitPoint = xhibitPoint;
    }

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getOpenCardAddress() {
		return openCardAddress;
	}

	public void setOpenCardAddress(String openCardAddress) {
		this.openCardAddress = openCardAddress;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


}