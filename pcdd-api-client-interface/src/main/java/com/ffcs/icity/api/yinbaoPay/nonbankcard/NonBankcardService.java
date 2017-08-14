package com.ffcs.icity.api.yinbaoPay.nonbankcard;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ffcs.icity.api.yinbaoPay.utils.HttpUtils;
import com.ffcs.icity.api.yinbaoPay.utils.MD5Util;


/**
 * 
 * @author lu.li
 *
 */
public class NonBankcardService {
	private static Log log 						= LogFactory.getLog(NonBankcardService.class);
	private static String p0_Cmd 				= "ChargeCardDirect"; 												// 请求命令名称
	private static String decodeCharset 		= "UTF-8"; 													// 字符方式
	private static String partner 		=  "19328"; 		// 商户编号
	private static String keyValue 		= "b706a8bc9c668b04cfb9b77a1d07e00c"; 		// 商户密钥
	private static String annulCardReqURL 	= "http://wytj.9vpay.com/PayBank.aspx"; 	// 请求地址
	
	// 卡号卡密采用算法模式，当前固定为该值
	private static String annulCardReqDESMode = "1";
	// 使用应答机制
	private static String NEEDRESPONSE	= "1";
	
	public static void main(String[] args) {
		System.out.println(pay("SDB", "1", "20171542225823", "http://").getReturnmsg());
	}
	/**
	 * 消费请求
	 * 该方法是根据《易宝支付非银行卡支付专业版接口文档 v3.0》对发起支付请求进行的封装
	 * 具体参数含义请仔细阅读《易宝支付非银行卡支付专业版接口文档 v3.0》
	 * 商户订单号
	 * @param p2_Order
	 * 订单金额
	 * @param p3_Amt
	 * 是否较验订单金额
	 * @param p4_verifyAmt
	 * 产品名称
	 * @param p5_Pid
	 * 产品类型
	 * @param p6_Pcat
	 * 产品描述
	 * @param p7_Pdesc
	 * 通知地址
	 * @param p8_Url
	 * 扩展信息
	 * @param pa_MP
	 * 卡面额组
	 * @param pa7_cardAmt
	 * 卡号组
	 * @param pa8_cardNo
	 * 支付方式
	 * @param pd_FrpId
	 * 通知是否需要应答
	 * @param pr_NeedResponse
	 * 用户ID
	 * @param pz_userId
	 * 用户注册时间
	 * @param pz1_userRegTime
	 * @return
	 */
	public static NonBankcardPaymentResult pay(String banktype,
			 								   String paymoney,
			 								   String ordernumber,
			 								   String callbackurl) {
		
		// 卡号和卡密不得为空
		if(banktype == null || banktype.equals("") || paymoney == null || paymoney.equals("")
				|| ordernumber == null || ordernumber.equals("")|| callbackurl == null || callbackurl.equals("")) {
			log.error("banktype or paymoney or ordernumber or callbackurl is empty.");
			throw new RuntimeException("banktype or paymoney or ordernumber or callbackurl is empty.");
		}
		String hrefbackurl="";
		String attach=""; 
		// 生成hmac，保证交易信息不被篡改,关于hmac详见《易宝支付非银行卡支付专业版接口文档 v3.0》
		String sign = "";
		//拼接字符串
		String tempStr="partner="+partner+"&banktype="+banktype+"&paymoney="+paymoney+
				"&ordernumber="+ordernumber+"&callbackurl="+callbackurl+keyValue;
		sign = MD5Util.string2MD5(tempStr);
		// 封装请求参数，参数说明详见《易宝支付非银行卡支付专业版接口文档 v3.0》
		Map reqParams = new HashMap();
		reqParams.put("partner", partner);
		reqParams.put("banktype", banktype);
		reqParams.put("paymoney", paymoney);
		reqParams.put("ordernumber", ordernumber);
		reqParams.put("callbackurl", callbackurl);
//		reqParams.put("hrefbackurl", hrefbackurl);
//		reqParams.put("attach", attach);
		reqParams.put("sign", sign);
		
		List responseStr = null;
		try {
			// 发起支付请求
			log.debug("Begin http communications,request params[" + reqParams
					+ "]");
			responseStr = HttpUtils.URLPost(annulCardReqURL, reqParams);
			log.debug("End http communications.responseStr:" + responseStr);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		if (responseStr.size() == 0) {
			log.error("no response.");
			throw new RuntimeException("no response.");
		}
		// 创建非银行卡专业版消费请求结果
		NonBankcardPaymentResult rs = new NonBankcardPaymentResult();
		// 解析易宝支付返回的消费请求结果,关于返回结果数据详见《易宝支付非银行卡支付专业版接口文档 v3.0》
		for (int t = 0; t < responseStr.size(); t++) {
			String currentResult = (String) responseStr.get(t);
			log.debug("responseStr[" + t + "]:" + currentResult);
			if (currentResult == null || currentResult.equals("")) {
				continue;
			}
			rs.setReturnmsg(currentResult);
			
		}
		
		return (rs);
	}

	/**
	 * 校验交易结果通知
	 * 该方法是根据《易宝支付非银行卡支付专业版接口文档 v3.0》对易宝支付返回扣款数据进行校验
	 * 具体参数含义请仔细阅读《易宝支付非银行卡支付专业版接口文档 v3.0》
	 * 业务类型
	 * @param r0_Cmd
	 * 支付结果
	 * @param r1_Code
	 * 商户编号
	 * @param p1_MerId
	 * 商户订单号
	 * @param p2_Order
	 * 成功金额
	 * @param p3_Amt
	 * 支付方式
	 * @param p4_FrpId
	 * 卡序列号组
	 * @param p5_CardNo
	 * 确认金额组
	 * @param p6_confirmAmount
	 * 实际金额组
	 * @param p7_realAmount
	 * 卡状态组
	 * @param p8_cardStatus
	 * 扩展信息
	 * @param p9_MP
	 * 支付余额
	 * @param pb_BalanceAmt
	 * 余额卡号
	 * @param pc_BalanceAct
	 * 签名数据
	 * @param hmac
	 */
	public static NonBankcardPaymentResult verifyCallback(String partner,String ordernumber,
			String orderstatus,String paymoney,String sysnumber,String attach,String sign) {
		
		//校验MD5码是不是正确的
		String tempStr="partner="+partner+"&ordernumber="+ordernumber+
				"&orderstatus="+orderstatus+"&paymoney="+paymoney+keyValue;
		String newMD5 = MD5Util.string2MD5(tempStr);
		if (!sign.equals(newMD5)) {
			String errorMessage = "交易签名被篡改!";
			log.debug(errorMessage);
			throw new RuntimeException(errorMessage);
		}
		//将数据保存到数据库
		System.out.println("支付接口订单号:"+sysnumber);
		NonBankcardPaymentResult rs=new NonBankcardPaymentResult();
		rs.setCode("ok");
		return rs;
	}
	
}
