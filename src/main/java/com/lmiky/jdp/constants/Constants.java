package com.lmiky.jdp.constants;

import java.util.Calendar;

import java.util.Date;

import com.lmiky.jdp.util.DateUtils;
import com.lmiky.jdp.util.PropertiesUtils;

/**
 * 常量
 * @author lmiky
 * @date 2013-4-16
 */
public class Constants {
	private static String beginDateTime;
	private static String endDateTime;
	// ****************************************properties文件key****************************************//
	// 配置文件
	public static final String PROPERTIES_KEY_CONTEXT_FILE = "config/context";

	// 格式
	public static final String CONTEXT_KEY_FORMAT_DATE = "format.date"; // 日期
	public static final String CONTEXT_KEY_FORMAT_DATETIME = "format.dateTime"; // 日期时间
	public static final String CONTEXT_KEY_FORMAT_TIME = "format.time"; // 时间
	//日期格式值
	public static final String CONTEXT_KEY_FORMAT_DATE_VALUE = PropertiesUtils.getStringContextValue(CONTEXT_KEY_FORMAT_DATE); // 日期
	public static final String CONTEXT_KEY_FORMAT_DATETIME_VALUE = PropertiesUtils.getStringContextValue(CONTEXT_KEY_FORMAT_DATETIME); // 日期时间
	public static final String CONTEXT_KEY_FORMAT_TIME_VALUE = PropertiesUtils.getStringContextValue(CONTEXT_KEY_FORMAT_TIME);  // 时间

	// 分页
	public static final String CONTEXT_KEY_PAGE_PAGESIZE = "page.pageSize";
	
	//文件上传路径
	public static final String SYSTEM_FILE_UPLOAD_PATH = "system.file.upload.path";

	// ****************************************properties文件key****************************************//

	
	public static final String SYSTEM_URI_PATTERN = PropertiesUtils.getStringContextValue("system.url.pattern").toLowerCase();
	
	//**********************************************Http参数**********************************************//
	
	public static final String HTTP_PARAM_PROPERTYFILTER_NAME_PREFIX = "propertyFilter_";	//过滤条件参数名前缀
	
	public static final String HTTP_PARAM_SORT_ORDERBY_NAME = "sort_orderBy";	//排序参数名
	public static final String HTTP_PARAM_SORT_TYPE_NAME_PREFIX  = "sort_type_";	//排序方式参数名前缀
	
	//分页
	public static final String HTTP_PARAM_PAGE_CURRENTPAGE = "page_currentPage";	//当前页
	public static final String HTTP_PARAM_PAGE_ACTION = "page_action";	//动作
	
	public static final String HTTP_PARAM_LOGIN_REDIRECT = "redirect";	//登陆
	public static final String HTTP_PARAM_LOGIN_CONTINUATION = "continuation";	//登陆

	public static final String HTTP_PARAM_MODULE_PATH= "modulePath";	//模块路劲
	public static final String HTTP_PARAM_SUBMENU_ID= "subMenuId";	//子菜单ID
	
	//**********************************************Http参数**********************************************//
	
	//**********************************************session属性**********************************************//
	
	public static final String SESSION_ATTR_SESSIONINFO = "sessionInfo";		//session信息
	
	public static final String SESSION_ATTR_CONTINUATION_URL_SUFFIX = ".url";
	public static final String SESSION_ATTR_CONTINUATION_PARAM_SUFFIX = ".parameters";
	
	//**********************************************session属性**********************************************//
	
	static {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		beginDateTime = DateUtils.format(date, CONTEXT_KEY_FORMAT_TIME_VALUE);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		date = calendar.getTime();
		endDateTime = DateUtils.format(date, CONTEXT_KEY_FORMAT_TIME_VALUE);
	}
	
	/**
	 * 开始时间
	 * @author lmiky
	 * @date 2014-1-25
	 * @return
	 */
	public static String getBeginDateTime() {
		return beginDateTime;
	}
	
	/**
	 * 结束时间
	 * @author lmiky
	 * @date 2014-1-25
	 * @return
	 */
	public static String getEndDateTime() {
		return endDateTime;
	}
}