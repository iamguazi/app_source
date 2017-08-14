package com.ffcs.icity.api.weixinPay.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotBlankString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
 
import com.ffcs.icity.api.module.userPayLog.dao.IUserPayLogDao;
import com.ffcs.icity.api.module.userPayLog.entity.UserPayLog;
import com.ffcs.icity.api.module.util.MD5Utils;
import com.ffcs.icity.api.module.util.PayParam;
import com.ffcs.icity.api.module.util.UrlUtil;
import com.ffcs.icity.api.module.util.Utils;
import com.ffcs.icity.api.module.util.XMLUtils;
import com.ffcs.icity.api.support.Arith;
@Controller
public class GetPayParamController extends NoValidController {

	@Value("${appid}")
	private String appid;
	
	@Value("${mch_id}")
	private String mch_id;
	
	@Value("${notify_url}")
	private String notify_url;
	
	@Value("${key}")
	private String key;
	
	
	@Autowired
	private IUserPayLogDao userPayLogDao;
	
	
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotBlankString(requestArgument, "body");
		assertNotBlankString(requestArgument, "nonce_str");
		assertNotBlankString(requestArgument, "spbill_create_ip");
		assertNotBlankString(requestArgument, "type");
		assertNotBlankString(requestArgument, "order_no");
		
	}
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		// TODO Auto-generated method stub
		String body=requestArgument.get("body").toString();
		String type=requestArgument.get("type").toString();//1付款  2充值
		Object freeFee=requestArgument.get("free_fee");
		
		
		
		//组装参数
		String orderNo=requestArgument.get("order_no").toString();
		 
		UserPayLog userPayLog=null;
		String totalFee="0";
		userPayLog=userPayLogDao.getUserPayLogByOrderNo(orderNo);
		totalFee=userPayLog.getTotalFee()*100+"";
			
		if (totalFee.contains(".")){
			totalFee=totalFee.substring(0, totalFee.indexOf("."));
		}
		String paramStr="appid="+appid+
						"&body="+body+
						"&mch_id="+mch_id+
						"&nonce_str="+requestArgument.get("nonce_str").toString()+
						"&notify_url="+notify_url+
						"&out_trade_no="+orderNo+"_"+totalFee+
						"&spbill_create_ip="+requestArgument.get("spbill_create_ip").toString()+
						"&total_fee="+totalFee+
						"&trade_type="+"APP"+
						"&key="+key;
		System.out.println("paramStr:"+paramStr);;
		String paramByMD5=MD5Utils.getMD5String(paramStr).toUpperCase();
		String XMLStr=XMLUtils.getPrePayMsgByApp(appid, body, mch_id, requestArgument.get("nonce_str").toString(),
				orderNo+"_"+totalFee, requestArgument.get("spbill_create_ip").toString(),
				totalFee, paramByMD5,notify_url);
		
		System.out.println("XMLStr:"+XMLStr);
		//请求预支付id
		
		String prepayid=null;
		if(userPayLog!=null&&userPayLog.getPrePayId()!=null&&userPayLog.getTotalFee()==Arith.div(Integer.parseInt(totalFee), 10, 2) ){
			prepayid=userPayLog.getPrePayId();
		}else if(userPayLog!=null){
			String recvXml=UrlUtil.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", XMLStr);
			System.out.println("recvXml:"+recvXml);
			 prepayid=XMLUtils.getXML(recvXml);
			 userPayLog.setPrePayId(prepayid);
			 //支付方式 1 余额 2微信 3 支付宝      db_column: PAY_TYPE 
			 userPayLog.setPayType(2);
			 userPayLogDao.updateUserPayLog(userPayLog);
		}
			
		PayParam param=new PayParam();
		param.setNonceStr(requestArgument.get("nonce_str").toString());
		param.setPackageStr(prepayid);
		param.setSignType("MD5");
		param.setTimestamp(Utils.getTimestamp()/1000+"");
		param.setAppid(appid);
		param.setPartnerid(mch_id);
		String paramStrByPay="appid="+appid+
		"&noncestr="+requestArgument.get("nonce_str").toString()+
		"&package="+"Sign=WXPay"+
		"&partnerid="+mch_id+
		"&prepayid="+prepayid+
		"&timestamp="+param.getTimestamp()+
		"&key="+key;
		param.setPaySign(MD5Utils.getMD5String(paramStrByPay).toUpperCase());
		
		
		
		
		return param;
		

	}
	/**
	 * 生成订单号
	 * @return
	 */
	public String getOrderNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date()).toString()+(int)(new Random().nextDouble()*(100000 - 10000) + 10000);

	}
	public static void main(String[] args) {
		System.out.println(new Date().getTime()/1000);
		System.out.println(MD5Utils.getMD5String("appid=wxc9c9e166d73d2008&noncestr=39EI13P88SNDC25&package=Sign=WXPay&partnerid=1254117001&prepayid=wx201507211404382b3624f4e00387523849&timestamp=1437458678&key=izxlsdaiwalaks9dlSJDLKSA9sdjslax").toUpperCase());
	}
}
