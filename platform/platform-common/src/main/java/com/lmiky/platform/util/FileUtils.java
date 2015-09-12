package com.lmiky.platform.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件工具
 * @author lmiky
 * @date 2014-1-27
 */
public class FileUtils {

	/**
	 * 文件名称编码
	 * @author lmiky
	 * @date 2015年5月21日 下午2:06:48
	 * @param fileName
	 * @param enc
	 * @return
	 */
	public static String encodeFileName(String fileName, String enc) {
		try {
			return URLEncoder.encode(fileName.replaceAll("\\x23", "%23"), "utf-8").replaceAll("\\x2b", "%20");
		}
		catch (UnsupportedEncodingException e) {
			return fileName;
		}
	}
}
