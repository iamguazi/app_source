package com.ffcs.icity.mvc.backstage.gamebili.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class GameBili extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer gameType;
    
    private Integer biliType;
    
    private String biliName;
    
    private Double bili;
    
    private String result;
    
    private Integer areaId;
    
    private String areaTypeName;

    private Double minPoint;
    
    private Double maxPoint;
    
    public Integer getGameType() {
        return this.gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
    public Integer getBiliType() {
        return this.biliType;
    }

    public void setBiliType(Integer biliType) {
        this.biliType = biliType;
    }
    public String getBiliName() {
        return this.biliName;
    }

    public void setBiliName(String biliName) {
        this.biliName = biliName;
    }
    public Double getBili() {
        return this.bili;
    }

    public void setBili(Double bili) {
        this.bili = bili;
    }
    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public Double getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(Double minPoint) {
		this.minPoint = minPoint;
	}

	public Double getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Double maxPoint) {
		this.maxPoint = maxPoint;
	}

}