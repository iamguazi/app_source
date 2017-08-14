package com.ffcs.icity.mvc.backstage.other.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

public class OtherParams extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramsKey;
    
    private String paramsValue;
    
    private String paramsGroup;
    
    private String paramsRemarks;
    

    public String getParamsKey() {
        return this.paramsKey;
    }

    public void setParamsKey(String paramsKey) {
        this.paramsKey = paramsKey;
    }
    public String getParamsValue() {
        return this.paramsValue;
    }

    public void setParamsValue(String paramsValue) {
        this.paramsValue = paramsValue;
    }
    public String getParamsGroup() {
        return this.paramsGroup;
    }

    public void setParamsGroup(String paramsGroup) {
        this.paramsGroup = paramsGroup;
    }
    public String getParamsRemarks() {
        return this.paramsRemarks;
    }

    public void setParamsRemarks(String paramsRemarks) {
        this.paramsRemarks = paramsRemarks;
    }

}