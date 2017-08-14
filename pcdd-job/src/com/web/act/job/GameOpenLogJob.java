package com.web.act.job;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.web.act.utils.Config;
import com.web.act.utils.UrlUtil;
import com.web.act.vo.*;
import com.web.act.dao.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("GameOpenLogJob")
public class GameOpenLogJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameOpenLogJob.class);
	
	public void run(){
		LOGGER.warn("=====================取回水结果开始===========================");
		 UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/huishui/add?game_type=1");
		 
		 LOGGER.warn("=====================取分销结果开始===========================");
		 UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/fenxiao/add?game_type=1");
		
		 
	}
}
