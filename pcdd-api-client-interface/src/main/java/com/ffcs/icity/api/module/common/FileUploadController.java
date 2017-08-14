package com.ffcs.icity.api.module.common;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotBlankString;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.util.FileUploadUtil;
import com.ffcs.icity.api.module.util.PropertyHolder;
import com.ffcs.icity.api.support.RequestContextHolder;

public class FileUploadController extends NoValidController{
    
	 @Value("${imgServerUrl}")
	    public String imgServerUrl;          // 图片存储服务器根目录
	    
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
    }

    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
    	String businessStr=(String) PropertyHolder.getProperty().map.get((String) requestArgument.get("business_type"));
        if(businessStr==null){
        	businessStr=(String) requestArgument.get("business_type");
        	if(businessStr==null){
        		businessStr="userPhoto/";
        	}else{
        		businessStr=businessStr+"/";
        	}
        	
        }
    	try {
            String[] filePath = FileUploadUtil.uploadFile(RequestContextHolder.getRequest(), 
                    PropertyHolder.getProperty().IMG_UPLOAD_ROOT_DIR, PropertyHolder.getProperty().IMG_SAVE_ROOT_DIR +
                    businessStr);
            return imgServerUrl+filePath[0].toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApiException("文件上传失败");
        }
    }

    @Override
    public Map<String, Object> resolvePostArgument(HttpServletRequest request)
            throws InvalidRequestArgumentException {
        Map<String, Object> requestArgument = null;
        try {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            requestArgument = resolveGetArgument(request);
        } catch (Exception e) {
            if (!(e instanceof InvalidRequestArgumentException)) {
                throw new InvalidRequestArgumentException("failure to resolve argument", e);
            } else {
                throw (InvalidRequestArgumentException) e;
            }
        }
        return requestArgument;
    }
    
    
    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
    	// TODO Auto-generated method stub
    	return new String[] {"no_valid"};
    }
    
    
    

}
