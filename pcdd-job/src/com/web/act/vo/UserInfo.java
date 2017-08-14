package com.web.act.vo;

public class UserInfo {
	private Integer id;
	
	private Integer areaId;
	
	private Integer roomId;
	
	private Integer point;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+this.id+"areaId:"+areaId+"roomId:"+roomId;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPoint() {
		return point;
	}

	 
}
