package com.ffcs.icity.api.aiyiPay.config;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.ffcs.icity.api.module.util.UrlUtil;

import sun.misc.BASE64Encoder;

public class Config {

	public static String url = "https://vip.iyibank.com/cashier/Home";//提交地址
	public static String mch_id = "10618";//商户ID
	public static  String callback_url = "http://103.210.236.195:50001/pcdd-wap/views/module/index/huidiao.html";//前台通知页面(用于展示，没有数据交互)
	public static   String notify_url = "http://103.210.236.195:50001/pcdd-api-client-interface/aiyi/recv";//后台通知页面(用于支付结果接收) 
	 
	
	public static String getErPayUrl(String orderNo,String fee ,String payType ,String mch_id,String Key ) {
		 String out_trade_no = orderNo;//商户订单
		 String body = "充值";//商品描述
		
        String total_fee = fee;//金额 
        String service = "cibalipay";//接口类型编码 cibalipay  cibweixin cibwxh5
        if("2".equals(payType)){
        	 service = "cibweixin";
        }
        String type = "1";//返回方式 0 收银台 1返回json
        String sub_openid = "";//用户 openid，微信公众号支付时必填 （不进行签名，参数跟在sign后）
        String dt = mch_id + out_trade_no + body + callback_url + notify_url + total_fee + service + type + Key;//签名字符串
        String sign = null;
		try {
			sign = md5Encode(dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//签名
        StringBuilder testurl = new StringBuilder();
        testurl.append("?mch_id=" + mch_id);
        testurl.append("&out_trade_no=" + out_trade_no);
        try {
			testurl.append("&body=" + URLEncoder.encode(body,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        testurl.append("&callback_url=" + callback_url);
        testurl.append("&notify_url=" + notify_url);
        testurl.append("&total_fee=" + total_fee);
        testurl.append("&service=" + service);
        testurl.append("&type=" + type);
        testurl.append("&sign=" + sign);
          
       //返回收银台链接地址
    
        
        //下面方法返回返回json，其中包含支付链接(token_id已生成好二维码,pay_info原生支付码可以自行生成二维码)
//        String strJson=send(url+testurl.toString());
        System.out.println(url+testurl.toString());
        return url+testurl.toString();
        
	}
	
	public static String send(String url)
	{
		
		try {  
    		StringBuffer buffer = new StringBuffer();
    		
   
            System.out.println("访问地址:" + url); 
            
            //发送get请求
            URL serverUrl = new URL(url);  
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();  
            conn.setRequestMethod("GET");  
            //必须设置false，否则会自动redirect到重定向后的地址  
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");  
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");  
            conn.addRequestProperty("Referer", "http://matols.com/");  
            conn.connect();  
            
            //将返回的输入流转换成字符串  
            InputStream inputStream = conn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
  
           return buffer.toString();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return "";  
	}
	
	
	
	  public static String md5Encode(String inStr) throws Exception {
	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            e.printStackTrace();
	            return "";
	        }

	        byte[] byteArray = inStr.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
	    }
}
