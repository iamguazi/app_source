package com.ffcs.icity.mvc.backstage.gameopen.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class GameOpenLog extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
    
    private Integer gameType;
    
    private Date openTime;
    
    private String gameNum;
    
    private Integer gameResult;
    
    private String gameResultDesc;
    
    private String resultType;
    
    private Double xhibitPoint;
    
    private Double totalPoint;
    
    private Integer isBaozi;
    
    private Integer isGive;
    
    private Double givePoint;

    private Integer color;
    
    private Integer source;
    
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGameType() {
        return this.gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
    public Date getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
    public String getGameNum() {
        return this.gameNum;
    }

    public void setGameNum(String gameNum) {
        this.gameNum = gameNum;
    }
    public Integer getGameResult() {
        return this.gameResult;
    }

    public void setGameResult(Integer gameResult) {
        this.gameResult = gameResult;
    }
    public String getGameResultDesc() {
        return this.gameResultDesc;
    }

    public void setGameResultDesc(String gameResultDesc) {
        this.gameResultDesc = gameResultDesc;
    }
    public String getResultType() {
        return this.resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    public Double getXhibitPoint() {
        return this.xhibitPoint;
    }

    public void setXhibitPoint(Double xhibitPoint) {
        this.xhibitPoint = xhibitPoint;
    }
    public Double getTotalPoint() {
        return this.totalPoint;
    }

    public void setTotalPoint(Double totalPoint) {
        this.totalPoint = totalPoint;
    }

	public Integer getIsBaozi() {
		return isBaozi;
	}

	public void setIsBaozi(Integer isBaozi) {
		this.isBaozi = isBaozi;
	}

	public Integer getIsGive() {
		return isGive;
	}

	public void setIsGive(Integer isGive) {
		this.isGive = isGive;
	}

	public Double getGivePoint() {
		return givePoint;
	}

	public void setGivePoint(Double givePoint) {
		this.givePoint = givePoint;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

}