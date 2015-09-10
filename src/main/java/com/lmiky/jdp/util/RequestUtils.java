package com.lmiky.jdp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Request工具
 *
 * @author lmiky
 * @date 2015年9月10日 下午3:24:34
 */
public class RequestUtils {

    /**
     * 判断是否ajax请求
     *
     * @param request 请求对象
     * @return 是否ajax请求
     * @author lmiky
     * @date 2015年9月10日 下午2:28:02
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        return false;
    }

    /**
     * 构建项目根路径
     *
     * @param request request
     * @return 项目根路径
     * @author lmiky
     * @date 2015年8月13日 下午4:06:31
     */
    public static final String buildBasePath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
    }
}
