package com.ffcs.icity.api.module.roomUserInfo.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobChatGroups;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.roomUserInfo.dao.IRoomUserInfoDao;
import com.ffcs.icity.api.module.roomUserInfo.entity.RoomUserInfo;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;


/**
 * @version v 1.0
 * @author: 黄嘉彬
 * @date: 2014-12-15
 */

public class AddRoomUserInfoController extends NoValidController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddRoomUserInfoController.class);
    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */

    @Autowired
    private IRoomUserInfoDao roomUserInfoDao;

    @Autowired
    private IRoomInfoDao roomInfoDao;

    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private MsgService msgService;

    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "room_id");
        assertNotEmpty(requestArgument, "user_id");
    }

    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
     */
    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        String roomId = requestArgument.get("room_id").toString();
        String userId = requestArgument.get("user_id").toString();
        Object password = requestArgument.get("password");
        LOGGER.info("huanxin userid {} into room {}", userId, roomId);
        RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
        if (!"-1".equals(roomInfo.getPassword().toString())) {
            if (password == null) {
                throw new ApiException("密码不能为空");
            }

            if (!password.toString().equals(roomInfo.getPassword().toString())) {
                throw new ApiException("密码不正确");
            }
        }
        UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
        LOGGER.info("huanxin user_id is {} userName is {}", userInfo.getId(), userInfo.getAccount());
        RoomUserInfo roomUserInfo = new RoomUserInfo();
        roomUserInfo.setAreaId(roomInfo.getAreaId());
        roomUserInfo.setRoomId(Integer.parseInt(roomId));
        roomUserInfo.setUserId(Integer.parseInt(userId));
        roomUserInfo.setUserPhoto(userInfo.getUserPhoto());
        roomUserInfo.setUserName(userInfo.getNickName());
        roomUserInfo.setMobile(userInfo.getMobile());
        long t1 = System.currentTimeMillis();
        /**
         * 在群组中添加一个人
         * curl示例
         * curl -X POST 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojianguo002' -H 'Authorization: Bearer {token}'
         */
        String addToChatgroupid = roomInfo.getImGourpId();
        String toAddUsername = userInfo.getImAccount();
        ObjectNode addUserToGroupNode = new EasemobChatGroups().addUserToGroup(addToChatgroupid, toAddUsername);
        LOGGER.info("huanxin add user to group use {} ms", (System.currentTimeMillis() - t1));
        RoomUserInfo roomUser = roomUserInfoDao.getRoomUserInfoByParams(roomUserInfo);
        if (roomUser == null) {
            roomUserInfoDao.insertRoomUserInfo(roomUserInfo);
        }

        return "ok";
    }
}

