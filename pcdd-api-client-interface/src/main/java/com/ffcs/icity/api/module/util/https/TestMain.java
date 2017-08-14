package com.ffcs.icity.api.module.util.https;

import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
	private String url = "https://gw.169.cc/interface/Autobank/index.aspx?parter=987827&type=1007&attach=%E5%85%85%E5%80%BC&orderid=2017314611122&callbackurl=http://119.23.144.221:50001/pcdd-api-client-interface/duobao/recv&sign=e60a10fe0252149dede56cbde7996c4e&hrefbackurl=http://119.23.144.221:50001/pcdd-wap/views/module/index/huidiao.html&value=2&onlyqr=Y";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test(){
		String httpOrgCreateTest = url ;
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("authuser","*****");
		createMap.put("authpass","*****");
		createMap.put("orgkey","****");
		createMap.put("orgname","****");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
	}
	
	public static void main(String[] args){
		TestMain main = new TestMain();
		main.test();
	}
}