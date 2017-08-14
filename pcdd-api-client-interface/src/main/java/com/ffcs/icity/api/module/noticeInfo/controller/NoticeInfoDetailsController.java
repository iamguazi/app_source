package com.ffcs.icity.api.module.noticeInfo.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.noticeInfo.dao.INoticeInfoDao;
import com.ffcs.icity.api.module.noticeInfo.entity.NoticeInfo;
import com.ffcs.icity.api.module.userAndNotice.dao.IUserAndNoticeDao;
import com.ffcs.icity.api.module.userAndNotice.entity.UserAndNotice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;


/**
 * @version v 1.0
 * @author: 黄嘉彬
 * @date: 2014-12-15
 */

public class NoticeInfoDetailsController extends NoValidController {

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */

    @Autowired
    private INoticeInfoDao noticeInfoDao;

    @Autowired
    private IUserAndNoticeDao userAndNoticeDao;


    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "notice_id");
    }

    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
     */
    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        String noticeId = requestArgument.get("notice_id").toString();
        String userId = requestArgument.get("user_id").toString();


        UserAndNotice userAndNotice = new UserAndNotice();
        userAndNotice.setUserId(Integer.parseInt(userId));
        userAndNotice.setNoticeId(Integer.parseInt(noticeId));
        userAndNotice.setStatus(1);
        if (userAndNoticeDao.getUserAndNoticeByParams(userAndNotice) == null) {
            userAndNoticeDao.insertUserAndNotice(userAndNotice);
        }
        NoticeInfo noticeInfoById = noticeInfoDao.getNoticeInfoById(Integer.parseInt(noticeId));
        return noticeInfoById;
    }

    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
        // TODO Auto-generated method stub
        return new String[]{"no_valid"};
    }
}

