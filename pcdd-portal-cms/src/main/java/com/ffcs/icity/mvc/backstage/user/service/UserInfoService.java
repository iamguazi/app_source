package com.ffcs.icity.mvc.backstage.user.service;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.user.entity.RobotXls;
import com.ffcs.icity.mvc.backstage.user.entity.UserInfo;
import com.ffcs.icity.mvc.common.HttpRequestUtil;
import com.ffcs.icity.mvc.common.MD5Utils;

@Service
public class UserInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

	
	@Autowired
	private UserInfoDao userInfoDao;

	@Value("${saveOrUpdateUserUrl}")
	private String saveOrUpdateUserUrl;
	
	
	public boolean saveOrUpdate(UserInfo userInfo){
		
	    if(userInfo.getId() != null ){
//	    	UserInfo userInfoData = userInfoDao.get(userInfo.getId());
//	    	if(userInfoData == null){
//	    		logger.error("用户id不存在"+userInfo.getId());
//	    		return false;
//	    	}
//	    	if(!userInfoData.getPassword().equals(userInfo.getPassword())){
//	    		userInfo.setPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(userInfo.getPassword()))));
//	    	}
	    	userInfoDao.update(userInfo);
	    	
	    	return true;
	    }else{
	    	String responseText = "";
		    JSONObject json = new JSONObject();
	    	json.put("userInfo", userInfo);
		    try {
		    	responseText = HttpRequestUtil.post(saveOrUpdateUserUrl, json.toString());
				JSONObject resJson = JSONObject.fromObject(responseText);
				if (resJson.getInt("result_code") == 0) {
					return  true;
				} else {
					logger.error("saveOrUpdate error : {}",resJson.getString("result_desc"));
					return false;
				}
		    } catch (IOException e) {
				logger.error("saveOrUpdate error : {}",e.getLocalizedMessage());
				return false;
			}
//	    	userInfo.setPassword(MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String(userInfo.getPassword()))));
	    }
	    
	    
	}
	
	public void resetPassword(Long id){
		String password = MD5Utils.getMD5String(MD5Utils.getMD5String(MD5Utils.getMD5String("123456")));
		userInfoDao.resetPassword(id, password);
	}
	
    public boolean isAccountExits(String account,Long id)  {
    	Integer count = userInfoDao.isAccountExits(account, id);
    	return  count == 0;
    }
    
    public void updateStatus(){
    	
    }
    
    public void batchUpdateRobot(List<RobotXls> robotXlss){
    	userInfoDao.batchUpdateRobotXls(robotXlss);
    }
}
