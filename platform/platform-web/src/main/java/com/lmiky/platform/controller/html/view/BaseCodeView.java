package com.lmiky.platform.controller.html.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.lmiky.platform.constants.Constants;
import com.lmiky.platform.controller.view.BaseCode;
import com.lmiky.platform.logger.util.LoggerUtils;
import com.lmiky.platform.util.BundleUtils;

/**
 * 带结果码的视图
 * @author lmiky
 * @date 2014年11月3日 下午2:26:15
 */
@Component
public class BaseCodeView extends BaseJsonView {
	public static final String KEY_NAME_CODE = "code";
	public static final String KEY_NAME_MSG = "msg";

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.controllerview.BaseJsonView#fill(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.util.Map)
	 */
	@Override
	protected void fill(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, Map<String, Object> resultMap) throws Exception {
		super.fill(map, request, response, resultMap);
		//填充结果码
		Integer codeValue = (Integer) map.get(KEY_NAME_CODE);
		if (codeValue == null) {
			codeValue = BaseCode.CODE_SUCCESS;
		}
		resultMap.put(KEY_NAME_CODE, codeValue.intValue());
		fillMsg(map, request, response, resultMap, codeValue.intValue());
	}

	/**
	 * 填充提示语
	 * @author lmiky
	 * @date 2014年11月3日 下午2:47:57
	 * @param map
	 * @param request
	 * @param response
	 * @param resultMap
	 * @param code
	 * @throws Exception
	 */
	protected void fillMsg(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, Map<String, Object> resultMap, int code) throws Exception {
		Object msgObj = map.get(KEY_NAME_MSG);
		String msg = "";
		try {
			if (msgObj == null) { // 从配置文件读
				msg = BundleUtils.getStringValue(Constants.PROPERTIES_KEY_CODE_MSG_FILE, code + "");
			} else {
				msg = msgObj.toString();
			}
		} catch (Exception e) { // 未配置
			LoggerUtils.logException(e);
		}
		resultMap.put(KEY_NAME_MSG, msg);
	}

	/**
	 * 设置结果码
	 *
	 * @param modelMap
	 * @param code
	 * @author lmiky
	 * @date 2015年9月15日 下午6:01:13
	 */
	public static void setCode(ModelMap modelMap, int code) {
	    modelMap.put(KEY_NAME_CODE, code);
	}
}
