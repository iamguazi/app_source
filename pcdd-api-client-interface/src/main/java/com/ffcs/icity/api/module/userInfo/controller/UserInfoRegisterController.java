package com.ffcs.icity.api.module.userInfo.controller;


import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.comm.Constants;
import com.ffcs.icity.api.module.IM.service.CreateNewIMUserService;
import com.ffcs.icity.api.module.messageLogs.service.SMSSenderService;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.util.MD5Utils;
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

public class UserInfoRegisterController extends NoValidController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoRegisterController.class);
    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */

    @Autowired
    private IUserInfoDao userInfoDao;


    @Autowired
    private SMSSenderService senderService;

    @Autowired
    private CreateNewIMUserService createNewIMUserService;

    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "account");
        assertNotEmpty(requestArgument, "password");
    }

    /* (non-Javadoc)
     * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
     */
    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        long t1 = System.currentTimeMillis();

        String account = requestArgument.get("account").toString();
        String password = requestArgument.get("password").toString();
        LOGGER.info("user register.account is {}", account);
        Object registrationId = requestArgument.get("registration_id");
        Object code = requestArgument.get("code");
        Object ipAddr = requestArgument.get("ip");


        UserInfo user = userInfoDao.getUserInfoByAccount(account);
        if (user != null) {
            throw new ApiException("用户已存在");
        } else {
            UserInfo userInfoModel = new UserInfo();
            userInfoModel.setAccount(account);
            userInfoModel.setPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(password))));
            userInfoModel.setBandId("0");
            userInfoModel.setBandType(0);
            if (registrationId != null) {
                userInfoModel.setRegistrationId(registrationId.toString());
            }

            if (code != null) {
                userInfoModel.setCode(code.toString());
            }
            if (ipAddr != null) {
                userInfoModel.setIpAddr(ipAddr.toString());
            }
            userInfoDao.insertUserInfo(userInfoModel);
            createNewIMUserService.createIMUser("u_" + userInfoModel.getId(), Constants.DEFAULT_PASSWORD, null);
            userInfoModel.setImAccount("u_" + userInfoModel.getId());
            userInfoDao.updateUserInfo(userInfoModel);
            LOGGER.info("huanxin user register.use {} ms", (System.currentTimeMillis() - t1));
            return userInfoModel;
        }
    }

    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
        // TODO Auto-generated method stub
        Object isWap = requestArgument.get("is_wap");
        if (isWap != null && "1".equals(isWap.toString())) {
            return new String[]{"no_valid"};
        } else {
            return new String[]{requestArgument.get("password").toString()};
        }
    }
}

