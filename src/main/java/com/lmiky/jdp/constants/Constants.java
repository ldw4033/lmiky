package com.lmiky.jdp.constants;

/**
 * 常量
 * @author lmiky
 * @date 2013-4-16
 */
public class Constants {

	// ****************************************properties文件key****************************************//
	// 配置文件
	public static final String PROPERTIES_KEY_CONTEXT_FILE = "config/context";

	// 格式
	public static final String CONTEXT_KEY_FORMAT_DATE = "format.date"; // 日期
	public static final String CONTEXT_KEY_FORMAT_DATETIME = "format.dateTime"; // 时间

	// 分页
	public static final String CONTEXT_KEY_PAGE_PAGESIZE = "page.pageSize";

	// ****************************************properties文件key****************************************//

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
	
}