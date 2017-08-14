package com.ffcs.icity.mvc.backstage.payconfig.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class PayListConfig extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
    
    private String remarks;
    
    private Integer type;
    
    private Integer status;
    
    private Integer payOrder;
    
    private Date createTime;
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getPayOrder() {
        return this.payOrder;
    }

    public void setPayOrder(Integer payOrder) {
        this.payOrder = payOrder;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}