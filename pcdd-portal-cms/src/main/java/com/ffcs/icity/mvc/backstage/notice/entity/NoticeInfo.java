package com.ffcs.icity.mvc.backstage.notice.entity;

import com.ffcs.icity.mvc.entity.IdEntity;
import java.util.Date;

public class NoticeInfo extends IdEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
    
    private String content;
    
    private Date createTime;
    
    private Integer noticeType;
    
    private Integer userId;
    

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
    public Integer getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}