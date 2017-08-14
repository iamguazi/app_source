package com.ffcs.icity.mvc.backstage.openrule.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class OpenRule extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer gameType;
    
    private Integer areaId;
    
    private String typeName;
    
    private Double start1;
    
    private Double end1;
    
    private Double bili1;
    
    private Double start2;
    
    private Double end2;
    
    private Double bili2;
    
    private Double start3;
    
    private Double end3;
    
    private Double bili3;

    private Double start4;
    
    private Double end4;
    
    private Double bili4;
    
    private Date createTime;

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Double getStart1() {
		return start1;
	}

	public void setStart1(Double start1) {
		this.start1 = start1;
	}

	public Double getEnd1() {
		return end1;
	}

	public void setEnd1(Double end1) {
		this.end1 = end1;
	}

	public Double getBili1() {
		return bili1;
	}

	public void setBili1(Double bili1) {
		this.bili1 = bili1;
	}

	public Double getStart2() {
		return start2;
	}

	public void setStart2(Double start2) {
		this.start2 = start2;
	}

	public Double getEnd2() {
		return end2;
	}

	public void setEnd2(Double end2) {
		this.end2 = end2;
	}

	public Double getBili2() {
		return bili2;
	}

	public void setBili2(Double bili2) {
		this.bili2 = bili2;
	}

	public Double getStart3() {
		return start3;
	}

	public void setStart3(Double start3) {
		this.start3 = start3;
	}

	public Double getEnd3() {
		return end3;
	}

	public void setEnd3(Double end3) {
		this.end3 = end3;
	}

	public Double getBili3() {
		return bili3;
	}

	public void setBili3(Double bili3) {
		this.bili3 = bili3;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getStart4() {
		return start4;
	}

	public void setStart4(Double start4) {
		this.start4 = start4;
	}

	public Double getEnd4() {
		return end4;
	}

	public void setEnd4(Double end4) {
		this.end4 = end4;
	}

	public Double getBili4() {
		return bili4;
	}

	public void setBili4(Double bili4) {
		this.bili4 = bili4;
	}
	
	
}