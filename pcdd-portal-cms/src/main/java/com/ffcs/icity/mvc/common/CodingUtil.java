package com.ffcs.icity.mvc.common;

import java.io.UnsupportedEncodingException;

import org.springframework.util.StringUtils;

public class CodingUtil {
	public static String changeIso2Utf(String name) throws UnsupportedEncodingException{
		if(!StringUtils.hasText(name)){
			return name;
		}
		
		return new String(name.getBytes("ISO-8859-1"),"UTF-8");
	}
}
