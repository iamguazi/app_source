package com.ffcs.icity.api.module.gameBili.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GameBili  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * gameType       db_column: GAME_TYPE 
     */ 	
	private java.lang.Integer gameType;
	
    /**
     * 比例类型 1大小单双 2猜数字 3特殊玩法       db_column: BILI_TYPE 
     */ 	
	private java.lang.Integer biliType;
	
    /**
     * 比例名称       db_column: BILI_NAME 
     */ 	
	private java.lang.String biliName;
	
    /**
     * 比例       db_column: BILI 
     */ 	
	private java.lang.Double bili;
	
    /**
     * 中奖和值       db_column: RESULT 
     */ 	
	private java.lang.String result;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	private Integer areaId;
	
	private Double minPoint;
	
	private Double maxPoint;
	//columns END

	public GameBili(){
	}

	public GameBili(
		java.lang.Integer id
	){
		this.id = id;
	}

		
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}		
	
		
	public void setGameType(java.lang.Integer value) {
		this.gameType = value;
	}
	
	public java.lang.Integer getGameType() {
		return this.gameType;
	}		
	
		
	public void setBiliType(java.lang.Integer value) {
		this.biliType = value;
	}
	
	public java.lang.Integer getBiliType() {
		return this.biliType;
	}		
	
		
	public void setBiliName(java.lang.String value) {
		this.biliName = value;
	}
	
	public java.lang.String getBiliName() {
		return this.biliName;
	}		
	
		
	public void setBili(java.lang.Double value) {
		this.bili = value;
	}
	
	public java.lang.Double getBili() {
		return this.bili;
	}		
	
		
	public void setResult(java.lang.String value) {
		this.result = value;
	}
	
	public java.lang.String getResult() {
		return this.result;
	}		
	

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setMinPoint(Double minPoint) {
		this.minPoint = minPoint;
	}

	public Double getMinPoint() {
		return minPoint;
	}

	public void setMaxPoint(Double maxPoint) {
		this.maxPoint = maxPoint;
	}

	public Double getMaxPoint() {
		return maxPoint;
	}
}

