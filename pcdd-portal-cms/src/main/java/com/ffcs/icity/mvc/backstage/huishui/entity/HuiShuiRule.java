package com.ffcs.icity.mvc.backstage.huishui.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class HuiShuiRule extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer areaType;
    
    private Double bili;
    
    private Double startPoint;
    
    private Double endPoint;
    
    private Date createTime;
    

    public Integer getAreaType() {
        return this.areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }
    public Double getBili() {
        return this.bili;
    }

    public void setBili(Double bili) {
        this.bili = bili;
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
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}