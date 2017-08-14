package com.ffcs.icity.api.module.util;

/**
 * 
 */

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;



/**
 * 
 * @Company: 福建讯盟软件有限公司 
 * @author: 黄嘉彬 
 * @date: 2015-1-22
 * @version v 1.0
 *  <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[fromUser]]></FromUserName> 
 <CreateTime>1348831860</CreateTime>
 <MsgType><![CDATA[text]]></MsgType>
 <Content><![CDATA[this is a test]]></Content>
 <MsgId>1234567890123456</MsgId>
 </xml>
 
 
 <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好]]></Content>
</xml>
 */
public class XMLUtils {
	
	public String getMsg(String toUser,String fromUserName,Date createTime,String content ){
		Document document=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Element ToUserName=DocumentHelper.createElement("ToUserName");
		Element FromUserName=DocumentHelper.createElement("FromUserName");
		Element CreateTime=DocumentHelper.createElement("CreateTime");
		Element MsgType=DocumentHelper.createElement("MsgType");
		Element Content=DocumentHelper.createElement("Content");
		ToUserName.addText(toUser);
		FromUserName.addText(fromUserName);
		CreateTime.addText(createTime.getTime()+"");
		MsgType.addText("text");
		Content.addText(content);
		root.add(ToUserName);
		root.add(FromUserName);
		root.add(CreateTime);
		root.add(MsgType);
		root.add(Content);
		return document.asXML();
	}
	
	/**
	 * <xml>
	<appid>wxf380b30bb430d138</appid>
	<attach>支付测试</attach>
	<body>JSAPI支付测试</body>
	<mch_id>1240459502</mch_id>
	<nonce_str>e61463f8efa94090b1f366cccfbbb444</nonce_str>
	<notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>
	<openid>orS1LuKbo6nQW9S6cPvu9poDwogA</openid>
	<out_trade_no>141251190</out_trade_no>
	<spbill_create_ip>14.23.150.211</spbill_create_ip>
	<total_fee>1</total_fee>
	<trade_type>JSAPI</trade_type>
	<sign>89D6103A6109DA013A7E783BB2936EE7</sign>
</xml>
	 * @param toUser
	 * @param fromUserName
	 * @param createTime
	 * @param content
	 * @return
	 */
	
	public static String getPrePayMsg(String appidStr,String desc,String mchId,String nonceStr,String outTradeNo,
			String openId,String spbillCreateIp,String totalFee,String mySign ,String notifyUrl){
		Document document=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Element appid=DocumentHelper.createElement("appid");
//		Element attach=DocumentHelper.createElement("attach");
		Element body=DocumentHelper.createElement("body");
		Element mch_id=DocumentHelper.createElement("mch_id");
		Element notify_url=DocumentHelper.createElement("notify_url");
		Element nonce_str=DocumentHelper.createElement("nonce_str");
		Element openid=DocumentHelper.createElement("openid");
		Element out_trade_no=DocumentHelper.createElement("out_trade_no");
		Element spbill_create_ip=DocumentHelper.createElement("spbill_create_ip");
		Element total_fee=DocumentHelper.createElement("total_fee");
		Element trade_type=DocumentHelper.createElement("trade_type");
		Element sign=DocumentHelper.createElement("sign");
		appid.addText(appidStr);
//		attach.addText("支付测试");
		body.addText(desc);
		mch_id.addText(mchId);
		nonce_str.addText(nonceStr);
		notify_url.addText(notifyUrl);
		openid.addText(openId);
		out_trade_no.addText(outTradeNo);
		spbill_create_ip.addText(spbillCreateIp);
		total_fee.addText(totalFee);
		trade_type.addText("JSAPI");
		sign.addText(mySign);
		root.add(appid);
//		root.add(attach);
		root.add(body);
		root.add(mch_id);
		root.add(notify_url);
		root.add(nonce_str);
		root.add(openid);
		root.add(out_trade_no);
		root.add(spbill_create_ip);
		root.add(total_fee);
		root.add(trade_type);
		root.add(sign);
		return document.asXML();
	}
	
