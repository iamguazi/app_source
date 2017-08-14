package com.ffcs.icity.api.module.roomUserInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class RoomUserInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 游戏区域id       db_column: AREA_ID 
     */ 	
	private java.lang.Integer areaId;
	
    /**
     * 房间id       db_column: ROOM_ID 
     */ 	
	private java.lang.Integer roomId;
	
    /**
     * userPhoto       db_column: USER_PHOTO 
     */ 	
	private java.lang.String userPhoto;
	
    /**
     * userId       db_column: USER_ID 
     */ 	
	private java.lang.Integer userId;
	
    /**
     * userName       db_column: USER_NAME 
     */ 	
	private java.lang.String userName;
	
    /**
     * mobile       db_column: MOBILE 
     */ 	
	private java.lang.String mobile;
	
	
	@JsonIgnore
	private Integer start;
	
	@JsonIgnore
	private Integer pageSize;
	//columns END

	public RoomUserInfo(){
	}

	public RoomUserInfo(
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
	
		
	public void setAreaId(java.lang.Integer value) {
		this.areaId = value;
	}
	
	public java.lang.Integer getAreaId() {
		return this.areaId;
	}		
	
		
	public void setRoomId(java.lang.Integer value) {
		this.roomId = value;
	}
	
	public java.lang.Integer getRoomId() {
		return this.roomId;
	}		
	
		
	public void setUserPhoto(java.lang.String value) {
		this.userPhoto = value;
	}
	
	public java.lang.String getUserPhoto() {
		return this.userPhoto;
	}		
	
		
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}		
	
		
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}		
	
		
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
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
}

