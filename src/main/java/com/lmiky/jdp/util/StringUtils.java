package com.lmiky.jdp.util;
/**
 * 字符串工具
 * @author lmiky
 * @date 2013-4-25
 */
public class StringUtils {
	
	/**
	 * 给URL添加参数
	 * @author lmiky
	 * @date 2013-4-25
	 * @param url
	 * @param parameterName
	 * @param parameterValue
	 * @return
	 */
	public static String addUrlParameter(String url, String parameterName, String parameterValue) {
		if(url.indexOf("?") == -1) {
			url += "?";
		} else {
			url += "&";
		}
		url += parameterName + "=" + parameterValue;
		return url;
	}
	
}
