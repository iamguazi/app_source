package com.ffcs.icity.api.module.userInfo.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.comm.Constants;
import com.ffcs.icity.api.module.IM.service.CreateNewIMUserService;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.userLevel.dao.IUserLevelDao;
import com.ffcs.icity.api.module.userLevel.entity.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;


/**
 * @version v 1.0
 * @author: 黄嘉彬
 * @date: 2014-12-15
 */

public class UserInfo3rdLoginController extends NoValidController {

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */

    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private CreateNewIMUserService createNewIMUserService;

    @Autowired
    private IUserLevelDao userLevelDao;


    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "band_id");
        assertNotEmpty(requestArgument, "band_type");
    }

    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
     */
    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        String bandId = requestArgument.get("band_id").toString();
        Object registrationId = requestArgument.get("registration_id");

        String bandType = requestArgument.get("band_type").toString();
        Object nickName = requestArgument.get("nick_name");
        Object userPhoto = requestArgument.get("user_photo");
        Object ipAddr = requestArgument.get("ip");


        UserInfo userInfo = new UserInfo();
        userInfo.setBandId(bandId);
        userInfo.setBandType(Integer.parseInt(bandType));
        UserInfo user = userInfoDao.getUserInfoByBand(userInfo);
        if (user != null) {
            if (user.getStatus() == 0) {
                throw new ApiException("用户被禁用");
            } else {
                user.setLoginTime(new Date());
                if (user.getAccount() == null) {
                    user.setAccount("u_" + user.getId());
                }
                if (user.getAccount() == null) {
                    user.setAccount("u_" + user.getId());
                }
                //判断等级
                UserLevel level = userLevelDao.getUserLevelByPoint(user.getAllPoint());
                if (level != null && level.getId() > user.getLevel()) {
                    user.setLevel(level.getId());
                }
                userInfoDao.updateUserInfo(user);
                return user;
            }

        } else {
            UserInfo userInfoModel = new UserInfo();
            if (nickName != null)
                userInfoModel.setNickName(nickName.toString());

            if (userPhoto != null) {
                try {
                    userInfoModel.setUserPhoto(URLDecoder.decode(userPhoto.toString()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            userInfoModel.setBandId(bandId);
            userInfoModel.setBandType(Integer.parseInt(bandType));
            if (registrationId != null) {
                userInfoModel.setRegistrationId(registrationId.toString());
            }
            if (ipAddr != null) {
                userInfoModel.setIpAddr(ipAddr.toString());
            }
            try {
                userInfoDao.insertUserInfo(userInfoModel);
            } catch (Exception e) {
                // TODO: handle exception
                userInfoModel.setNickName(null);
                userInfoDao.insertUserInfo(userInfoModel);
            }
            createNewIMUserService.createIMUser("u_" + userInfoModel.getId(), Constants.DEFAULT_PASSWORD, null);
            userInfoModel.setImAccount("u_" + userInfoModel.getId());
            if (userInfoModel.getNickName() == null) {
                userInfoModel.setNickName("游客" + userInfoModel.getId());
            }
            if (userInfoModel.getAccount() == null) {
                userInfoModel.setAccount("u_" + userInfoModel.getId());
            }
            userInfoDao.updateUserInfo(userInfoModel);
            return userInfoModel;
        }
    }
}

