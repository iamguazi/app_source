package com.ffcs.icity.mvc.backstage.userlevel.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserLevel extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String levelName;
    
    private Double rechargeFee;
    
    private String levelIcon;
    
    private Date createTime;
    

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public Double getRechargeFee() {
        return this.rechargeFee;
    }

    public void setRechargeFee(Double rechargeFee) {
        this.rechargeFee = rechargeFee;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getLevelIcon() {
		return levelIcon;
	}

	public void setLevelIcon(String levelIcon) {
		this.levelIcon = levelIcon;
	}

}