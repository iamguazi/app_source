package com.ffcs.icity.api.module.gameOpenLog.controller;


import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.gameOpenLog.entity.*;
import com.ffcs.icity.api.module.gameOpenLog.Service.OpenService;
import com.ffcs.icity.api.module.gameOpenLog.dao.*;
import com.ffcs.icity.api.module.roomUserInfo.service.MsgService;
import com.ffcs.icity.api.module.util.UrlUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */

public class AddGameOpenLogController  extends NoValidController {
	
	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#verifyBusiArgument(java.util.Map)
	 */
	
	@Autowired
	private IGameOpenLogDao gameOpenLogDao;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private OpenService openService;
	
	@Value("${bjklUrl}")
	public  String  bjklUrl;
	
	@Value("${jndUrl}")
	public String  jndUrl;
	
//	public String  bjklUrl="http://c.apiplus.net/newly.do?token=8d012ff8e6af1f10&code=bjkl8&format=json";//北京快8开奖url 
//	
//	public String  jndUrl="http://c.apiplus.net/newly.do?token=8d012ff8e6af1f10&code=cakeno&format=json";//加拿大快8开奖url 
//	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "game_type");
	 
	}

	/* (non-Javadoc)
	 * @see com.ffcs.icity.api.core.JSONController#handleRequest(java.util.Map)
	 */
	@Override 
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		String gameType=requestArgument.get("game_type").toString();
		try {
			bjkl8();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		jndl8();
		
		return "ok";
	}
	public  void  bjkl8() {
		String url=bjklUrl;
		String jsonResult=UrlUtil.sendGet(url);
		JSONObject json=JSONObject.fromObject(jsonResult);
		JSONArray dataList = json.getJSONArray("data");
		for(int i=0;i<1;i++){
			JSONObject vo=dataList.getJSONObject(i);
			String expect=vo.getString("expect");
			String opentime=vo.getString("opentime");
			String opencode=vo.getString("opencode");
			List<Integer> result=new ArrayList<Integer>();
			for(String code:opencode.split(",")){
				if(code.contains("+")){
					result.add(Integer.parseInt(code.substring(0,code.indexOf("+"))));
				}else{
					result.add(Integer.parseInt(code));
				}
			}
			//从小到大排序
			Collections.sort(result);
			//实例化
			GameResultVo resultVo=new GameResultVo(result);
//			int sum1=resultVo.getResult2()+resultVo.getResult5()+resultVo.getResult8()+resultVo.getResult11()+resultVo.getResult14()+resultVo.getResult17();
//			sum1=sum1%10;
//			int sum2=resultVo.getResult3()+resultVo.getResult6()+resultVo.getResult9()+resultVo.getResult12()+resultVo.getResult15()+resultVo.getResult18();
//			sum2=sum2%10;
//			int sum3=resultVo.getResult4()+resultVo.getResult7()+resultVo.getResult10()+resultVo.getResult13()+resultVo.getResult16()+resultVo.getResult19();
			
			int sum1=resultVo.getResult1()+resultVo.getResult2()+resultVo.getResult3()+resultVo.getResult4()+resultVo.getResult5()+resultVo.getResult6();
			sum1=sum1%10;
			int sum2=resultVo.getResult7()+resultVo.getResult8()+resultVo.getResult9()+resultVo.getResult10()+resultVo.getResult11()+resultVo.getResult12();
			sum2=sum2%10;
			int sum3=resultVo.getResult13()+resultVo.getResult14()+resultVo.getResult15()+resultVo.getResult16()+resultVo.getResult17()+resultVo.getResult18();
			sum3=sum3%10;
			
			GameOpenLog gameOpenLog=new GameOpenLog();
			gameOpenLog.setUserId(0);
			gameOpenLog.setGameType(1);
			gameOpenLog.setGameNum(expect);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				gameOpenLog.setOpenTime(sdf.parse(opentime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameOpenLog.setGameResult(sum1+sum2+sum3);
			gameOpenLog.setGameResultDesc(sum1+"+"+sum2+"+"+sum3+"="+(sum1+sum2+sum3));
			String resultType="";
			if(gameOpenLog.getGameResult()>13){
				 resultType="大";
			}else{
				resultType="小";
			}
			if(gameOpenLog.getGameResult()%2==0){
				resultType=resultType+",双";
			}else{
				resultType=resultType+",单";
			}
			gameOpenLog.setResultType(resultType);
			//三个数字相同 为豹子
			if(sum1==sum2&&sum2==sum3){
				
				gameOpenLog.setIsBaozi(1);
			}else{
				gameOpenLog.setIsBaozi(0);
			}
			
			try {
				Thread.sleep(getRandomNum(100, 300)*10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(gameOpenLogDao.getGameOpenLogByNum(gameOpenLog)==null){
				
				gameOpenLog.setColor(getColor(gameOpenLog.getGameResult())+"");
				gameOpenLogDao.insertGameOpenLog(gameOpenLog);
				try {
					openService.openJiang(gameOpenLog.getGameNum(), "1");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				msgService.sendOpenMsg(gameOpenLog);
			}
		}
		System.out.println(jsonResult);
	}
	
	 public static int getRandomNum(int min,int max) {
	        
	        Random random = new Random();
	        				 
	        int s = random.nextInt(max)%(max-min+1) + min;
	        return s;
	  }
	
	public  void  jndl8() {
		String url=jndUrl;
		String jsonResult=UrlUtil.sendGet(url);
		JSONObject json=JSONObject.fromObject(jsonResult);
		JSONArray dataList = json.getJSONArray("data");
		for(int i=0;i<1;i++){
			JSONObject vo=dataList.getJSONObject(i);
			String expect=vo.getString("expect");
			String opentime=vo.getString("opentime");
			String opencode=vo.getString("opencode");
			List<Integer> result=new ArrayList<Integer>();
			for(String code:opencode.split(",")){
				if(code.contains("+")){
					result.add(Integer.parseInt(code.substring(0,code.indexOf("+"))));
				}else{
					result.add(Integer.parseInt(code));
				}
			}
			//从小到大排序
			Collections.sort(result);
			//实例化
			GameResultVo resultVo=new GameResultVo(result);
			int sum1=resultVo.getResult2()+resultVo.getResult5()+resultVo.getResult8()+resultVo.getResult11()+resultVo.getResult14()+resultVo.getResult17();
			sum1=sum1%10;
			int sum2=resultVo.getResult3()+resultVo.getResult6()+resultVo.getResult9()+resultVo.getResult12()+resultVo.getResult15()+resultVo.getResult18();
			sum2=sum2%10;
			int sum3=resultVo.getResult4()+resultVo.getResult7()+resultVo.getResult10()+resultVo.getResult13()+resultVo.getResult16()+resultVo.getResult19();
			sum3=sum3%10;
			
			GameOpenLog gameOpenLog=new GameOpenLog();
			gameOpenLog.setUserId(0);
			gameOpenLog.setGameType(2);
			gameOpenLog.setGameNum(expect);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				gameOpenLog.setOpenTime(sdf.parse(opentime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameOpenLog.setGameResult(sum1+sum2+sum3);
			gameOpenLog.setGameResultDesc(sum1+"+"+sum2+"+"+sum3+"="+(sum1+sum2+sum3));
			String resultType="";
			if(gameOpenLog.getGameResult()>13){
				 resultType="大";
			}else{
				resultType="小";
			}
			if(gameOpenLog.getGameResult()%2==0){
				resultType=resultType+",双";
			}else{
				resultType=resultType+",单";
			}
			gameOpenLog.setResultType(resultType);
			//三个数字相同 为豹子
			if(sum1==sum2&&sum2==sum3){
				
				gameOpenLog.setIsBaozi(1);
			}else{
				gameOpenLog.setIsBaozi(0);
			}
			try {
				Thread.sleep(getRandomNum(100, 300)*10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			System.out.println(gameOpenLog.getGameResultDesc());
			if(gameOpenLogDao.getGameOpenLogByNum(gameOpenLog)==null){
				
				gameOpenLog.setColor(getColor(gameOpenLog.getGameResult())+"");
				gameOpenLogDao.insertGameOpenLog(gameOpenLog);
				try {
					openService.openJiang(gameOpenLog.getGameNum(), "2");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				msgService.sendOpenMsg(gameOpenLog);
			}
		}
		System.out.println(jsonResult);
	}
	public static void main(String[] args) {
		new AddGameOpenLogController().jndl8();
	}
	
	/**
	 * 1红 2绿 3蓝 4无
	 * @param result
	 * @return
	 */
	public static int getColor(int result){
		String lv="1,4,7,10,16,19,22,25";
		String lan="2,5,8,11,17,20,23,26";
		String hong="3,6,9,12,15,18,21,24";
		for(String num:hong.split(",")){
			if(Integer.parseInt(num)==result){
				return 1;
			}
		}
		for(String num:lv.split(",")){
			if(Integer.parseInt(num)==result){
				return 2;
			}
		}
		
		for(String num:lan.split(",")){
			if(Integer.parseInt(num)==result){
				return 3;
			}
		}

		return 4;
		
	}
	
	 @Override
	    public String[] getSignItems(Map<String, Object> requestArgument) {
	    	// TODO Auto-generated method stub
	    	return new String[] {"no_valid"};
	    }
}

