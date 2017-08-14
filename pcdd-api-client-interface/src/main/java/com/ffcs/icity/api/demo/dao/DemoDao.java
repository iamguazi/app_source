package com.ffcs.icity.api.demo.dao;

import java.util.List;
import java.util.Map;

import com.ffcs.icity.api.demo.entity.Demo;
import com.ffcs.icity.common.mybatis.MyBatisRepository;


/**
 *
 * 此接口中的方法名即为DemoMapper.xml文件中定义的SQL语句的id.
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Feb 24, 2013
 * @version 1.00.00
 * @history:
 * 
 */
@MyBatisRepository
public interface DemoDao {

	public List<Demo> search(Map<String, Object> parameters);
	
	public Demo get(Long id);
 
	public void save(Demo demo);
	
	public void update(Demo demo);

	public void delete(Long id);
	
}
