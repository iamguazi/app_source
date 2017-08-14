package com.ffcs.icity.mvc.backstage.wapinfo.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class WapInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String wapTitle;
    
    private String wapContent;
    
    private String wapKey;
    

    public String getWapTitle() {
        return this.wapTitle;
    }

    public void setWapTitle(String wapTitle) {
        this.wapTitle = wapTitle;
    }
    public String getWapContent() {
        return this.wapContent;
    }

    public void setWapContent(String wapContent) {
        this.wapContent = wapContent;
    }
    public String getWapKey() {
        return this.wapKey;
    }

    public void setWapKey(String wapKey) {
        this.wapKey = wapKey;
    }

}