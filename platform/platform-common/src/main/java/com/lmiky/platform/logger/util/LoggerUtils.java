package com.lmiky.platform.logger.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lmiky.platform.constants.Constants;

/**
 * 日志工具
 * @author lmiky
 * @date 2013-5-10
 */
public class LoggerUtils {
	private static int exceptionStackLogNum = Constants.SYSTEM_EXCEPTION_STACKLOGNUM;
	private static final Log log = LogFactory.getLog(LoggerUtils.class);

	/**
	 * 获取日志对象
	 * @param clazz 要记录日志的类
	 * @return 日志对象
	 * @author lmiky
	 * @date 2015年10月21日 下午3:25:38
	 */
	public static Log getLog(Class<?> clazz) {
		return LogFactory.getLog(clazz);
	}

	/**
	 * 获取日志对象
	 * @param obj 要记录日志的对象
	 * @return 日志对象
	 * @author lmiky
	 * @date 2015年10月21日 下午3:26:25
	 */
	public static Log getLog(Object obj) {
		return LogFactory.getLog(obj.getClass());
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @return
	 */
	public static boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void debug(Object obj) {
		if (isDebugEnabled()) {
			log.debug(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void debug(Object obj, Throwable t) {
		if (isDebugEnabled()) {
			log.debug(obj, t);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void error(Object obj) {
		if (isErrorEnabled()) {
			log.error(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void error(Object obj, Throwable t) {
		if (isErrorEnabled()) {
			log.error(obj, t);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void fatal(Object obj) {
		if (isFatalEnabled()) {
			log.fatal(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void fatal(Object obj, Throwable t) {
		if (isFatalEnabled()) {
			log.fatal(obj, t);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void info(Object obj) {
		if (isInfoEnabled()) {
			log.info(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void info(Object obj, Throwable t) {
		if (isInfoEnabled()) {
			log.info(obj, t);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void trace(Object obj) {
		if (isTraceEnabled()) {
			log.trace(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void trace(Object obj, Throwable t) {
		if (isTraceEnabled()) {
			log.trace(obj, t);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 */
	public static void warn(Object obj) {
		if (isWarnEnabled()) {
			log.warn(obj);
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-10
	 * @param obj
	 * @param t
	 */
	public static void warn(Object obj, Throwable t) {
		if (isWarnEnabled()) {
			log.warn(obj, t);
		}
	}

	/**
	 * 处理异常日志
	 * @author lmiky
	 * @date 2013-5-31
	 * @param e
	 */
	private static void detailLogException(Throwable e) {
		// 记录异常对象
		error(e);
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		int logNum = exceptionStackLogNum;
		if (exceptionStackLogNum > stackTraceElements.length) {
			logNum = stackTraceElements.length;
		}
		// 记录异常跟踪信息
		StackTraceElement stackTraceElement = null;
		for (int i = 0; i < logNum; i++) {
			stackTraceElement = stackTraceElements[i];
			if (stackTraceElement == null) {
				break;
			}
			error(String.format("Caused by: %s", stackTraceElement));
		}
	}

	/**
	 * 记录异常
	 * @author lmiky
	 * @date 2014年11月13日 上午11:25:02
	 * @param exceptionDesc 错误说明
	 * @param e
	 */
	public static void logException(String exceptionDesc, Throwable e) {
		// 记录异常对象
		error(String.format(exceptionDesc + ": %s", e.getMessage()));
		detailLogException(e);
	}

	/**
	 * 记录异常
	 * @author lmiky
	 * @date 2013-5-31
	 * @param e
	 */
	public static void logException(Throwable e) {
		// 记录异常对象
		error(e.getMessage());
		detailLogException(e);
	}

}
