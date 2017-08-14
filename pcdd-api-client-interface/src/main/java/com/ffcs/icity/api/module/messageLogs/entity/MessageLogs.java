package com.ffcs.icity.api.module.messageLogs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class MessageLogs  {
	
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
	
	 
	private String phone;
	 
    /**
     * code       db_column: CODE 
     */ 	
	private java.lang.String code;
	
	 
    /**
     * createTime       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	@JsonIgnore
	private String createTimeBegin;
	@JsonIgnore
	private String createTimeEnd;
	 
    /**
     * status       db_column: STATUS 
     */ 	
	private java.lang.String status;
	
	private String smsMessageSid;
	
	private String message;
	
	 
	//columns END

	public MessageLogs(){
	}

	public MessageLogs(
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
	
		
	
		
	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}		
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	
	public String getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}
		

    public java.lang.String getStatus() {
        return status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public String getSmsMessageSid() {
        return smsMessageSid;
    }

    public void setSmsMessageSid(String smsMessageSid) {
        this.smsMessageSid = smsMessageSid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }		
	

	
}

