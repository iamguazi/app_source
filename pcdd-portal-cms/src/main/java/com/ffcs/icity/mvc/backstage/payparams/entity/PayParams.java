package com.ffcs.icity.mvc.backstage.payparams.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class PayParams extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mchId;
    
    private String mchKey;
    
    private String noticeUrl;
    
    private String callbackUrl;
    
    private String mchType;
    

    public String getMchId() {
        return this.mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    public String getMchKey() {
        return this.mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }
    public String getNoticeUrl() {
        return this.noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
    public String getMchType() {
        return this.mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

}