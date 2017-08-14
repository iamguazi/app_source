package com.web.act.mapper;

import com.web.act.dao.*;
import com.web.act.vo.*;
import java.util.List;


public interface OtherParamsMapper {
	
	public List<OtherParams> findAll();
	
	public List<OtherParams> findByCondition(OtherParams otherParams);
	
	public OtherParams getOtherParamsById(int id);
	
	public void insertOtherParams(OtherParams otherParams);
	
	public void updateOtherParams(OtherParams otherParams);
	
	public void deleteOtherParams(long id);
}
