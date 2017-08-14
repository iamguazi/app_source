package com.ffcs.icity.mvc.backstage.login.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class LoginLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
    
    private String userName;
    
    private String remarks;
    
    private String ip;
    
    private Date createTime;
    
    private Integer userType;
    

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}