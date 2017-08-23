package com.ffcs.icity.api.aiyiPay.controller;

import com.ffcs.icity.api.aiyiPay.config.Config;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.accountInfo.dao.IAccountInfoDao;
import com.ffcs.icity.api.module.accountInfo.entity.AccountInfo;
import com.ffcs.icity.api.module.payParams.dao.IPayParamsDao;
import com.ffcs.icity.api.module.payParams.entity.PayParams;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

public class GetPayUrlController extends NoValidController {
    @Autowired
    private IPayParamsDao payParamsDao;
    @Autowired
    private IAccountInfoDao accountInfoDao;

    @Override
    public void verifyBusiArgument(Map<String, Object> requestArgument)
            throws InvalidRequestArgumentException {
        // TODO Auto-generated method stub
        assertNotEmpty(requestArgument, "order_no");
        assertNotEmpty(requestArgument, "fee");
        assertNotEmpty(requestArgument, "pay_type");

    }

    @Override
    public Object handleRequest(Map<String, Object> requestArgument)
            throws ApiException {
        // TODO Auto-generated method stub
        String fee = requestArgument.get("fee").toString();
        String orderNo = requestArgument.get("order_no").toString();
        String payType = requestArgument.get("pay_type").toString();
        if ("2".equalsIgnoreCase(payType)) {
            AccountInfo accountInfoById = accountInfoDao.getAccountInfoById(13);
            return accountInfoById.getPhoto();
        }

        PayParams params = payParamsDao.getPayParamsByType("爱益支付");
        String result = Config.getErPayUrl(orderNo, fee, payType, params.getMchId(), params.getMchKey());

//		HttpsGetData vo = new HttpsGetData(payurl, null);
        JSONObject json = null;
        try {
//			String result = vo.Do();
            json = JSONObject.fromObject(result);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (json.getBoolean("success")) {
            return json.getString("token_id");
        } else {
            if ("1".equals(payType)) {
                throw new ApiException("暂不支持支付宝 ，请联系客服");
            } else if ("2".equals(payType)) {
                return orderNo;
//                throw new ApiException("暂不支持微信，请联系客服");
            }
        }
        return null;


    }

    @Override
    public String[] getSignItems(Map<String, Object> requestArgument) {
        // TODO Auto-generated method stub

        return new String[]{requestArgument.get("order_no").toString()};
    }

}
