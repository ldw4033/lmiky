package com.lmiky.platform.servlet;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.beanutils.ConvertUtils;

import com.alibaba.fastjson.JSON;
import com.lmiky.platform.constants.Constants;
import com.lmiky.platform.controller.html.converter.DateConverter;

/**
 * @author lmiky
 * @date 2013-4-25
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {
	private static final long serialVersionUID = -9019448991067367058L;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 注册Conveter
		DateConverter dateConverter = new DateConverter();
		dateConverter.setUseLocaleFormat(true);
		dateConverter.setPatterns(new String[] { Constants.CONTEXT_KEY_FORMAT_DATE_VALUE, Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE });
		ConvertUtils.register(dateConverter, Date.class);
		//设置fastjson默认日期格式
		JSON.DEFFAULT_DATE_FORMAT = Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE;
		super.init(config);
	}
}
