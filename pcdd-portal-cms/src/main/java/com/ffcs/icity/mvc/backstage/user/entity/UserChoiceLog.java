package com.ffcs.icity.mvc.backstage.user.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class UserChoiceLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
    
    private Integer roomId;
    
    private Integer roomLevel;
    
    private String choiceNo;
    
    private String choiceResult;
    
    private String choiceName;
    
    private Double bili;
    
    private Double point;
    
    private Double accountPoint;
    
    private String realResult;
    
    private String resultType;
    
    private Double getPoint;
    
    private Date createTime;
    
    private Integer gameType;
    
    private Double xhibitPoint;
    
    private String account;
    
    private String nickName;
    
    private String userPhoto;
    
    private String areaName;
    
    private String roomName;

    private Integer status;
    
    private Integer isZhong;
    
    private Integer biliId;
    
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    public Integer getRoomLevel() {
        return this.roomLevel;
    }

    public void setRoomLevel(Integer roomLevel) {
        this.roomLevel = roomLevel;
    }
    public String getChoiceNo() {
        return this.choiceNo;
    }

    public void setChoiceNo(String choiceNo) {
        this.choiceNo = choiceNo;
    }
    public String getChoiceResult() {
        return this.choiceResult;
    }

    public void setChoiceResult(String choiceResult) {
        this.choiceResult = choiceResult;
    }
    public Double getBili() {
        return this.bili;
    }

    public void setBili(Double bili) {
        this.bili = bili;
    }
    public Double getPoint() {
        return this.point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
    public String getRealResult() {
        return this.realResult;
    }

    public void setRealResult(String realResult) {
        this.realResult = realResult;
    }
    public String getResultType() {
        return this.resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
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
    public Integer getGameType() {
        return this.gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
    public Double getXhibitPoint() {
        return this.xhibitPoint;
    }

    public void setXhibitPoint(Double xhibitPoint) {
        this.xhibitPoint = xhibitPoint;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsZhong() {
		return isZhong;
	}

	public void setIsZhong(Integer isZhong) {
		this.isZhong = isZhong;
	}

	public Integer getBiliId() {
		return biliId;
	}

	public void setBiliId(Integer biliId) {
		this.biliId = biliId;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public Double getAccountPoint() {
		return accountPoint;
	}

	public void setAccountPoint(Double accountPoint) {
		this.accountPoint = accountPoint;
	}

}