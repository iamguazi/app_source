package com.ffcs.icity.mvc.backstage.banner.dao;

import java.util.Map;

import com.ffcs.icity.mvc.backstage.banner.entity.Banner;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BannerDao extends CRUDDao<Banner> {
	public void updateStatus(Map<String,Object> params);
}