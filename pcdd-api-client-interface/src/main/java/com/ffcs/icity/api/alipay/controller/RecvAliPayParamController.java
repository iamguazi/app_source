package com.ffcs.icity.api.alipay.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffcs.icity.api.alipay.config.AlipayConfig;
import com.ffcs.icity.api.alipay.util.AlipayNotify;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.pointChangeLog.dao.IPointChangeLogDao;
import com.ffcs.icity.api.module.pointChangeLog.entity.PointChangeLog;
import com.ffcs.icity.api.module.userInfo.dao.IUserInfoDao;
import com.ffcs.icity.api.module.userInfo.entity.UserInfo;
import com.ffcs.icity.api.module.userPayLog.dao.IUserPayLogDao;
import com.ffcs.icity.api.module.userPayLog.entity.UserPayLog;
 

public class RecvAliPayParamController extends NoValidController{
	 
	
	 @Autowired
	 private IUserPayLogDao userPayLogDao;
	 
	 @Autowired
	 private IUserInfoDao userInfoDao;
	 
	 @Autowired
	 private IPointChangeLogDao pointChangeLogDao;
	
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
	@Transactional
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
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
			params.put(name, valueStr);
		}

		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝
		System.out.println(trade_no);
		System.out.println(out_trade_no);
		System.out.println();
		
		//交易状态
		try {
			if(AlipayNotify.verify(params)){//验证成功
				if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){ 
					UserPayLog userPayLog = userPayLogDao.getUserPayLogByOrderNo(out_trade_no);
					if(userPayLog.getStatus()==0){
						//1付款 2充值 
						if(userPayLog.getOrderType()==2){
							UserInfo user = userInfoDao.getUserInfoById(userPayLog.getUserId());
							user.setPoint(user.getPoint()+Double.parseDouble(total_fee));
							if(user.getAllPoint()==null){
								user.setAllPoint(0.0);
								
							}
							user.setAllPoint(user.getAllPoint()+Double.parseDouble(total_fee));
//							user.setMoney(user.getMoney()+userPayLog.getTotalFee()*10);
							userInfoDao.updateUserInfo(user);
							
							//添加中奖账变记录
							PointChangeLog pointChangeLog=new PointChangeLog();
							pointChangeLog.setPoint(Double.parseDouble(total_fee));
							pointChangeLog.setPointDesc("支付宝充值");
							pointChangeLog.setUserId(userPayLog.getUserId());
							pointChangeLog.setPayType(1);
							pointChangeLogDao.insertPointChangeLog(pointChangeLog);
						}
						
						//修改支付记录 
						userPayLog.setResultCode(trade_status);
						userPayLog.setOutTradeNo(trade_no);
						userPayLog.setResultFee(Double.parseDouble(total_fee));
						userPayLog.setUpdateTime(new Date());
						userPayLog.setStatus(1);
						userPayLog.setPayType(3);
						userPayLogDao.updateUserPayLog(userPayLog);
					}
					
					
					
					
				}
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("fail");
		}
		
	}

}
