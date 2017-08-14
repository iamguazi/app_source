package com.ffcs.icity.api.module.otherInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class OtherInfo  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
    /**
     * otherKey       db_column: OTHER_KEY 
     */ 	
	private java.lang.String otherKey;
	
    /**
     * otherValue       db_column: OTHER_VALUE 
     */ 	
	private java.lang.String otherValue;
	
    /**
     * beizhu       db_column: BEIZHU 
     */ 	
	private java.lang.String beizhu;
	
	//columns END

	public OtherInfo(){
	}

	public OtherInfo(
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
	
		
	public void setOtherKey(java.lang.String value) {
		this.otherKey = value;
	}
	
	public java.lang.String getOtherKey() {
		return this.otherKey;
	}		
	
		
	public void setOtherValue(java.lang.String value) {
		this.otherValue = value;
	}
	
	public java.lang.String getOtherValue() {
		return this.otherValue;
	}		
	
		
	public void setBeizhu(java.lang.String value) {
		this.beizhu = value;
	}
	
	public java.lang.String getBeizhu() {
		return this.beizhu;
	}		
	

	
}

