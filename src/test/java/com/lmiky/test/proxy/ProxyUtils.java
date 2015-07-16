/**
 * 
 */
package com.lmiky.test.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明
 * @author lmiky
 * @date 2015年7月15日 下午9:43:14
 */
public class ProxyUtils {
	private static Map<String, Object> proxies = new HashMap<>();

	/**
	 * 说明
	 * @author lmiky
	 * @date 2015年7月15日 下午9:44:35
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  static <T> T generateProxy(Object object) {
		String objClassName = object.getClass().getName();
		Object proxy = proxies.get(objClassName);
		if(proxy != null) {
			System.out.println("缓存中已经存在");
			return (T)proxy;
		}
		ServiceProxy sp = new ServiceProxy();  
		proxy = sp.bind(object);  
		proxies.put(objClassName, proxy);
		return (T)proxy;
	}
	
}
