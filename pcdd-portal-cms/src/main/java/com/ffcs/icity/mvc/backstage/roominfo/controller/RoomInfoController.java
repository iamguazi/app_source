package com.ffcs.icity.mvc.backstage.roominfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.roominfo.dao.RoomInfoDao;
import com.ffcs.icity.mvc.backstage.roominfo.entity.RoomInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;


@RequestMapping("backstage/roomInfo")
@Controller
public class RoomInfoController extends AsynController<RoomInfo>{

	private RoomInfoDao roomInfoDao;

	@Override
	protected CRUDDao<RoomInfo> getDao() {
		return this.roomInfoDao;
	}

	@Autowired
	public void setRoomInfoDao(RoomInfoDao roomInfoDao) {
		this.roomInfoDao = roomInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/roomInfo";
	}
	
	@Override
	protected Page<RoomInfo> getPage(Map<String, Object> params,
			HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<RoomInfo> page = new Page<RoomInfo>(pageNo, pageSize);
		roomInfoDao.queryRoomIndex(params, page);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("ids");
		String status = request.getParameter("status");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		params.put("status", status);
		roomInfoDao.updateStatus(params);
		return true;
	}
	
	@RequestMapping("/getAllRoomInfo")
	@ResponseBody
	public Object getAllRoomInfo(HttpServletRequest request,HttpServletResponse response){
		try {
			String status = request.getParameter("status");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("status", status);
			List<Map<String,Object>> roomInfos = roomInfoDao.queryRoomInfoItem(params, null);
			if(CollectionUtils.isEmpty(roomInfos)){
				return Result.getSuccessForNoData();
			}else {
				return Result.getSuccessForData(roomInfos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.getServerError(e.getMessage());
		}
	}
}
