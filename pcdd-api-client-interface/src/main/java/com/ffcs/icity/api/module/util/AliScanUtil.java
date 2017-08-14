package com.ffcs.icity.api.module.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

public class AliScanUtil {
	
	
	
	public static void main(String[] args) {
		String key="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMRKm6bQrvgp5j2u3+tAHSCsfF0ZtSvZ4+5r1V1rry7hLGVdOXBryTwXyIeuUiKXKy9oSqVSWq6wTqdu4tfzk74yEK1YN4KkEnwCY4R5AGBy4In5B5K4lFjE5OAacsfjk6VZQokwihaFGwZgPwrSyhxIWIPhjjjlvrofkpPty/jzAgMBAAECgYEAxD/fnQmZVa32bwdPApIoTQmGkUJpmz0OoMoZGXzXOiSQ0YtCKT7qg/U3XUgRAFppJ826i81psqu9B7NagxQZSdbE2e/0bl8zM220XIefOWdLD28jxH8mgwx7SUehI96kiRplxiP9Gex1gLn+alc/NONJPk7MPEiu2sdqx7+4JvECQQDweMqjNDHtBBliQ4GcDb6/ZdjjPCjTXGMCW2nQv3tQy9AH3LDJZ9DEKWzilG9HWUJw6ejvv4FOfhxR8XQwN6NbAkEA0Pd52uHesKJ8hIjR83sFmgjvlMSo6oA4Ous9feaHrP/CaYExQOIWlPzw4ZuKcekBzvgLVHkvKmfzVrOGgkxsSQJAQ5V/Rh0eRwHW7IIShDxstrg2G69FvhmCDiCI/c1DKDMP9ZW17fUKfIcz/f4/xCq2/KsQ94i4G8pfxTv6Lq8EbwJAGow2OO6VIg+ijhvrwGkSrx0PgvDxHMmpWyLjrwkIl5vukQTOwxV2FPuFhsQB9LLLfgXZOqQir9qA+bTZE9itsQJAFRr89uRZEQqDMQkv+yzEH82Rv88EPZPLzNvqxCISyvMsmLkBeoiYddBfT6jvj4bhpwkqyISZRRoUgn8pGBOD6g==";
		String  publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2017031106163633",key,"json","utf-8",publicKey,"RSA");
		AlipayTradePrecreateRequest  request = new AlipayTradePrecreateRequest ();
//		request.setNotifyUrl(notifyUrl);
		request.setBizContent("{" +
		"    \"out_trade_no\":\"20170314010101001\"," +
		"    \"total_amount\":88.88," +
		"    \"subject\":\"Iphone6 16G\"," +
		"    \"body\":\"Iphone6 16G\"," +
		"  }");
		
		 
		AlipayTradePrecreateResponse response;
		try {
			response = alipayClient.execute(request);
			if(response.isSuccess()){
				System.out.println("调用成功");
				} else {
				System.out.println("调用失败");
				}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
