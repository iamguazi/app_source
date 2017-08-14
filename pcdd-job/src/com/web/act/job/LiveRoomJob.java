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


@Service("LiveRoomJob")
public class LiveRoomJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LiveRoomJob.class);
	
	public void run(){
		LOGGER.warn("=====================定时取开奖结果开始===========================");
		String a = UrlUtil.sendGet(Config.yuming+"pcdd-api-client-interface/result/open?game_type=1");
		System.out.println(a);
	}
}
