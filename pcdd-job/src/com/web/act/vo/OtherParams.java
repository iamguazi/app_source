package com.web.act.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class OtherParams  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * 键       db_column: PARAMS_KEY 
     */ 	
	private java.lang.String paramsKey;
	
    /**
     * 值       db_column: PARAMS_VALUE 
     */ 	
	private java.lang.String paramsValue;
	
    /**
     * 分组       db_column: PARAMS_GROUP 
     */ 	
	private java.lang.String paramsGroup;
	
    /**
     * 备注       db_column: PARAMS_REMARKS 
     */ 	
	private java.lang.String paramsRemarks;
	
	//columns END

	public OtherParams(){
	}

	public OtherParams(
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
	
		
	public void setParamsKey(java.lang.String value) {
		this.paramsKey = value;
	}
	
	public java.lang.String getParamsKey() {
		return this.paramsKey;
	}		
	
		
	public void setParamsValue(java.lang.String value) {
		this.paramsValue = value;
	}
	
	public java.lang.String getParamsValue() {
		return this.paramsValue;
	}		
	
		
	public void setParamsGroup(java.lang.String value) {
		this.paramsGroup = value;
	}
	
	public java.lang.String getParamsGroup() {
		return this.paramsGroup;
	}		
	
		
	public void setParamsRemarks(java.lang.String value) {
		this.paramsRemarks = value;
	}
	
	public java.lang.String getParamsRemarks() {
		return this.paramsRemarks;
	}		
	

	
}

