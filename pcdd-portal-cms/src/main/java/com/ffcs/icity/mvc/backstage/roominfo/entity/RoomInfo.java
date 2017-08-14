package com.ffcs.icity.mvc.backstage.roominfo.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class RoomInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imGourpId;
    
    private Integer gameType;
    
    private Integer areaId;
    
    private String roomName;
    
    private String roomPhoto;
    
    private Integer rebotMaxCount;
    
    private Integer rebotMinCount;
    
    private Integer peopleMaxCount;
    
    private Double perMaxPoint;
    
    private Double perMinPoint;
    
    private Double allMaxPoint;
    
    private Integer status;
    
    private Date createTime;
    
    private String areaTypeName;
    
    private Integer realUser;
    
    private Integer robot;
    
    private Integer minPeopleCount;
    
    private String password;
    
    public String getImGourpId() {
        return this.imGourpId;
    }

    public void setImGourpId(String imGourpId) {
        this.imGourpId = imGourpId;
    }
    public Integer getGameType() {
        return this.gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getRoomPhoto() {
        return this.roomPhoto;
    }

    public void setRoomPhoto(String roomPhoto) {
        this.roomPhoto = roomPhoto;
    }
    public Integer getRebotMaxCount() {
        return this.rebotMaxCount;
    }

    public void setRebotMaxCount(Integer rebotMaxCount) {
        this.rebotMaxCount = rebotMaxCount;
    }
    public Integer getRebotMinCount() {
        return this.rebotMinCount;
    }

    public void setRebotMinCount(Integer rebotMinCount) {
        this.rebotMinCount = rebotMinCount;
    }
    public Integer getPeopleMaxCount() {
        return this.peopleMaxCount;
    }

    public void setPeopleMaxCount(Integer peopleMaxCount) {
        this.peopleMaxCount = peopleMaxCount;
    }
    public Double getPerMaxPoint() {
        return this.perMaxPoint;
    }

    public void setPerMaxPoint(Double perMaxPoint) {
        this.perMaxPoint = perMaxPoint;
    }
    public Double getPerMinPoint() {
        return this.perMinPoint;
    }

    public void setPerMinPoint(Double perMinPoint) {
        this.perMinPoint = perMinPoint;
    }
    public Double getAllMaxPoint() {
        return this.allMaxPoint;
    }

    public void setAllMaxPoint(Double allMaxPoint) {
        this.allMaxPoint = allMaxPoint;
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

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public Integer getRealUser() {
		return realUser;
	}

	public void setRealUser(Integer realUser) {
		this.realUser = realUser;
	}

	public Integer getRobot() {
		return robot;
	}

	public void setRobot(Integer robot) {
		this.robot = robot;
	}

	public Integer getMinPeopleCount() {
		return minPeopleCount;
	}

	public void setMinPeopleCount(Integer minPeopleCount) {
		this.minPeopleCount = minPeopleCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}