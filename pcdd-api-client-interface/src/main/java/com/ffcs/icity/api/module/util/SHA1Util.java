package com.ffcs.icity.api.module.util;

import java.security.MessageDigest;

public class SHA1Util {
	public static String getSHA1(String str){
		try {
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		}
	public static String getSignature(String jsapiTicket,String timestampe,String url,String noncestr){
		String keyvaluestring = "jsapi_ticket="+jsapiTicket+
								"&noncestr="+noncestr+
								"&timestamp="+timestampe+
								"&url="+url;
		String signature=getSHA1(keyvaluestring);
		return signature;
	}
	
	public static void main(String[] args) {
		String keyvaluestring = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VL4rwVjsnZRCMJzFBWNUXdM5rRvnHhjJwP7niSLi-BW-gCJM8tyKCfuR0NFfhOvjnw&noncestr=Wm3WZYTPz0wzccnW&timestamp=1430961884&url=http://massage.v-ma.net:58180/massage-wap/views/module/recharge/recharge.html";
        
		System.out.println(getSHA1(keyvaluestring));
	}
}
