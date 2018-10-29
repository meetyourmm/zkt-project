package com.zkt.common.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/** 当前毫秒值 */
	public static long NOW_DATE_MIS = getNowTime();

	/** 根据方法获取 **/
	public static long getNowTime() {
		return new Date().getTime();
	}

	public static final String MM_DD = "MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String MM_DD_HH_MM = "MM-dd HH:mm";
	public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String MM_DD_EN = "MM/dd";
	public static final String YYYY_MM_EN = "yyyy/MM";
	public static final String YYYY_MM_DD_EN = "yyyy/MM/dd";
	public static final String MM_DD_HH_MM_EN = "MM/dd HH:mm";
	public static final String MM_DD_HH_MM_SS_EN = "MM/dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_EN = "yyyy/MM/dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS_EN = "yyyy/MM/dd HH:mm:ss";

	public static final String MM_DD_CN = "MM月dd日";
	public static final String YYYY_MM_CN = "yyyy年MM月";
	public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
	public static final String MM_DD_HH_MM_CN = "MM月dd日 HH:mm";
	public static final String MM_DD_HH_MM_SS_CN = "MM月dd日 HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_CN = "yyyy年MM月dd日 HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";

	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	
	/**
	 * 获取当前时间的年月日时分秒+时间戳后4位 如:20140620123456 18位
	 * 
	 * @return
	 */
	public static String ymdhms4() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		String s = sdf.format(date);
		double a = Math.random() * 9000 + 1000;
		int result = (int) a;
		return s + result;
	}
	
	/**
	 * 获取当前时间的年月日时分秒 20171231123159 14位
	 * 
	 * @return
	 */
	public static String ymdhms() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		String s = sdf.format(date);
		return s;
	}
	
	/**
	 * 把一个字符串时间转换为毫秒值 如 20151215153602 转换为 1406086513619
	 * 
	 * @param strDate
	 * @return
	 */
	public static long str2DateTime(String strDate) {
		SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
		Date date = new Date();
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.getStackTrace();
		}
		return date.getTime();
	}

	/**
	 * 把一个日期转换为指定的格式
	 * 
	 * @param strDate
	 * @param formater
	 *            格式参数（空或者NULL 默认yyyy-MM-dd）
	 * @return
	 */
	public static Date str2Date(String strDate, String formater) {
		if (strDate == null) {
			return null;
		}
		if (formater == null) {
			formater = YYYY_MM_DD;
		}
		SimpleDateFormat df = new SimpleDateFormat(formater);
		Date date = new Date();
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.getStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar calendarDate1 = Calendar.getInstance();
			calendarDate1.setTime(date1);
			Calendar calendarDate2 = Calendar.getInstance();
			calendarDate2.setTime(date2);

			// 日期判断
			if (calendarDate2.equals(calendarDate1)) {
				return 0;
			}

			if (calendarDate1.after(calendarDate2)) {
				Calendar temp = calendarDate1;
				calendarDate1 = calendarDate2;
				calendarDate2 = temp;
			}

			if (calendarDate2.get(Calendar.DAY_OF_MONTH) < calendarDate1.get(Calendar.DAY_OF_MONTH)) {
				flag = 1;
			}

			if (calendarDate2.get(Calendar.YEAR) > calendarDate1.get(Calendar.YEAR)) {
				iMonth = ((calendarDate2.get(Calendar.YEAR) - calendarDate1.get(Calendar.YEAR)) * 12
						+ calendarDate2.get(Calendar.MONTH) - flag) - calendarDate1.get(Calendar.MONTH);
			} else {
				iMonth = calendarDate2.get(Calendar.MONTH) - calendarDate1.get(Calendar.MONTH) - flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return iMonth;
	}

	/**
	 * 获取当前星期几（如：星期二）
	 */
	public static String cnDayOfWeek() {
		Calendar c = GregorianCalendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		String[] s = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return s[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取当前星期几（如：tuesday）
	 */
	public static String enDayOfWeek(Date date) {

		Calendar c = GregorianCalendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		String[] s = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
		return s[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取当前周几（如：3）
	 */
	public static String getWeekInNum(Date date) {

		Calendar c = GregorianCalendar.getInstance();
		c.setTime(date);
		int weekDayNum = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekDayNum == 0) {
			weekDayNum = 7;
		}
		return String.valueOf(weekDayNum);
	}

	/**
	 * 取给定日期的前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
		return calendar.getTime();
	}
	
	/**
	 * 取给定日期的后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAfterDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		return calendar.getTime();
	}

	/**
	 * 通过月份计算季度
	 * 
	 * @param month
	 *            月份
	 * 
	 * @return
	 */
	public static int getQuarter(int month) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("月份无效。");
		}
		return (month - 1) / 3 + 1;
	}
	
	/**
	 * 计算当前日期
	 * @param date_str
	 * @return
	 * @throws ParseException
	 */
	public static String[] cacleDate(String date_str) throws ParseException {
    	String[] ret = new String[3];
    	
    	int[] d = new int[] { 7, 1, 2, 3, 4, 5, 6 };
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
		Date date = sdf.parse(date_str);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String str = sdf.format(calendar.getTime());
		int dfw = calendar.get(Calendar.DAY_OF_WEEK);
		ret = new String[] { str, String.valueOf(d[dfw - 1]), String.valueOf(dfw) };
		
    	return ret;
    }
    
	/**
	 * 计算推算日期
	 * @param date_str
	 * @param dfw_mark
	 * @param index
	 * @return
	 * @throws ParseException
	 */
	public static String[] cacle1Date(String date_str, int dfw_mark, int index) throws ParseException {
    	String[] ret = new String[3];
    	
    	int[] d = new int[] { 7, 1, 2, 3, 4, 5, 6 };
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
		Date date = sdf.parse(date_str);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, index - (d[dfw_mark - 1] - 1));
		String str = sdf.format(calendar.getTime());
		int dfw = calendar.get(Calendar.DAY_OF_WEEK);
		ret = new String[] { str, String.valueOf(d[dfw - 1]), String.valueOf(dfw) };
		
    	return ret;
    }
	
	
	 /**
	  * 返回毫秒
	  * @param date
	  * @return
	  */
	public static long getMillis(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	/**
	 * 日期+天数  推算日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDate(Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	
	
	/**
	 * 日期格式化
	 * @param date
	 * @param formater
	 * @return
	 */
	public static String formatDate(Date date,String formater) {  
        SimpleDateFormat df = null;  
        String returnValue = "";  
        if (date != null) {  
            df = new SimpleDateFormat(formater);  
            returnValue = df.format(date);  
        }  
        return (returnValue);  
    }  

	/**
	 * 今天00:00:00
	 * @return
	 */
	public static String todayStart() {
		String date = formatDate(new Date(), YYYY_MM_DD);
		return date + " 00:00:00";
	}
	/**
	 * 今天23:59:59
	 * @return
	 */
	public static String todayEnd() {
		String date = formatDate(new Date(), YYYY_MM_DD);
		return date + " 23:59:59";
	}
	
	/**
	 * 查询日期是当年第几周
	 * @param date
	 * @return
	 */
	public static String getYearWeek(Date date) {
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		// 计算指定年的第一天是否为周几
		int year = c.get(Calendar.YEAR);
		c.set(year, 0, 1);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		c.setTime(date);
		// 计算该年最后一个周日
		Calendar instance = Calendar.getInstance();
		instance.set(year, 11, 31, 0, 0, 0);
		instance.add(Calendar.MONTH, 1);// 月份+1
		instance.set(Calendar.DAY_OF_MONTH, 1);	
		// 本月最后一天
		instance.add(Calendar.DAY_OF_MONTH, -1);
		// 根据月末最后一天是星期几，向前偏移至最近的周日
		instance.add(Calendar.DAY_OF_MONTH, 1 - instance.get(Calendar.DAY_OF_WEEK));
		if (instance.getTime().getTime() <= date.getTime()) {
			return year + "年" + "最后一周";
		} else {
			if (dayForWeek == 7) {// 当前年份的第一天为周日
				int week = c.get(Calendar.WEEK_OF_YEAR);
				return year + "年" + "第" + week + "周";
			} else {// 当前年份的第一天不为周日
				int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
				// 在此周日之前为上一年的最后一周
				if (week == 0) {
					return year - 1 + "年" + "最后一周";
				} else {
					return year + "年" + "第" + week + "周";
				}
	
			}
		}
	}
	
	/**
	 * 查询日期是当年第几月
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date) {
		Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		return year+"年"+month+"月";
	}
	
	/**
	 * 查询日期是当年第几月
	 * @param date
	 * @return
	 */
	public static String getYearYear(Date date) {
		Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year+"年";
	}
	
	/**
	 * 本月第一天日期
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();    
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
	}
	
	/**
	 * 本月最后一天日期
	 * @return
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();    
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return calendar.getTime();
	}
	
	/**
	 * 上月第一天日期
	 * @return
	 */
	public static Date getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();    
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
	}
	
	/**
	 * 上月最后一天日期
	 * @return
	 */
	public static Date getLastMonthLastDay() {
		Calendar calendar = Calendar.getInstance();    
	    calendar.set(Calendar.DAY_OF_MONTH,0);
        return calendar.getTime();
	}
	
	/**
	 * 指定日期月份加减
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getSubMonth(Date date,int month) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.MONTH, month);  
        return calendar.getTime(); 
	}
	
	/**
	 * 指定日期年份加减
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getSubYear(Date date,int year) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.YEAR, year);  
        return calendar.getTime(); 
	}
	
	/**
	 * 获取当月最大天数
	 * @return
	 */
	public static int getMaxMonthDays() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.get(Calendar.DATE);
	}
	
	/**
	 * 下个月最后一天
	 * @return
	 */
	public static Date getNextMonthLastDay() {
		Calendar calendar = Calendar.getInstance();    
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 当前月份
	 * @return
	 */
	public static int getNowMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 当前月份
	 * @return
	 */
	public static int getNowYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 指定年月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year,int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime() ;
	}
	
	/**
	 * 指定年月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year,int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 根据当月某天获取日期
	 * @param day
	 * @return
	 */
	public static Date getDateByDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime(); 
	}
	
	/**
	 * 获取范围内的日期
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			String fdate = format.format(date);
			return fdate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}
	
	/**
	 * 是否为当前月
	 * @param date
	 * @return
	 */
	public static boolean thisMonth(String date) {
		boolean result = false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			String now = format.format(new Date());
			String target = format.format(format.parse(date));
			if (now.equals(target)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 所在月最后一天
	 * @param date
	 * @return
	 */
	public static int lastDay(String date) {
		int maximum = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date time = format.parse(date);// 构造开始日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
	        maximum = cal.getActualMaximum(Calendar.DATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maximum;
	}
	
	/**
	 * 今天
	 * @return
	 */
	public static int today() {
		int today = 0;
		try {
			Date time = new Date();// 构造开始日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			today = cal.get(Calendar.DATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return today;
	}
	
	
	/**
	 * Long 转换为Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date longToDate(long date){
		return new Date(date * 1000);  
	}
	
}
