package com.ffcs.icity.api.demo.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * 数据库建表语句：
 * 
 * CREATE TABLE `demo` (
 *    `id` int(11) NOT NULL AUTO_INCREMENT,
 *    `name` varchar(45) DEFAULT NULL,
 *    `password` varchar(45) DEFAULT NULL,
 *    PRIMARY KEY (`id`)
 *  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Feb 24, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class Demo implements Serializable{

	private static final long serialVersionUID = 2640777389831984683L;

	private Long id;
	
	private String name;
	
	private String password;
	
	public Demo(){}

	public Demo(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
