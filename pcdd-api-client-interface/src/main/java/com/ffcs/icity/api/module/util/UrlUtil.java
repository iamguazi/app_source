package com.ffcs.icity.api.module.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class UrlUtil {
	
	
	 public static String sendGet(String url) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url ;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.setReadTimeout(4000);
	            connection.setConnectTimeout(4000);
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
//	            for (String key : map.keySet()) {
//	                System.out.println(key + "--->" + map.get(key));
//	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	 
	 public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
//	            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            conn.setReadTimeout(4000);
	            conn.setConnectTimeout(4000);
	            // 发送POST请求必须设置如下两行
	            
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }    
	 
	 public static String sendUserNotice(String productName,String createTime,String openId,String url,String templateId,String token){
		 JSONObject json=new JSONObject();
		 JSONObject jsonData=new JSONObject();
		 JSONObject jsonParam1=new JSONObject();
		 JSONObject jsonParam2=new JSONObject();
		 JSONObject jsonParam3=new JSONObject();
		 JSONObject jsonParam4=new JSONObject();
		 try {
			jsonParam1.put("value", "缺货商品已补货啦");
			jsonParam1.put("color", "#173177");
			jsonData.put("first", jsonParam1);

			jsonParam2.put("value", productName);
			jsonParam2.put("color", "#173177");
			jsonData.put("keyword1", jsonParam2);
			
			jsonParam3.put("value", createTime);
			jsonParam3.put("color", "#173177");
			jsonData.put("keyword2", jsonParam3);
			
			jsonParam4.put("value", "欢迎进行选购！");
			jsonParam4.put("color", "#173177");
			jsonData.put("remark", jsonParam4);
			json.put("touser", openId);
			json.put("url", url);
			json.put("topcolor", "#FF0000");
			json.put("template_id", templateId);
			json.put("data", jsonData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String weixinStatus=UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token, json.toString());
		return weixinStatus;
	 }
	 
	 public static void main(String[] args) {
//		System.out.println(UrlUtil.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", XMLUtils.getPrePayMsg("描述", "111", "1", "1")));
		 JSONObject json=new JSONObject();
		 JSONObject jsonData=new JSONObject();
		 JSONObject jsonParam1=new JSONObject();
		 try {
			jsonParam1.put("value", "您预约的商品已经到货");
			jsonParam1.put("color", "#173177");
			jsonData.put("first", new JSONObject(jsonParam1.toString()));

			jsonParam1.put("value", "巧克力");
			jsonData.put("keyword1", new JSONObject(jsonParam1.toString()));
			
			jsonParam1.put("value", "2014年9月16日");
			jsonData.put("keyword2", new JSONObject(jsonParam1.toString()));
			
			jsonParam1.put("value", "欢迎选购！");
			jsonData.put("remark", new JSONObject(jsonParam1.toString()));
			json.put("touser", "ouEykuHMhm1nLqjuN7-aP_04hN0U");
			json.put("url", "http://localhost:8080/fruit-wap/views/module/productDetails/productDetails.html?area_id=1&shop_address=%E5%91%BC%E5%91%BC&area_name=%E9%BC%93%E6%A5%BC&user_id=1&nick_name=a&city_id=1&city_name=%E7%A6%8F%E5%B7%9E&product_id=6");
			json.put("topcolor", "#FF0000");
			json.put("template_id", "4jCuJ7rHZEyR_EE0uvVEDO4SEWh3pI7tLOPX9f5Swu4");
			json.put("data", jsonData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		
//		String a=UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=FjAASF9NYYiSHfWhEtlwXbnrl6LR1-HorRVDvYQ-Z68SJxdEv9X6qN0BWIhdCeFBrDa2h7FWJSEn5glB-AoXjkgc3v9c-2hP2nXIIi8pzU8", json.toString());
//		System.out.println(a);
	 }
}
