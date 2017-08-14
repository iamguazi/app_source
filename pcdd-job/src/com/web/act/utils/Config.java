package com.web.act.utils;

import java.util.Date;

public class Config {

	public static Date beiJingNextTime;//北京开奖时间

	public static long beiJingNextSeconds;//北京延迟时间

	public static long beiJingNum;//北京期数

	public static Date jiaNaDaNextTime;//加拿大开奖时间

	public static long jiaNaDaNextSeconds;//加拿大延迟时间

	public static long jiaNaDaNum;//北京期数

	public static String yuming="http://127.0.0.1:50001/";//服务器域名


	public static int  beijingStartTime=45;//北京快8 提前封盘时间 （单位 秒）
	public static int  beijingEndTime=30;//北京快8  允许开盘的最大延迟时间 （单位 秒）
	public static int  beijingTime=5*60;//北京快8 间隔开奖时间
	public static int  JNDStartTime=35;//加拿大快8 提前封盘时间 （单位 秒）
	public static int  JNDEndTime=30;//加拿大快8 提前封盘时间 （单位 秒）
	public static int  JNDTime=210;//加拿大快8间隔开奖时间



}
