package com.lmiky.platform.util;

import java.util.ResourceBundle;

import com.lmiky.platform.constants.Constants;

/**
 * 资源文件工具栏
 * @author lmiky
 * @date 2013-4-16
 */
public class BundleUtils {
	// 当前环境类别
	private static EnvironmentType environmentType = null;

	// 环境类别
	public static enum EnvironmentType {
		PRODUCTION("production"), // 运营环境
		TEST("test"), // 测试
		DEVELOPMENT("development"); // 开发

		private String value;

		/**
		 * @param value
		 */
		private EnvironmentType(String value) {
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		/**
		 * 构建
		 * @param value 值
		 * @return EnvironmentType对象
		 * @author lmiky
		 * @date 2015年11月12日 下午4:50:16
		 */
		public static EnvironmentType build(String value) {
			if (value == null) {
				return null;
			}
			return valueOf(value.toUpperCase());
		}
	}

	/**
	 * @return the environmentType
	 */
	public static EnvironmentType getEnvironmentType() {
		return environmentType;
	}

	/**
	 * @param environmentType the environmentType to set
	 */
	public static void setEnvironmentType(EnvironmentType environmentType) {
		BundleUtils.environmentType = environmentType;
	}

	/**
	 * 构建环境资源文件
	 * @param file
	 * @return
	 * @author lmiky
	 * @date 2015年11月12日 上午11:16:44
	 */
	public static String buildEnvironmentResourceFile(String file) {
		String directory = file.substring(0, file.lastIndexOf("/") + 1);
		String fileName = file.substring(file.lastIndexOf("/") + 1);
		return directory + ((environmentType == null) ? "" : (environmentType.getValue() + "/")) + fileName;
	}

	/**
	 * @author lmiky
	 * @date 2013-4-16
	 * @param file 资源文件
	 * @param bundleKey
	 * @return
	 */
	public static String getStringValue(String file, String key) {
		return ResourceBundle.getBundle(file).getString(key);
	}

	/**
	 * @author lmiky
	 * @date 2013-4-16
	 * @param file
	 * @param key
	 * @return
	 */
	public static int getIntValue(String file, String key) {
		return Integer.parseInt(getStringValue(file, key));
	}

	/**
	 * @author lmiky
	 * @date 2013-4-16
	 * @param file
	 * @param key
	 * @return
	 */
	public static long getLongValue(String file, String key) {
		return Long.parseLong(getStringValue(file, key));
	}

	/**
	 * @author lmiky
	 * @date 2013-4-16
	 * @param file
	 * @param key
	 * @return
	 */
	public static float getFloatValue(String file, String key) {
		return Float.parseFloat(getStringValue(file, key));
	}

	/**
	 * @author lmiky
	 * @date 2013-4-16
	 * @param file
	 * @param key
	 * @return
	 */
	public static double getDoubleValue(String file, String key) {
		return Double.parseDouble(getStringValue(file, key));
	}

	/**
	 * @author lmiky
	 * @date 2013-10-18
	 * @param file
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(String file, String key) {
		return Boolean.parseBoolean(getStringValue(file, key));
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-4-16
	 * @param key
	 * @return
	 */
	public static int getIntContextValue(String key) {
		return getIntValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-4-16
	 * @param key
	 * @return
	 */
	public static long getLongContextValue(String key) {
		return getLongValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-4-16
	 * @param key
	 * @return
	 */
	public static float getFloatContextValue(String key) {
		return getFloatValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-4-16
	 * @param key
	 * @return
	 */
	public static double getDoubleContextValue(String key) {
		return getDoubleValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-4-16
	 * @param key
	 * @return
	 */
	public static String getStringContextValue(String key) {
		return getStringValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

	/**
	 * 获取环境配置文件
	 * @author lmiky
	 * @date 2013-10-18
	 * @param key
	 * @return
	 */
	public static boolean getBooleanContextValue(String key) {
		return getBooleanValue(Constants.PROPERTIES_KEY_CONTEXT_FILE, key);
	}

}
