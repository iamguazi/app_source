package com.ffcs.icity.api.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.demo.dao.DemoDao;
import com.ffcs.icity.api.demo.entity.Demo;
import com.ffcs.icity.api.module.util.MD5Utils;
import com.ffcs.icity.api.module.util.UrlUtil;


public class DemoController extends NoValidController {

    
    @Autowired
    private DemoDao demoDao;
    
    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {

        System.out.println(requestArgument.get("name"));
    }

    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        Demo d = demoDao.get(Long.parseLong((String)requestArgument.get("id")));
        return d.getPassword();
    }
	
    public static void main(String[] args) {
		String school_no = "yong";
		String send_name = "李四";
		String send_limit = "2015年级管理员";
		String type = "1";
		String key = "96DB22C1";
		String sign=school_no +send_name+send_limit + type +key;
		String a=UrlUtil.sendPost("http://hsktest001.eicp.net:17657/SendSMS/RecSMS", "school_no=yong" +
				"& send_name=李四" +
				"&send_limit=2015年级管理员" +
				"&type=1" +
				"&sign=D176963CCC7D4475EA4597B0A3EE3302");
		System.out.println(a);
		System.out.println(MD5Utils.getMD5String(sign).toUpperCase());
	}
	
}
