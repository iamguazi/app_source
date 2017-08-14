package com.ffcs.icity.api.module.roomInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class RoomInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * imGourpId       db_column: IM_GOURP_ID 
     */ 	
	private java.lang.String imGourpId;
	
    /**
     * 1北京28   2加拿大28       db_column: GAME_TYPE 
     */ 	
	private java.lang.Integer gameType;
	
    /**
     * 所属房间id       db_column: AREA_ID 
     */ 	
	private java.lang.Integer areaId;
	
    /**
     * 房间名称       db_column: ROOM_NAME 
     */ 	
	private java.lang.String roomName;
	
    /**
     * 房间图片       db_column: ROOM_PHOTO 
     */ 	
	private java.lang.String roomPhoto;
	
    /**
     * 机器人上限       db_column: REBOT_MAX_COUNT 
     */ 	
	private java.lang.Integer rebotMaxCount;
	
    /**
     * 机器人下限       db_column: REBOT_MIN_COUNT 
     */ 	
	private java.lang.Integer rebotMinCount;
	
    /**
     * 人数上限       db_column: PEOPLE_MAX_COUNT 
     */ 	
	private java.lang.Integer peopleMaxCount;
	
    /**
     * 单注最高金额       db_column: PER_MAX_POINT 
     */ 	
	private java.lang.Double perMaxPoint;
	
    /**
     * 单注最低金额       db_column: PER_MIN_POINT 
     */ 	
	private java.lang.Double perMinPoint;
	
    /**
     * 总投注上限       db_column: ALL_MAX_POINT 
     */ 	
	private java.lang.Double allMaxPoint;
	
    /**
     * 1正常 0禁止       db_column: STATUS 
     */ 	
	private java.lang.Integer status;
	
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	
	
	private int peopleCount;
	
	private String password;
	//columns END

	public RoomInfo(){
	}

	public RoomInfo(
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
	
		
	public void setImGourpId(java.lang.String value) {
		this.imGourpId = value;
	}
	
	public java.lang.String getImGourpId() {
		return this.imGourpId;
	}		
	
		
	public void setGameType(java.lang.Integer value) {
		this.gameType = value;
	}
	
	public java.lang.Integer getGameType() {
		return this.gameType;
	}		
	
		
	public void setAreaId(java.lang.Integer value) {
		this.areaId = value;
	}
	
	public java.lang.Integer getAreaId() {
		return this.areaId;
	}		
	
		
	public void setRoomName(java.lang.String value) {
		this.roomName = value;
	}
	
	public java.lang.String getRoomName() {
		return this.roomName;
	}		
	
		
	public void setRoomPhoto(java.lang.String value) {
		this.roomPhoto = value;
	}
	
	public java.lang.String getRoomPhoto() {
		return this.roomPhoto;
	}		
	
		
	public void setRebotMaxCount(java.lang.Integer value) {
		this.rebotMaxCount = value;
	}
	
	public java.lang.Integer getRebotMaxCount() {
		return this.rebotMaxCount;
	}		
	
		
	public void setRebotMinCount(java.lang.Integer value) {
		this.rebotMinCount = value;
	}
	
	public java.lang.Integer getRebotMinCount() {
		return this.rebotMinCount;
	}		
	
		
	public void setPeopleMaxCount(java.lang.Integer value) {
		this.peopleMaxCount = value;
	}
	
	public java.lang.Integer getPeopleMaxCount() {
		return this.peopleMaxCount;
	}		
	
		
	public void setPerMaxPoint(java.lang.Double value) {
		this.perMaxPoint = value;
	}
	
	public java.lang.Double getPerMaxPoint() {
		return this.perMaxPoint;
	}		
	
		
	public void setPerMinPoint(java.lang.Double value) {
		this.perMinPoint = value;
	}
	
	public java.lang.Double getPerMinPoint() {
		return this.perMinPoint;
	}		
	
		
	public void setAllMaxPoint(java.lang.Double value) {
		this.allMaxPoint = value;
	}
	
	public java.lang.Double getAllMaxPoint() {
		return this.allMaxPoint;
	}		
	
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
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

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}

