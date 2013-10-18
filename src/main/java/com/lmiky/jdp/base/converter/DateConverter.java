package com.lmiky.jdp.base.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * 日期转换器
 * @author lmiky
 * @date 2013-5-8
 */
public class DateConverter implements Converter {
	private String pattern;	//日期格式
	
	/**
	 * @param pattern
	 */
	public DateConverter(String pattern) {
		this.pattern = pattern;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
		if (value == null) {
			return (null);
		}
		Date dateObj = null;
		if (value instanceof String) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				dateObj = sdf.parse((String) value);
			} catch (ParseException pe) {
				return null;
			}
		}
		return dateObj;
	}

}
