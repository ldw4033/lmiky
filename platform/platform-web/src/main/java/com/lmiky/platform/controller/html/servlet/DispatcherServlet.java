package com.lmiky.platform.controller.html.servlet;

import com.lmiky.platform.constants.Constants;
import com.lmiky.platform.controller.html.converter.DateConverter;
import com.lmiky.platform.util.Environment;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
		// 设置环境
		ServletContext application = config.getServletContext();
		Environment.setServletContext(application);
		super.init(config);
	}
}
