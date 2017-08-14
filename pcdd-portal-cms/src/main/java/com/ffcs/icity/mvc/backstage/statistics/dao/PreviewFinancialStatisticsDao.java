package com.ffcs.icity.mvc.backstage.statistics.dao;

import java.util.List;
import java.util.Map;

import com.ffcs.icity.mvc.backstage.statistics.entity.PreviewFinancialStatistics;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mybatis.Page;

@MyBatisRepository
public interface PreviewFinancialStatisticsDao extends CRUDDao<PreviewFinancialStatistics>{
//	public PreviewFinancialStatistics query(Map<String,Object> params);
	public Map<String,Object> queryForMap(Map<String,Object> params);
	public List<PreviewFinancialStatistics> queryPreview(Map<String,Object> params,Page<PreviewFinancialStatistics> page);
}
