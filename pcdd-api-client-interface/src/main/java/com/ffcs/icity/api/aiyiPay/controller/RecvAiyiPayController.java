package com.ffcs.icity.api.aiyiPay.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ffcs.icity.api.aiyiPay.config.Config;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.noticeInfo.dao.INoticeInfoDao;
import com.ffcs.icity.api.module.noticeInfo.entity.NoticeInfo;
import com.ffcs.icity.api.module.payParams.dao.IPayParamsDao;
import com.ffcs.icity.api.module.payParams.entity.PayParams;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.pointChangeLog.entity.PointChangeLog;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.userPayLog.dao.IUserPayLogDao;
import com.ffcs.icity.api.module.userPayLog.entity.UserPayLog;
import com.ffcs.icity.api.module.util.MD5Utils;

public class RecvAiyiPayController  extends NoValidController{

	 @Autowired
	 private IUserPayLogDao userPayLogDao;
	 
	 @Autowired
	 private IUserInfoDao userInfoDao;
	 
	 @Autowired
	 private IPointChangeLogDao pointChangeLogDao;
	 
	 @Autowired
	 private INoticeInfoDao noticeInfoDao;
	 
	 @Autowired
		private IPayParamsDao payParamsDao;
	
	 
	 private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
	        "8", "9", "a", "b", "c", "d", "e", "f"};
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//这里是您接收的xml文件
		Map <String,Object> requestParams = request.getParameterMap();
		Map <String,Object> map=new HashMap<String, Object>();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			map.put(name, valueStr);
		}
         /**
          * 
			String postxml="<xml><charset><![CDATA[uft-8]]></charset><device_info><![CDATA[]]></device_info><err_code><![CDATA[ok]]>
			</err_code><err_msg><![CDATA[ok]]></err_msg><mch_id><![CDATA[1544]]></mch_id><message><![CDATA[ok]]></message><nonce_str>
			<![CDATA[-247370137]]></nonce_str><orderid><![CDATA[1610251044047365485608]]></orderid><out_trade_no><![CDATA[20161025104404495]]>
			</out_trade_no><result_code><![CDATA[0]]></result_code><service><![CDATA[781]]></service><sign><![CDATA[c6e759a13627dd667f412c71638e42ff]]></sign>
			<sign_type><![CDATA[md5]]></sign_type><status><![CDATA[0]]></status><total_fee><![CDATA[10.00]]></total_fee><version><![CDATA[1.0]]></version></xml>";
          */
		 
		try{
			System.out.println("sign:"+map.get("sign").toString());
			PayParams params = payParamsDao.getPayParamsByType("爱益支付");
		if((getSign(map,params.getMchKey())).toUpperCase().equals((map.get("sign").toString()).toUpperCase()))
		{
			
			if(map.get("result_code").toString().equals("0")) //支付成功
			{
				String orderNo=map.get("out_trade_no").toString();
				String tradeAmt=map.get("total_fee").toString();
				String orderStatus=map.get("result_code").toString();
				String accNo=map.get("orderid").toString();
				UserPayLog userPayLog = userPayLogDao.getUserPayLogByOrderNo(orderNo);
				if(userPayLog.getStatus()==0){
					//1付款 2充值 
					if(userPayLog.getOrderType()==2){
						UserInfo user = userInfoDao.getUserInfoById(userPayLog.getUserId());
						user.setPoint(user.getPoint()+Double.parseDouble(tradeAmt));
//						user.setMoney(user.getMoney()+userPayLog.getTotalFee()*10);
						if(user.getAllPoint()==null){
							user.setAllPoint(0.0);
							
						}
						user.setAllPoint(user.getAllPoint()+Double.parseDouble(tradeAmt));
						userInfoDao.updateUserInfo(user);
						
						//添加中奖账变记录
						PointChangeLog pointChangeLog=new PointChangeLog();
						pointChangeLog.setPoint(Double.parseDouble(tradeAmt));
						pointChangeLog.setPointDesc("爱益充值");
						pointChangeLog.setUserId(userPayLog.getUserId());
						pointChangeLog.setPayType(3);
						pointChangeLogDao.insertPointChangeLog(pointChangeLog);
					}
					
					
					
					

					//修改支付记录 
					userPayLog.setResultCode(orderStatus);
					userPayLog.setOutTradeNo(accNo);
					userPayLog.setResultFee(Double.parseDouble(tradeAmt));
					userPayLog.setUpdateTime(new Date());
					userPayLog.setStatus(1);
					if("cibalipay".equals( map.get("service").toString())) {
						userPayLog.setPayDesc("爱益支付宝支付");
					}else{
						userPayLog.setPayDesc("爱益微信支付");
					}
					
					userPayLogDao.updateUserPayLog(userPayLog);
					
					

					//通知消息
					NoticeInfo noticeInfo=new NoticeInfo();
					noticeInfo.setContent("您在爱益支付的金额"+tradeAmt+"元,已经到账");
					noticeInfo.setNoticeType(2);
					noticeInfo.setStatus(0);
					noticeInfo.setTitle("充值到账通知");
					noticeInfo.setUserId(userPayLog.getUserId());
					noticeInfoDao.insertNoticeInfo(noticeInfo);
					
				}
				System.out.println("success");
			}
			else
			{
				System.out.println("交易失败");
			}
		}
		else
		{
			System.out.println("签名错误");
			
		}
		}
		catch(Exception e)
		{
			 System.out.println(e.toString());
			//e.printStackTrace();
		}
	}
	
	

	/**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
	   /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
	 /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    /**
     * 异步通知字段样式如下：
mch_id=10024&out_trade_no=1234456789&orderid=abcd00012&total_fee=0.01&service=cibalipay&result_code=0&sign=12e56e673280a019cc496dde712b4e29 
异步通知验签时取上面样式除（sign）字段的值加上key值拼接成如下格式与(sign)字段的值进行比较：
100241234456789abcd00012&0.01cibalipay06929b166930c48c09fd88dfd17006dcd
     * @param map
     * @param Key
     * @return
     */
    public static String getSign(Map<String,Object> map,String Key){
        
        String result = map.get("mch_id").toString()+map.get("out_trade_no").toString()+map.get("orderid").toString()+map.get("total_fee").toString()+map.get("service").toString()
        				+map.get("result_code").toString() +Key;
        System.out.println(result);
       // System.out.println(result);
        result =MD5Encode(result).toUpperCase();
        System.out.println(result);
        //Util.log("Sign Result:" + result);
        return result;
    }
	 

    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

	  public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        InputStream is = getStringStream(xmlString);
	          Document document = builder.parse(is);

	        //获取到document里面的全部结点
	        NodeList allNodes = document.getFirstChild().getChildNodes();
	        Node node;
	        Map<String, Object> map = new HashMap<String, Object>();
	        int i=0;
	        while (i < allNodes.getLength()) {
	            node = allNodes.item(i);
	            if(node instanceof Element){
	                map.put(node.getNodeName(),node.getTextContent());
	            }
	            i++;
	        }
	        return map;
	  }
	  
	  /**
	   * sign:DFC9F30A9C9395E326E50330E91C4826
mch_id=10618&orderid=17031802290663905153433&out_trade_no=201721461121&result_code=0&service=cibalipay&sign=DFC9F30A9C9395E326E50330E91C4826&total_fee=0.01&
mch_id=10618&orderid=17031802290663905153433&out_trade_no=201721461121&result_code=0&service=cibalipay&sign=DFC9F30A9C9395E326E50330E91C4826&total_fee=0.01&key=7cf147999fc44094ae29d9cca7ecd8d7
952F362A35D8EBD430A7E0A5959BC3FD

	   * @param args
	   */
	  
	  public static void main(String[] args) {
		String result="10618201721461121170318022906639051534330.01cibalipay07cf147999fc44094ae29d9cca7ecd8d7";
		System.out.println(  MD5Encode(result));
		System.out.println(  MD5Utils.getMD5String(result));
		
	}
}
