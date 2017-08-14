package com.ffcs.icity.api.baseModule;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotBlankString;
import static com.ffcs.icity.api.util.RequestArgumentAssert.assertValidTimestamp;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ffcs.icity.api.core.JSONController;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.util.MD5Utils;
import com.ffcs.icity.api.support.BaseApplicationConfig.FieldDescription;
import com.ffcs.icity.api.util.CrytoUtils;
import com.ffcs.icity.api.util.JSONHelper;
import com.ffcs.icity.api.util.RequestUtils;

public abstract class NoValidController extends JSONController{
    
    @Value("${openAccess:false}")
    private boolean openAccess;
    

	@FieldDescription("验证请求签名所用的APP KEY")
	private  static String appKey = "sh3pcdd";
	
	
	private static String appsecret = "RP3ee12CnCibO5VQ";

	
	private static String ipaddr=null;

	private static final Logger logger = LoggerFactory.getLogger(NoValidController.class);

    @Override
    public Object resolveAndVerifyArgument(HttpServletRequest request)
            throws InvalidRequestArgumentException {
        Map<String, Object> requestArgument = resolveArgument(request);
        requestArgument.put("ip", request.getRemoteAddr());
        verifyBusiArgument(requestArgument);
//        verifySign(requestArgument);
        return requestArgument;
    }
    
    
    
    
    
    public void verifySign(Map<String, Object> requestArgument) throws InvalidRequestArgumentException {
    	
//		if (!isVerifyRequestSign()) {
//			return;
//		}
    	 
		
		for(String key:requestArgument.keySet()){
			if("no_valid".equals(requestArgument.get(key).toString())){
				throw new InvalidRequestArgumentException("非法请求");
			}
		}
		String[] param=getSignItems(requestArgument);
		if(param!=null){
			for(String vo:param){
				if("no_valid".equals(vo)){
					if(ipaddr==null){
						return;
					}
					//验证ip 只有是本地ip才能免验证
					if(ipaddr.equals(requestArgument.get("ip").toString())){
						return;
					}else{
						throw new InvalidRequestArgumentException("非法请求");
					}
					 
				}
				 
			 }
		}
    	assertValidTimestamp(requestArgument, "timestamp");
		assertNotBlankString(requestArgument, "sign");
		assertNotBlankString(requestArgument, "user_id");
		
		boolean isValidSign = verifySign(getSignItems(requestArgument),(String) requestArgument.get("sign"), (String) requestArgument.get("timestamp"), (String) requestArgument.get("user_id"), appKey, 0);
		if (!isValidSign) {
			throw new InvalidRequestArgumentException("请求频繁");
		}

	}
    
    
	/**
	 * 验证请求的数字签名.
	 * 
	 * @param sign 待校验的数字签名串
	 * @param timestampInRequest 请求中的时间戳,格式：yyyy-MM-dd HH:mm:ss
	 * @param signItems 生成请求签名中MD5部分需要的内容
	 * @param appKey  客户端的appKey
	 * @param expiry  请求签名过期时间(单位:秒),当expiry为0时，不进行请求签名过期验证
	 * @return true 表示签名验证通过,false 表示签名验证失败
	 */
	public static boolean verifySign(String[] param,String sign, String timestampInRequest, String userId, String appKey, int expiry) {
		try {
			if (StringUtils.isBlank(sign) || StringUtils.isBlank(timestampInRequest)) {
				logger.debug("sign or timestamp is blank");
				return false;
			}

			 
			 
			if (expiry > 0) {
				String parseDatePatterns = "yyyy-MM-dd HH:mm:ss";
				Date requestDate = DateUtils.parseDate(timestampInRequest, parseDatePatterns);
				Date expiredDate = DateUtils.addSeconds(requestDate, expiry);
				if (new Date().after(expiredDate)) {
					if (logger.isDebugEnabled()) {
						logger.debug("request expired - timestampInRequest:{},expiredDate:{}", timestampInRequest, DateFormatUtils.format(expiredDate, parseDatePatterns));
					}
					return false;
				}
			}

			// 验证MD5部分
			String str=appKey+","+timestampInRequest+","+userId;
			if(param!=null){
				for(String vo:param){
					 
					 str=str+","+vo;
				 }
			}
			 
			 System.out.println(str);
			String baseStr=MD5Utils.BASE64Encode(MD5Utils.aesEncrypt(str, appsecret));
			String md5 =MD5Utils.getMD5String(MD5Utils.getMD5String(baseStr));
			System.out.println(md5);
			boolean b = md5.equals(sign);
			if(!b){
			    logger.debug("sign[{}] and md5InServer[{}] is inconsistent",sign,md5);
			}
			return b;
		} catch (Exception e) {
			logger.debug("failure to verify sign["+sign+"]", e);
		}
		return false;

	}
    
    @Override
    public Map<String, Object> resolveArgument(HttpServletRequest request) throws InvalidRequestArgumentException {
		Map<String, Object> requestArgument = null;
		Map<String, Object> result = new HashMap<String, Object>();
		if ("GET".equals(request.getMethod())) {
			requestArgument= resolveGetArgument(request);
		} else {
			requestArgument= resolvePostArgument(request);
		}
		for(String key:requestArgument.keySet()){
			if(!"".equals(requestArgument.get(key))){
				result.put(key, requestArgument.get(key));
			}
		}
		return result;
	}

    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
        return null;
    }

    public Map<String, Object> getParamsByRequest(String json){
    	Map<String, Object> requestArgument =new HashMap<String, Object> ();
    	for(String str:json.split("&")){
    		requestArgument.put(str.split("=")[0], str.split("=")[1]);
    	}
		return requestArgument;
    }
    @Override
    public Map<String, Object> resolvePostArgument(HttpServletRequest request) throws InvalidRequestArgumentException {
		Map<String, Object> requestArgument = null;
		try {
			String json = IOUtils.toString(request.getInputStream(),"UTF-8");
			if (StringUtils.isBlank(json)) {
				json=request.getParameter("param");
				System.out.println(json);
				requestArgument = JSONHelper.toMap(json);
//				throw new InvalidRequestArgumentException("request body is empty");
			}
			System.out.println(json);
			if(json.contains("&")){
				requestArgument=getParamsByRequest(json);
			}else{
				
				requestArgument = JSONHelper.toMap(json);
			}
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
    public void out(HttpServletResponse response, String content) {
        this.logger.debug("response:{}", content);
        if(this.openAccess == true){
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(content);
        } catch (Exception e) {
            this.logger.error("failure to send response data.caused by:"+e.getLocalizedMessage(), e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        fireEvent(content);
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    
    public static void main(String[] args) {
    	//		签名规则 appkey+","+timestamp+","+userId
    	
		String baseStr=MD5Utils.BASE64Encode(MD5Utils.aesEncrypt("pcdd,2017-02-05 22:20:54", appsecret));
		System.out.println(baseStr);
		String md5 =MD5Utils.getMD5String(MD5Utils.getMD5String(baseStr));
    	System.out.println(md5);
	}
    
}
