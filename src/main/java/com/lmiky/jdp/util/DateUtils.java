package com.lmiky.jdp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lmiky.jdp.constants.Constants;

/**
 * 日期工具类
 * @author 
 * @date 2013-5-16
 */
public class DateUtils {
	
	/**
	 * 格式化日期/时间
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @param format 日期/时间格式
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}
	
	/**
	 * 格式化日期，用默认的日期格式
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, Constants.CONTEXT_KEY_FORMAT_DATE_VALUE);
	}
	
	/**
	 * 格式化时间，用默认的时间格式
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(date, Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE);
	}
	
	/**
	 * 字符串转时间
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 字符串转时间，用默认的日期格式
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parseDate(String date) {
		return parse(date, Constants.CONTEXT_KEY_FORMAT_DATE_VALUE);
	}
	
	/**
	 * 字符串转时间，用默认的时间格式
	 * @author
	 * @date 2013-5-16
	 * @param date
	 * @return
	 */
	public static Date parseTime(String date) {
		return parse(date, Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE);
	}
}
