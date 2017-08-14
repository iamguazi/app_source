package com.ffcs.icity.mvc.backstage.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.notice.dao.NoticeInfoDao;
import com.ffcs.icity.mvc.backstage.notice.entity.NoticeInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/noticeInfo")
@Controller
public class NoticeInfoController extends AsynController<NoticeInfo>{

	private NoticeInfoDao noticeInfoDao;

	@Override
	protected CRUDDao<NoticeInfo> getDao() {
		return this.noticeInfoDao;
	}

	@Autowired
	public void setNoticeInfoDao(NoticeInfoDao noticeInfoDao) {
		this.noticeInfoDao = noticeInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/notice";
	}
}
