package com.ffcs.icity.mvc.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class HttpRequestUtil {
	/**
	 * 代理服务器IP
	 * 不对外开放，只能在调用请求时当作参数传递
	 */
	private static String proxyIp = "127.0.0.1";		
	
	/**
	 * 代理服务器端口
	 * 不对外开放，只能在调用请求时当作参数传递
	 */
	private static int proxyPort = 8080;	
	
	/**
	 * 默认的编码和解码
	 * 不对外开放
	 */
	private static String encoding = "UTF-8";
	
	/**
	 * 传递的数据类型
	 */
	private static String contentType = "application/x-www-form-urlencoded";  

	/**
	 * 修改数据类型
	 * @param contentType 新的数据类型
	 * @date: 2013-11-25 下午1:58:02
	 * @version: 1.00.00
	 * @history:
	 */
	public static void setContentType(String contentType){
		HttpRequestUtil.contentType = contentType;
	}
	/**
	 * get 方式的HTTP请求
	 * @param getUrl-URL+参数
	 * @return
	 * @throws IOException 
	 */
	public static String get(String getUrl) throws IOException{
		return HttpRequestUtil.httpGet(getUrl, false);
	}
	
	/**
	 * get 方式的HTTP请求,使用代理服务器
	 * @param getUrl-URL+参数
	 * @param proxyIp 代理服务器IP
	 * @param proxyPort 代理服务器端口
	 * @return
	 * @throws IOException
	 */

	public static String get(String getUrl, String proxyIp, int proxyPort) throws IOException{
		HttpRequestUtil.setProxy(proxyIp, proxyPort);
		return HttpRequestUtil.httpGet(getUrl, true);
	}
	/**
	 * get 方式的HTTP请求
	 * @param getUrl URL，不带问号
	 * @param params 参数
	 * @return
	 * @throws IOException
	 */
	public static String get(String getUrl, String params) throws IOException{
		return HttpRequestUtil.httpGet(getUrl + "?" + params, false);
	}
	
	/**
	 * get 方式的HTTP请求,使用代理服务器
	 * @param getUrl URL
	 * @param params 参数
	 * @param proxyIp 代理服务器IP
	 * @param proxyPort 代理服务器端口
	 * @return
	 * @throws IOException
	 */
	public static String get(String getUrl, String params, String proxyIp, int proxyPort) throws IOException{
		HttpRequestUtil.setProxy(proxyIp, proxyPort);
		return HttpRequestUtil.httpGet(getUrl + "?" + params, true);
	}
	
	/**
	 * post方式的HTTP请求
	 * @param post Url
	 * @param params 参数
	 * @return
	 * @throws IOException
	 */
	public static String post(String postUrl, String params) throws IOException{
		return HttpRequestUtil.httpPost(postUrl, params, false);
	}
	
	/**
	 * post方式的HTTP请求
	 * @param post Url
	 * @param params 参数
	 * @param proxyIp 代理服务器IP
	 * @param proxyPort 代理服务器端口
	 * @return
	 * @throws IOException
	 */
	public static String post(String postUrl, String params, String proxyIp, int proxyPort) throws IOException{
		HttpRequestUtil.setProxy(proxyIp, proxyPort);
		return HttpRequestUtil.httpPost(postUrl, params, true);
	}
	
	/**
	 * post方式的HTTP请求,网络流的传递方式，这种模式适用于向服务器传送较大的或者是不容易 获取长度的数据
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String postChunk(String postUrl, String params) throws IOException{
		return HttpRequestUtil.httpChunkPost(postUrl, params, false);
	}
	
	/**
	 * post方式的HTTP请求,网络流的传递方式，这种模式适用于向服务器传送较大的或者是不容易 获取长度的数据
	 * @param post Url
	 * @param params 参数
	 * @param proxyIp 代理服务器IP
	 * @param proxyPort 代理服务器端口
	 * @return
	 * @throws IOException
	 */
	public static String postChunk(String postUrl, String params, String proxyIp, int proxyPort) throws IOException{
		HttpRequestUtil.setProxy(proxyIp, proxyPort);
		return HttpRequestUtil.httpChunkPost(postUrl, params, true);
	}
	
	
	/**
	 * get方式的http请求
	 * @param getUrl - URL+参数
	 * @return
	 * @throws IOException
	 */
	private static String httpGet(String getUrl, boolean useProxy) throws IOException {
//		getUrl = URLEncoder.encode(getUrl);
		URL httpUrl = new URL(getUrl);
		// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		// 判断是否使用代理
		HttpURLConnection connection = null;
		if(useProxy){
			connection = (HttpURLConnection) httpUrl.openConnection(HttpRequestUtil.useProxy(httpUrl));
		}else{
			connection = (HttpURLConnection) httpUrl.openConnection();
		}
		// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
		// 服务器
		connection.connect();
		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), HttpRequestUtil.encoding));
		String lines;
		StringBuffer res = new StringBuffer();
		while ((lines = reader.readLine()) != null) {
			res.append(lines);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		return res.toString();
	}
	
	
	/**
	 * post方式的http请求
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private static String httpPost(String postUrl, String params, boolean useProxy)
			throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		URL httpUrl = new URL(postUrl);
		// 打开连接
		HttpURLConnection connection = null;
		if(useProxy){
			connection = (HttpURLConnection) httpUrl.openConnection(HttpRequestUtil.useProxy(httpUrl));
		}else{
			connection = (HttpURLConnection) httpUrl.openConnection();
		}
		// Output to the connection. Default is
		// false, set to true because post
		// method must write something to the
		// connection
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// Set the post method. Default is GET
		connection.setRequestMethod("POST");
		// Post cannot use caches
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// This method takes effects to
		// every instances of this class.
		// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
		// connection.setFollowRedirects(true);

		// This methods only
		// takes effacts to this
		// instance.
		// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		// Set the content type to urlencoded,
		// because we will write
		// some URL-encoded content to the
		// connection. Settings above must be set before connect!
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				HttpRequestUtil.contentType);  	
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), HttpRequestUtil.encoding);
		// The URL-encoded contend
		// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
		out.write(params);

		out.flush();
		out.close(); // flush and close
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), HttpRequestUtil.encoding));
		String line;
		StringBuffer res = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			res.append(line);
		}
		reader.close();
		connection.disconnect();
		return res.toString();
	}
	
	/**
	 * post方式的HTTP请求，网络流的传递方式，这种模式适用于向服务器传送较大的或者是不容易 获取长度的数据
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private static String httpChunkPost(String postUrl, String params, boolean useProxy)
			throws IOException {
		URL httpUrl = new URL(postUrl);
		HttpURLConnection connection = null;
		if(useProxy){
			connection = (HttpURLConnection) httpUrl.openConnection(HttpRequestUtil.useProxy(httpUrl));
		}else{
			connection = (HttpURLConnection) httpUrl.openConnection();
		}
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				HttpRequestUtil.contentType);
		/**//*
			 * 与readContentFromPost()最大的不同，设置了块大小为5字节
			 */
		connection.setChunkedStreamingMode(5);
		connection.connect();
		/*
		 * 注意，下面的getOutputStream函数工作方式于在readContentFromPost()里面的不同
		 * 在readContentFromPost()里面该函数仍在准备http request，没有向服务器发送任何数据
		 * 而在这里由于设置了ChunkedStreamingMode，getOutputStream函数会根据connect之前的配置 生成http
		 * request头，先发送到服务器。
		 */
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), HttpRequestUtil.encoding);
		out.write(params);

		out.flush();
		out.close(); // 到此时服务器已经收到了完整的http
						// request了，而在readContentFromPost()函数里，要等到下一句服务器才能收到http请求。
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), HttpRequestUtil.encoding));

		out.flush();
		out.close(); // flush and close
		String line;
		StringBuffer res = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			res.append(line);
		}
		reader.close();
		connection.disconnect();
		return res.toString();
	}
	
	/**
	 * 设置代理服务器IP和端口
	 * @param proxyIp
	 * @param proxyPort
	 */
	private static void setProxy(String proxyIp, int proxyPort){
		HttpRequestUtil.proxyIp = proxyIp;
		HttpRequestUtil.proxyPort = proxyPort;
	}
	
	/**
	 * 使用代理服务器
	 * @param httpUrl
	 * @return
	 * @throws IOException
	 */
	private static Proxy useProxy(URL httpUrl) throws IOException {
		/* 构造Proxy对象，以适用于代理上网的方式 */
		InetSocketAddress socketAddress = new InetSocketAddress(
				InetAddress.getByName(HttpRequestUtil.proxyIp),
				HttpRequestUtil.proxyPort);
		return new Proxy(Proxy.Type.HTTP, socketAddress);
	}
	

}
