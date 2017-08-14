package com.ffcs.icity.mvc.backstage.gamearea.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class GameArea extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer gameType;
    
    private Integer areaType;
    
    private String areaName;
    
    private String areaPhoto;
    
    private String feedbackDesc;
    
    private Double minPoint;
    
    private Integer status;
    
    private Date createTime;
    

    public Integer getGameType() {
        return this.gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
    public Integer getAreaType() {
        return this.areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }
    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaPhoto() {
        return this.areaPhoto;
    }

    public void setAreaPhoto(String areaPhoto) {
        this.areaPhoto = areaPhoto;
    }
    public String getFeedbackDesc() {
        return this.feedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }
    public Double getMinPoint() {
        return this.minPoint;
    }

    public void setMinPoint(Double minPoint) {
        this.minPoint = minPoint;
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

}