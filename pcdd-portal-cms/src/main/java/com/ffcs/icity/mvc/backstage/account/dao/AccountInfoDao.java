package com.ffcs.icity.mvc.backstage.account.dao;

import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

import com.ffcs.icity.mvc.backstage.account.entity.AccountInfo;

@MyBatisRepository
public interface AccountInfoDao extends CRUDDao<AccountInfo> {
	
}