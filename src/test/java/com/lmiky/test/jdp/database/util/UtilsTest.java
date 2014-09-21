package com.lmiky.test.jdp.database.util;

import java.lang.reflect.Field;
import java.util.Collection;

import com.lmiky.jdp.user.pojo.User;
import com.lmiky.test.BaseTest;

/**
 * 类说明
 * @author lmiky
 * @date 2013-5-30
 */
public class UtilsTest extends BaseTest {
	
	public static void isCollectionClass(Class<?> propertyClass) {
		System.out.println(Collection.class.isAssignableFrom(propertyClass));
	}

	public static void testField() throws SecurityException, NoSuchFieldException {
		User user = new User();
		Field[] fields = user.getClass().getDeclaredFields();
		for(Field f : fields) {
			System.out.println(f.getName());
		}
	}
}
