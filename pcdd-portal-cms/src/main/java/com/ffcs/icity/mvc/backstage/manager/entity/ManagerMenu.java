package com.ffcs.icity.mvc.backstage.manager.entity;

import com.ffcs.icity.mvc.entity.IdEntity;

/**
 * 管理员栏目关系实体
 * @author Administrator
 *
 */
public class ManagerMenu extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer managerId;
    
    private Integer backstageMenuId;
    

    public Integer getManagerId() {
        return this.managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    public Integer getBackstageMenuId() {
        return this.backstageMenuId;
    }

    public void setBackstageMenuId(Integer backstageMenuId) {
        this.backstageMenuId = backstageMenuId;
    }

}