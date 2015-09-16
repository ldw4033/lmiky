package com.lmiky.platform.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * IP地址工具类
 * @author lmiky
 * @date 2015年5月11日 下午2:18:09
 */
public class IPUtils {

	/**
	 * 获取真实IP
	 * @author lmiky
	 * @date 2015年5月13日 下午4:15:49
	 * @param request
	 * @return
	 */
	public static String getRealIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		if("127.0.0.1".equalsIgnoreCase(ip) || "localhost".equalsIgnoreCase(ip) || ip.startsWith("192.168.")) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
