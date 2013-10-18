package com.lmiky.jdp.util;

/**
 * 环境
 * @author lmiky
 * @date 2013-4-22
 */
public class Environment {
	public static final String WEBINF_NAME = "WEB-INF";
	public static final String CLASSPATH_NAME = "classes";
	private static String classPath;	//web应用class目录
	private static String webAppPath;	//web应用上下文目录
	
	static {
		//初始化
		String path = Environment.class.getResource("").getPath();
		//需要判断，否则如果是Maven项目，在Junit环境下会报错
		int index = path.toUpperCase().lastIndexOf(WEBINF_NAME + "/");
		if(index != -1) {
			webAppPath = path.substring(0, index);
		}
		webAppPath = path.replaceAll("%20", " ");
		classPath = path.substring(0, path.toLowerCase().lastIndexOf(CLASSPATH_NAME + "/")).replaceAll("%20", " ");
	}
	
	/**
	 * 获取WEB-INF路劲
	 * @author lmiky
	 * @date 2013-4-22
	 * @return
	 */
	public static String getWebInfPath() {
		return webAppPath + WEBINF_NAME + "/";
	}

	/**
	 * 获取classpath目录
	 * @author lmiky
	 * @date 2013-10-16
	 * @return
	 */
	public static String getClassPath() {
		return classPath + CLASSPATH_NAME + "/";
	}
}
