package com.ffcs.icity.mvc.backstage.sharebill.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class ShareBili extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String level;
    
    private Double startPoint;
    
    private Double endPoint;
    
    private Double getPoint;
    
    private Date createTime;
    

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public Double getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(Double startPoint) {
        this.startPoint = startPoint;
    }
    public Double getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(Double endPoint) {
        this.endPoint = endPoint;
    }
    public Double getGetPoint() {
        return this.getPoint;
    }

    public void setGetPoint(Double getPoint) {
        this.getPoint = getPoint;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}