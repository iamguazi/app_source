package com.ffcs.icity.mvc.backstage.banner.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class Banner extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bannerName;
    
    private String bannerImgurl;
    
    private String content;
    
    private Integer bannerOrder;
    
    private Integer isGo;
    
    private Integer status;
    
    private Integer bannerPlace;
    
    private Date createTime;
    
    private String url;
    

    public String getBannerName() {
        return this.bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }
    public String getBannerImgurl() {
        return this.bannerImgurl;
    }

    public void setBannerImgurl(String bannerImgurl) {
        this.bannerImgurl = bannerImgurl;
    }
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getBannerOrder() {
        return this.bannerOrder;
    }

    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }
    public Integer getIsGo() {
        return this.isGo;
    }

    public void setIsGo(Integer isGo) {
        this.isGo = isGo;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getBannerPlace() {
        return this.bannerPlace;
    }

    public void setBannerPlace(Integer bannerPlace) {
        this.bannerPlace = bannerPlace;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}