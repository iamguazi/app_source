package com.ffcs.icity.mvc.backstage.statistics.dao;

import java.util.List;
import java.util.Map;

import com.ffcs.icity.mvc.backstage.statistics.entity.FinancialStatistics;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface FinancialStatisticsDao {
	public List<FinancialStatistics> query(Map<String,Object> params);
}
