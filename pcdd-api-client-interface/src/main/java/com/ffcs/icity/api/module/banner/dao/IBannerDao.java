package com.ffcs.icity.api.module.banner.dao;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.ffcs.icity.api.module.banner.entity.*;
import com.ffcs.icity.api.module.banner.dao.*;
import java.util.List;

import com.ffcs.icity.common.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IBannerDao {
	
	public List<Banner> findAll();
	
	public List<Banner> findByCondition(Banner banner);
	
	public Banner getBannerById(int id);
	
	public void insertBanner(Banner banner);
	
	public void updateBanner(Banner banner);
	
	public void deleteBanner(long id);
}
