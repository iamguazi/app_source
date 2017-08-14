package com.ffcs.icity.api.module.userChoiceLog.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameBili.dao.IGameBiliDao;
import com.ffcs.icity.api.module.gameBili.entity.GameBili;
import com.ffcs.icity.api.module.gameOpenLog.dao.IGameOpenLogDao;
import com.ffcs.icity.api.module.gameOpenLog.entity.GameOpenLog;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.pointChangeLog.entity.PointChangeLog;
import com.ffcs.icity.api.module.roomInfo.dao.IRoomInfoDao;
import com.ffcs.icity.api.module.roomInfo.entity.RoomInfo;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.userChoiceLog.dao.IUserChoiceLogDao;
import com.ffcs.icity.api.module.userChoiceLog.entity.UserChoiceLog;
import com.ffcs.icity.api.module.userChoiceLog.util.OpenTimeUtil;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;


/**
 * @version v 1.0
 * @author: 黄嘉彬
 * @date: 2014-12-15
 */

public class AddUserChoiceLogController extends NoValidController {

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */

    @Autowired
    private IUserChoiceLogDao userChoiceLogDao;

    @Autowired
    private IGameBiliDao gameBiliDao;

    @Autowired
    private MsgService msgService;

    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private IPointChangeLogDao pointChangeLogDao;

    @Autowired
    private IRoomInfoDao roomInfoDao;

    @Autowired
    private IGameOpenLogDao gameOpenLogDao;

    @Autowired
    private OpenTimeUtil openTimeUtil;


    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "user_id");
        assertNotEmpty(requestArgument, "area_id");
        assertNotEmpty(requestArgument, "room_id");
        assertNotEmpty(requestArgument, "choice_no");
        assertNotEmpty(requestArgument, "point");
        assertNotEmpty(requestArgument, "bili_id");
    }

    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
     */
    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        String userId = requestArgument.get("user_id").toString();
        String roomId = requestArgument.get("room_id").toString();
        String areaId = requestArgument.get("area_id").toString();
        String choiceNo = requestArgument.get("choice_no").toString();
        String point = requestArgument.get("point").toString();
        String biliId = requestArgument.get("bili_id").toString();


        RoomInfo room = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));
        if (room != null && room.getStatus() == 0) {
            throw new ApiException("该房间停用，请到其他房间");
        }

        //查看开奖状态
        GameOpenLog gameOpenLog = new GameOpenLog();
        if ("1".equals(areaId) || "2".equals(areaId) || "3".equals(areaId)) {
            gameOpenLog.setGameType(1);
        } else {
            gameOpenLog.setGameType(2);
        }

        gameOpenLog.setGameNum(choiceNo);
        GameOpenLog openLog = gameOpenLogDao.getGameOpenLogByNum(gameOpenLog);

        if (openLog != null) {
            int isAdd = openTimeUtil.curGameStatus(openLog);
            if (isAdd == 2) {
                throw new ApiException("该房间已经封盘，请选择其他房间");
            }
            if (isAdd == 3) {
                throw new ApiException("该房间已经停售，请选择其他房间");
            }
        }

        GameBili gameBili = gameBiliDao.getGameBiliById(Integer.parseInt(biliId));
        UserChoiceLog userChoiceLog = new UserChoiceLog();
        userChoiceLog.setUserId(Integer.parseInt(userId));
        userChoiceLog.setRoomId(Integer.parseInt(roomId));
        userChoiceLog.setAreaId(Integer.parseInt(areaId));
        userChoiceLog.setChoiceNo(choiceNo);
        userChoiceLog.setChoiceResult(gameBili.getResult());
        userChoiceLog.setBili(gameBili.getBili());
        userChoiceLog.setPoint(Double.parseDouble(point));
        userChoiceLog.setGameType(gameBili.getGameType());
        userChoiceLog.setBiliId(Integer.parseInt(biliId));
        userChoiceLog.setChoiceName(gameBili.getBiliName());

        double allfee = userChoiceLogDao.getUserChoiceAllFee(userChoiceLog);
        RoomInfo roomInfo = roomInfoDao.getRoomInfoById(Integer.parseInt(roomId));

        if (roomInfo.getPerMinPoint() > Double.parseDouble(point)) {
            throw new ApiException("小于单注最小限额");
        }
        if (roomInfo.getPerMaxPoint() < allfee + Double.parseDouble(point)) {
            throw new ApiException("超过单注最大限额");
        }
        UserInfo userInfo = userInfoDao.getUserInfoById(Integer.parseInt(userId));
        if (userInfo != null) {
            if (userInfo.getPoint() < Double.parseDouble(point)) {
                throw new ApiException("您的余额不足，请先充值");
            } else {
                UserInfo userInfoModel = new UserInfo();
                userInfoModel.setId(userInfo.getId());
                userInfo.setPoint(-Double.parseDouble(point));
                userInfoDao.updateUserInfoByPoint(userInfo);

            }
        }
        userChoiceLogDao.insertUserChoiceLog(userChoiceLog);

        PointChangeLog pointChangeLog = new PointChangeLog();
        pointChangeLog.setUserId(Integer.parseInt(userId));
        pointChangeLog.setPoint(-Double.parseDouble(point));
        pointChangeLog.setPointDesc("投注");

        pointChangeLogDao.insertPointChangeLog(pointChangeLog);
        try {
            msgService.sendAddPointMsg(roomId, Integer.parseInt(userId), userChoiceLog);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "ok";
    }

    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
        // TODO Auto-generated method stub

        return new String[]{requestArgument.get("choice_no").toString(), requestArgument.get("point").toString()};
    }
}

