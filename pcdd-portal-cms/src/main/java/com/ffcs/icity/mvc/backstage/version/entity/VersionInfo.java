package com.ffcs.icity.mvc.backstage.version.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class VersionInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer versionCode;
    
    private String versionNo;
    
    private String updateVersionNo;
    
    private String updateContent;
    
    private String versionUrl;
    
    private Integer status;
    
    private Date createTime;
    
    private Integer client;
    

    public Integer getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
    public String getVersionNo() {
        return this.versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
    public String getUpdateVersionNo() {
        return this.updateVersionNo;
    }

    public void setUpdateVersionNo(String updateVersionNo) {
        this.updateVersionNo = updateVersionNo;
    }
    public String getUpdateContent() {
        return this.updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
    public String getVersionUrl() {
        return this.versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
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
    public Integer getClient() {
        return this.client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

}