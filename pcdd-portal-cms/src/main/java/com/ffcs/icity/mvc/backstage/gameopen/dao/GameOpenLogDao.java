package com.ffcs.icity.mvc.backstage.gameopen.dao;

import java.util.Map;

import com.ffcs.icity.mvc.backstage.gameopen.entity.GameOpenLog;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;

@MyBatisRepository
public interface GameOpenLogDao extends CRUDDao<GameOpenLog> {
    int checkGameNum(Map<String,Object> params);
}