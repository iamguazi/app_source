package com.web.act.job;

import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

import com.web.act.vo.*;
import com.web.act.dao.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("OtherParamsJob")
public class OtherParamsJob {
	@Autowired(required = true)
	private OtherParamsDao otherParamsDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OtherParamsJob.class);
	
	public void run(){
		
	}
}
