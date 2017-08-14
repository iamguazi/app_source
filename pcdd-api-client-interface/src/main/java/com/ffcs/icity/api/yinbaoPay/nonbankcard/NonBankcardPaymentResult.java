package com.ffcs.icity.api.yinbaoPay.nonbankcard;

/**
 * 闈為摱琛屽崱鏀粯缁撴灉
 * @author lu.li
 *
 */
public class NonBankcardPaymentResult {
	private String cmd;			// 涓氬姟绫诲瀷
	private String code;			// 娑堣垂璇锋眰缁撴灉(璇ョ粨鏋滀唬琛ㄨ姹傛槸鍚︽垚鍔燂紝涓嶄唬琛ㄥ疄闄呮墸娆剧粨鏋�)
	private String order;		// 鍟嗘埛璁㈠崟鍙�
	private String returnmsg;	// 閿欒淇℃伅
	private String sign;			// 绛惧悕鏁版嵁
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
