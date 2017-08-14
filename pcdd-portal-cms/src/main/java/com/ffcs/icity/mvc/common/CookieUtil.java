package com.ffcs.icity.mvc.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
public class CookieUtil {
    
    /**
     * 根据cookie的名称取cookie
     * 
     * @param request
     * @param name
     * @return
     * @date: 2014-12-22 下午1:37:49
     * @version: 1.00.00
     * @history:
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;

        }else{
            return null;
        }   
    }
    

    /**
     * 读取所有cookie到map中
     * 
     * @param request
     * @return
     * @date: 2014-12-22 下午1:38:20
     * @version: 1.00.00
     * @history:
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;

    }
    
    
    /**
     * 根据名称清除cookie
     * 
     * @param request
     * @param response
     * @param cookieName
     * @date: 2014-12-22 下午1:38:35
     * @version: 1.00.00
     * @history:
     */
    public static void removeCookies(HttpServletRequest request, HttpServletResponse response, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                String cookieValue = cookie.getValue();
                if(cookieValue == null || StringUtils.isBlank(cookieValue) || cookieValue.equals(cookieName)){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
    
    
}