	//用户地理位置
	public static Map<String, String> getLocationXML(String xml){
		try {
			Document document=DocumentHelper.parseText(xml);
			String openId=document.getRootElement().elementText("FromUserName");
			String Latitude=document.getRootElement().elementText("Latitude");
			String Longitude=document.getRootElement().elementText("Longitude");
			Map<String, String>locationParam=new HashMap<String, String>();
			locationParam.put("openId", openId);
			locationParam.put("Latitude", Latitude);
			locationParam.put("Longitude", Longitude);
			return locationParam;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//微信消息应答
	
	public static String sendUserMsg(String XMLStr){
		String toUser="";
		String fromUser="";
		try {
			Document document=DocumentHelper.parseText(XMLStr);
			Element element = document.getRootElement();
			toUser=element.elementText("ToUserName");
			fromUser=element.elementText("FromUserName");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Document document=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Element ToUserName=DocumentHelper.createElement("ToUserName");
		Element FromUserName=DocumentHelper.createElement("FromUserName");
		Element CreateTime=DocumentHelper.createElement("CreateTime");
		Element MsgType=DocumentHelper.createElement("MsgType");
		Element Content=DocumentHelper.createElement("Content");
		ToUserName.addText(fromUser);
		FromUserName.addText(toUser);
		CreateTime.addText(new Date().getTime()+"");
		MsgType.addText("text");
		Content.addText("感谢使用本微信号");
		root.add(ToUserName);
		root.add(FromUserName);
		root.add(CreateTime);
		root.add(MsgType);
		root.add(Content);
		return document.asXML();
	}
	
	public static String sendUserMsgByGuanzhu(String XMLStr){
		String toUser="";
		String fromUser="";
		try {
			Document document=DocumentHelper.parseText(XMLStr);
			Element element = document.getRootElement();
			toUser=element.elementText("ToUserName");
			fromUser=element.elementText("FromUserName");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Document document=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Element ToUserName=DocumentHelper.createElement("ToUserName");
		Element FromUserName=DocumentHelper.createElement("FromUserName");
		Element CreateTime=DocumentHelper.createElement("CreateTime");
		Element MsgType=DocumentHelper.createElement("MsgType");
		Element Content=DocumentHelper.createElement("Content");
		ToUserName.addText(fromUser);
		FromUserName.addText(toUser);
		CreateTime.addText(new Date().getTime()+"");
		MsgType.addText("text");
		Content.addText("欢迎关注本微信号");
		root.add(ToUserName);
		root.add(FromUserName);
		root.add(CreateTime);
		root.add(MsgType);
		root.add(Content);
		return document.asXML();
	}
	
	
	//生成app的支付id
	public static String getPrePayMsgByApp(String appidStr,String desc,String mchId,String nonceStr,String outTradeNo,
			String spbillCreateIp,String totalFee,String mySign ,String notifyUrl){
		Document document=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Element appid=DocumentHelper.createElement("appid");
//		Element attach=DocumentHelper.createElement("attach");
		Element body=DocumentHelper.createElement("body");
		Element mch_id=DocumentHelper.createElement("mch_id");
		Element notify_url=DocumentHelper.createElement("notify_url");
		Element nonce_str=DocumentHelper.createElement("nonce_str");
		Element out_trade_no=DocumentHelper.createElement("out_trade_no");
		Element spbill_create_ip=DocumentHelper.createElement("spbill_create_ip");
		Element total_fee=DocumentHelper.createElement("total_fee");
		Element trade_type=DocumentHelper.createElement("trade_type");
		Element sign=DocumentHelper.createElement("sign");
		appid.addText(appidStr);
//		attach.addText("支付测试");
		body.addText(desc);
		mch_id.addText(mchId);
		nonce_str.addText(nonceStr);
		notify_url.addText(notifyUrl);
		out_trade_no.addText(outTradeNo);
		spbill_create_ip.addText(spbillCreateIp);
		total_fee.addText(totalFee);
		trade_type.addText("APP");
		sign.addText(mySign);
		root.add(appid);
//		root.add(attach);
		root.add(body);
		root.add(mch_id);
		root.add(nonce_str);
		root.add(notify_url);
		root.add(out_trade_no);
		root.add(spbill_create_ip);
		root.add(total_fee);
		root.add(trade_type);
		root.add(sign);
		return document.asXML();
	}
	public static String getXML(String xml){
		try {
			Document document=DocumentHelper.parseText(xml);
			return document.getRootElement().elementText("prepay_id");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static PayResult getPayResult(String xml){
		PayResult payResult=new PayResult();
		try {
			Document document=DocumentHelper.parseText(xml);
			Element element = document.getRootElement();
			payResult.setAppid(element.elementText("appid"));
			payResult.setFeeType(element.elementText("fee_type"));
			payResult.setOpenid(element.elementText("openid"));
			payResult.setOutTradeNo(element.elementText("out_trade_no"));
			payResult.setResultCode(element.elementText("result_code"));
			payResult.setTimeEnd(element.elementText("time_end"));
			payResult.setTotalFee(element.elementText("total_fee"));
			payResult.setTradeType(element.elementText("trade_type"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payResult;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(Utils.getTimestamp());;
//		System.out.println(getXML("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[mch_id参数格式错误]]></return_msg></xml>"));
	}
}
