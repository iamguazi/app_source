package com.ffcs.icity.api.module.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 提供日期相关操作的助手类.
 * 
 * @author dylan.chen 2009-9-23
 * 
 */
public class DateHelper {

	/**
	 * 默认的日期格式字符串
	 * */
	public final static String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private final static Logger log = LoggerFactory.getLogger(DateHelper.class);
	
	private DateHelper() {
	}

	/**
	 * 获取当前的时间戳的字符串表示形式 注:如果不指定格式表达式,则使用默认的格式表达式("yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param parsePatterns
	 *            用于解析日期的格式表达式
	 * @return String
	 * */
	public static String getCurrentTimeStamp(String... parsePatterns) {
		return getTimeStamp(new Date(), parsePatterns);
	}

	/**
	 * 获取指定时间戳的字符串表示形式 注:如果不指定格式表达式,则使用默认的格式表达式("yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param date
	 *            指定的时间
	 * @param parsePatterns
	 *            用于解析日期的格式表达式
	 * @return String
	 * */
	public static String getTimeStamp(Date date, String... parsePatterns) {
		return DateFormatUtils.format(date, ArrayUtils.isEmpty(parsePatterns) ? DEFAULT_DATE_FORMAT_PATTERN : parsePatterns[0]);
	}
	
	/**
	 * 获取传递时间的下n天
	 * @param date
	 * @return
	 * <pre>
	 * 
	 *
	 * </pre>
	 */
	public static Date getLaterDay(Date date,int n) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, +n);	
		return c.getTime();
	}
	
	/**
	 * 获取下一周
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getLaterWeek(Date date,int n) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, +n*7);	
		return c.getTime();
	}
   /**
    * 获取下一月
    * @param date
    * @param n
    * @return
    */
	public static Date getLaterMonth(Date date,int n) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, +n);	
		return c.getTime();
	}
	public static void main(String[] args) {
		System.out.println(getFirstDayOfWeek(new Date()));
	}
	
	/**
	 * 获取某年某月 一共有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public  static int getDayCountofMonth(Date date ){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return value;
	}
	
	/**
	 * 获取当前日期是当年的第几周 从周一开始
	 * @param date
	 * @return
	 */
	public static int getWeekOfYearByMonday(Date date) { 
		 Calendar c = new GregorianCalendar(); 
//		 c.setFirstDayOfWeek(1);
		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		 c.setMinimalDaysInFirstWeek(7); 
		 c.setTime (date);
		 return c.get(Calendar.WEEK_OF_YEAR); 
	}
	
	//获取当前月第一天：
	public static Date getFirstDayOfMonth(Date date){
	        Calendar c = Calendar.getInstance();   
	        c.setTime(date);
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	       return c.getTime();
	       
	}
	
	//获取当前月最后一天：
	public static Date getLastDayOfMonth(Date date){
	        Calendar c = Calendar.getInstance();   
	        c.setTime(date);
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为1号,当前日期既为本月第一天
	       return c.getTime();
	       
	}
	
	//获取当前日期的周一
	public static Date getFirstDayOfWeek(Date date){
			Calendar cal =Calendar.getInstance();
			cal.setTime(date);
			  int weekday=cal.get(Calendar.DAY_OF_WEEK);
			  System.out.println(weekday);
			  if(weekday==1){
				  cal.setTime(getEarlyDay(date, 1));
			  }
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
	        return cal.getTime();
	}
	/**
	 * 获取当前日期是当年的第几周 从周日开始
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) { 
		 Calendar c = new GregorianCalendar(); 
//		 c.setFirstDayOfWeek(1);
		 c.setFirstDayOfWeek(Calendar.SUNDAY); 
		 c.setMinimalDaysInFirstWeek(7); 
		 c.setTime (date);
		 return c.get(Calendar.WEEK_OF_YEAR); 
	}
	/**
	 * 获取传递时间的前n天
	 * @param date
	 * @return
	 * <pre>
	 * 
	 *
	 * </pre>
	 */
	public static Date getEarlyDay(Date date,int n) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -n);	
		return c.getTime();
	}
	
	/**
	 * 返回俩个DATE时间的间隔。(date格式为：yyyy-MM-dd )
	 * @param earlyDate
	 * @param date
	 * @return  返回long类型的时间戳
	 */
	public static long getTimeInterval(String earlyDate,String date) {
		
		long res = 0l;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = df.parse(earlyDate);
			java.util.Date date2 = df.parse(date);
			res = date2.getTime() - date1.getTime();
		} catch (ParseException e) {
			log.error("in DateHelper.getTimeInterval has an error, e is " + e);
		}
		return res;
	}
	
	public static long getTimeIntervalByDay(String earlyDate,String date) {
		return getTimeInterval(earlyDate, date)/(24*60*60*1000L);
	}
	
	/**
	 * 相差几个月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	 public static long getTimeIntervalByMonth(String startDate, String endDate) {
	        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	        long monthday;
	        try {
	            Date startDate1 = f.parse(startDate);
	            //开始时间与今天相比较
	            Date endDate1 = f.parse(endDate);

	            Calendar starCal = Calendar.getInstance();
	            starCal.setTime(startDate1);

	            int sYear = starCal.get(Calendar.YEAR);
	            int sMonth = starCal.get(Calendar.MONTH);
	            int sDay = starCal.get(Calendar.DATE);

	            Calendar endCal = Calendar.getInstance();
	            endCal.setTime(endDate1);
	            int eYear = endCal.get(Calendar.YEAR);
	            int eMonth = endCal.get(Calendar.MONTH);
	            int eDay = endCal.get(Calendar.DATE);

	            monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

	             
	            return monthday;
	        } catch (ParseException e) {
	            monthday = 0;
	        }
	        return monthday;
	    } 	
	 
	/**
	 * 返回俩个DATE时间的间隔。
	 * @param earlyDate
	 * @param date
	 * @return  返回long类型的时间戳
	 */
	public static long getTimeInterval(Date earlyDate,Date date) {
		
		return date.getTime() - earlyDate.getTime();
	}
	
}
