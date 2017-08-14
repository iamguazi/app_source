package com.ffcs.icity.mvc.backstage.other.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class OtherInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String otherKey;
    
    private String otherValue;
    
    private String beizhu;
    

    public String getOtherKey() {
        return this.otherKey;
    }

    public void setOtherKey(String otherKey) {
        this.otherKey = otherKey;
    }
    public String getOtherValue() {
        return this.otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
    public String getBeizhu() {
        return this.beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

}