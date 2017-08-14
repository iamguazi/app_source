package com.ffcs.icity.mvc.backstage.quest.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class QuestInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
    
    private String content;
    
    private Date createTime;
    

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}