/**
 * 
 */
package com.ffcs.icity.api.module.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author: 黄嘉彬 
 * @date: 2014-12-15
 * @version v 1.0
 * 
 */
public class Utils {

	public final static String PATTERN_24TIME = "yyyy-MM-dd HH:mm:ss"; 
	public final static String PATTERN_DATE = "yyyy-MM-dd"; 
	
	//通过Calendar来获取是星期几
	public static String getWeekOfDate(Calendar c) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int temp = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (temp < 0)
            temp = 0;
        return weekDays[temp];
	}
	
	//获取当天时间的前一天
	public static String getEarlyDay(Date date) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);	
		return formatDate(c.getTime(),PATTERN_DATE);
	}
	
	//获取当天时间的前一天
	public static String getEarlyDay(Date date,String pattern) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);	
		return formatDate(c.getTime(),pattern);
	}
	
	/** 
	 * 得到指定月的天数 
	 * */  
	public static int getMonthLastDay(int year, int month)  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.YEAR, year);  
	    a.set(Calendar.MONTH, month - 1);  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	} 
	
	/**
	 * 把字符串改为时间格式
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr,String pattern) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(dateStr);
	}
	
	/**
	 * 将GMT先转化为DATE，之后在转化为STRING
	 * @param timeStr
	 * @return
	 */
	public static String getTimeFromGMT(String gmtTime) {
		
		try {
			DateFormat df = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss zzz", Locale.US); 
			Date date = df.parse(gmtTime);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return s.format(date);
		} catch (ParseException e) {
		}
		return "";
	}
	
	public static String getTimeFromCST(String cstTime) {
		
		try {
			DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
			Date date = df.parse(cstTime);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return s.format(date);
		} catch (ParseException e) {
		}
		return "";
	}
	
	
	/**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday(String pattern)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday()
    {
        return getToday("yyyy-MM-dd");
    }
    
    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getNow()
    {
    	return getToday("yyyy-MM-dd HH:mm:ss");
    }   
    
    /**
     * 比较两个日期大小
     * @param date1 日期字符串
     * @param pattern1 日期格式
     * @param date2 日期字符串
     * @param pattern2 日期格式
     * @return boolean 若是date1比date2小则返回true
     * @throws ParseException
     */
    public static boolean compareMinDate(String date1, String pattern1,
                                         String date2, String pattern2) throws ParseException
    {
        Date d1 = convertToCalendar(date1, pattern1).getTime();
        Date d2 = convertToCalendar(date2, pattern2).getTime();
        return d1.before(d2);
    }

    /**
     * 根据传入的日期字符串以及格式，产生一个Calendar对象
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return Calendar
     * @throws ParseException 当格式与日期字符串不匹配时抛出该异常
     */
    public static Calendar convertToCalendar(String date, String pattern) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = sdf.parse(date);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern 字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Calendar currentDate, String pattern)
    {
        if(currentDate == null || pattern == null)
        {
            throw new NullPointerException("The arguments are null !");
        }
        Date date = currentDate.getTime();
        return formatDate(date, pattern);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern 字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Date currentDate, String pattern)
    {
        if(currentDate == null || pattern == null)
        {
            throw new NullPointerException("The arguments are null !");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param currentDate 被格式化日期字符串 必须为yyyymmdd
     * @param pattern 字符串的格式
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     * @throws java.text.ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static String formatDate(String currentDate, String pattern) throws java.text.ParseException
    {
        if(currentDate == null || pattern == null)
        {
            throw new NullPointerException("The arguments are null !");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(currentDate);
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param strDate 被格式化日期字符串 必须为yyyymmdd
     * @param formator 格式字符串
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     * @throws java.text.ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static Calendar strToDate(String strDate, String formator)
    {
        if(strDate == null || formator == null)
        {
            throw new NullPointerException("The arguments are null !");
        }

        Calendar date = Calendar.getInstance();
        date.setTime(java.sql.Date.valueOf(strDate));
        return date;
    }

    /**
     * 将指定格式的时间String转为Date类型
     * @param dateStr String 待转换的时间String
     * @param pattern String 转换的类型
     * @throws ParseException
     * @return Date
     */
    public static Date convertStringToDate(String dateStr,String patternner) throws ParseException
    {
        String pattern =patternner;

        if(dateStr == null)
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    public static String convertDateToString(Date date) throws ParseException
    {
        if(date == null)
        {
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    
//    public static void main(String[] args) throws ParseException{
//    	
//    	
//    	System.out.println(getDate("2013-01-18 16:16:43.0","yyyy-MM-dd"));
//    	
//    }
    
    /**
     * 
     * @return
     * @Description: 获取当前时间（单位毫秒） 
     * @author: Linguisen
     * @version: V1.0 
     * @Date: 2014-2-10 下午03:57:45
     */
    public static long getTimestamp(){
    	return System.currentTimeMillis();
    }
    
    /**
     * 
     * @param list
     * @return
     * @Description: 判断list对象是否为空元素
     * @author: Linguisen
     * @version: V1.0 
     * @param <T>
     * @Date: 2014-2-20 下午4:00:59
     */
    public static <T> boolean checkListIsNotEmpty(List<T> list){
    	if(list != null && list.size() > 0 ){
    		return true;
    	}
		return false;
    	
    }
    
    public static String trans(String str){
        
        List<Integer> record =new ArrayList<Integer>();
        for(int i=0;i<str.length();i++)
        {
            char tmp =str.charAt(i);
             
            if((tmp<='Z')&&(tmp>='A'))
            {
                record.add(i);//记录每个大写字母的位置
            }
             
        }
        record.remove(0);//第一个不需加下划线
         
        str= str.toLowerCase();
        char[] charofstr = str.toCharArray();
        String[] t =new String[record.size()];
        for(int i=0;i<record.size();i++)
        {
            t[i]="_"+charofstr[(int)record.get(i)];//加“_”
        }
        String result ="";
        int flag=0;
        for(int i=0;i<str.length();i++)
        {
            if((flag<record.size())&&(i==(int)record.get(flag))){
                result+=t[flag];
                flag++;
            }
            else
                result+=charofstr[i];
        }
         
        return result;
    }
    public static void main(String[] args) {
		System.out.println(Utils.trans("UserName"));
	}
}
