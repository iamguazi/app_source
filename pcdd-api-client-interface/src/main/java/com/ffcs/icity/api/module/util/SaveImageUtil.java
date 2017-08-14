package com.ffcs.icity.api.module.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SaveImageUtil {
	 public static String getRoudomNo(){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			return df.format(new Date()).toString()+(int)(new Random().nextDouble()*(100000 - 10000) + 10000);

		}

	 public static InputStream getInputStream(String url ) {



	       InputStream is = null;

	       System.out.println(url);
	       try {

	           URL urlGet = new URL(url);

	           HttpURLConnection http = (HttpURLConnection) urlGet

	                   .openConnection();

	           http.setRequestMethod("GET"); // 必须是get方式请求

	           http.setRequestProperty("Content-Type",

	                   "application/x-www-form-urlencoded");

	           http.setDoOutput(true);

	           http.setDoInput(true);

	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

	           http.connect();

	           /**
	            * 如果不是图片类型
	            */
	           if(!http.getContentType().equals("image/jpeg")){
	        	   return null;
	           }
	           // 获取文件转化为byte流
	           is = http.getInputStream();

	       } catch (Exception e) {

	           e.printStackTrace();

	       }

	       return is;

	   }
	 
	 
	 
	 
	 
	/**
	 * 保存文件
	 * @param url
	 * @return
	 * @throws Exception
	 */
	 public  static  String saveImageToDisk(String url) throws Exception {
		 
		   String fileUrl="";
	       InputStream inputStream = getInputStream(url);
	     
	     
	       byte[] data = new byte[1024];

	       int len = 0;

	       FileOutputStream fileOutputStream = null;

	       try {
	    	   String saveFilePath=PropertyHolder.getProperty().IMG_SAVE_ROOT_DIR +
	    	   					 (String) PropertyHolder.getProperty().map.get("user_photo");
	    	   fileUrl=saveFilePath+"user_photo"+getRoudomNo()+".jpg";
	           fileOutputStream = new FileOutputStream(PropertyHolder.getProperty().IMG_UPLOAD_ROOT_DIR+fileUrl);
	           while ((len = inputStream.read(data)) != -1) {
	        	   
	               fileOutputStream.write(data, 0, len);

	           }

	       } catch (IOException e) {

	           e.printStackTrace();

	       } finally {

	           if (inputStream != null) {

	               try {

	                   inputStream.close();

	               } catch (IOException e) {

	                   e.printStackTrace();

	               }

	           }

	           if (fileOutputStream != null) {

	               try {

	                   fileOutputStream.close();

	               } catch (IOException e) {

	                   e.printStackTrace();

	               }

	           }

	       }
	       return fileUrl;
	 }
	 
}
