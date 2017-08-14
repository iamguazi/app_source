package com.ffcs.icity.api.module.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Property implements InitializingBean{

    @Value("${imgUploadRootDir}")
    public String IMG_UPLOAD_ROOT_DIR;          // 图片存储服务器根目录
    
    @Value("${imgSaveRootDir:upload/}")
    public String IMG_SAVE_ROOT_DIR;            // 数据库存储根目录
    
    @Value("${imgUploadUserPhoto}")
    public String IMG_UPLOAD_USER_THOTO;        // 用户头像目录
    
    @Value("${imgServerUrl}")
    public String IMG_SERVER_URL;               // 图片显示的根路径
    
    public Map<String, Object> map;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        map = new HashMap<String, Object>();
        map.put("user_photo", IMG_UPLOAD_USER_THOTO);
    }
    
}
