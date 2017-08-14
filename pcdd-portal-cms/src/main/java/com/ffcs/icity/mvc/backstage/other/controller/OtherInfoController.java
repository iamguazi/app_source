package com.ffcs.icity.mvc.backstage.other.controller;

import static com.ffcs.icity.mvc.utils.RequestUtils.resolveParams;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.other.dao.OtherInfoDao;
import com.ffcs.icity.mvc.backstage.other.entity.OtherInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;


@RequestMapping("backstage/otherInfo")
@Controller
public class OtherInfoController extends AsynController<OtherInfo>{

	private OtherInfoDao otherInfoDao;

	@Override
	protected CRUDDao<OtherInfo> getDao() {
		return this.otherInfoDao;
	}

	@Autowired
	public void setOtherInfoDao(OtherInfoDao otherInfoDao) {
		this.otherInfoDao = otherInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/other";
	}
	
	
	@RequestMapping("saveOrUpdate")
    public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String, Object> params = resolveParams(request);
    		Iterator<Entry<String,Object>> iteator = params.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		OtherInfo other = null;
    		while(iteator.hasNext()){
    			entry = iteator.next();
    			other = otherInfoDao.getByKey(entry.getKey());
    			if (null != other) {
    				other.setOtherValue((String) entry.getValue());
    				otherInfoDao.update(other);
    			}
    		}
    		
    	Result.returnResult(response, Result.getSuccessForNoData());
    }
}
