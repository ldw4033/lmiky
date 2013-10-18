package com.lmiky.jdp.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring工具
 * @author lmiky
 * @date 2013-5-19
 */
public class SpringUtils {
	private static ServletContext servletContext;
	private static WebApplicationContext webApplicationContext;
	
	/**
	 * 获取bean
	 * @author lmiky
	 * @date 2013-5-19
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return webApplicationContext.getBean(beanName);
	}

	/**
	 * @param sc the servletContext to set
	 */
	public static void setServletContext(ServletContext servletContext) {
		SpringUtils.servletContext = servletContext;
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
	/**
	 * @return the servletContext
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @return the webApplicationContext
	 */
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	
}
