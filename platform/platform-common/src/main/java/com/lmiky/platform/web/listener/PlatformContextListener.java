package com.lmiky.platform.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lmiky.platform.util.BundleUtils;
import com.lmiky.platform.util.Environment;
import com.lmiky.platform.util.BundleUtils.EnvironmentType;

/**
 * 平台环境侦听器
 * @author lmiky
 * @date 2015年11月12日 上午11:31:14
 */
public class PlatformContextListener implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 设置环境
		ServletContext application = sce.getServletContext();
		Environment.setServletContext(application);
		String[] activeProfiles = Environment.getWebApplicationContext().getEnvironment().getActiveProfiles();
		// 设置文件读取工具部署环境
		if (activeProfiles != null && activeProfiles.length > 0) {
			BundleUtils.setEnvironmentType(EnvironmentType.build(activeProfiles[0]));
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
