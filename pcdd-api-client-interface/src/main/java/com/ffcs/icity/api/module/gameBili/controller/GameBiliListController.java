package com.ffcs.icity.api.module.gameBili.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameBili.entity.*;
import com.ffcs.icity.api.module.gameBili.dao.*;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class GameBiliListController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameBiliDao gameBiliDao;
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "game_type");
		assertNotEmpty(requestArgument, "area_id");
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameType=requestArgument.get("game_type").toString();
		String areaId=requestArgument.get("area_id").toString();

		GameBili gameBili=new GameBili();
		gameBili.setGameType(Integer.parseInt(gameType));
		gameBili.setAreaId(Integer.parseInt(areaId));
		Map<String, Object>result=new HashMap<String, Object>();
		//比例类型 1大小单双 2猜数字 3特殊玩法 
		gameBili.setBiliType(1);
		result.put("da_xiao", gameBiliDao.findByCondition(gameBili));
		
		gameBili.setBiliType(2);
		result.put("shu_zi", gameBiliDao.findByCondition(gameBili));
		
		gameBili.setBiliType(3);
		List<GameBili> list = gameBiliDao.findByCondition(gameBili);
		for(GameBili bili:list){
			if(("-1").equals(bili.getResult())){
				bili.setResult("三个数字一致即为中奖");
			}
		}
		result.put("te_shu", list);
		return result;
	}
}

