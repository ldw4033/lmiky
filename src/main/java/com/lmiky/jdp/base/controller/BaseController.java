package com.lmiky.jdp.base.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

import com.lmiky.jdp.authority.exception.AuthorityException;
import com.lmiky.jdp.authority.service.AuthorityService;
import com.lmiky.jdp.constants.Constants;
import com.lmiky.jdp.logger.util.LoggerUtils;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.session.exception.SessionException;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.session.service.SessionService;
import com.lmiky.jdp.sso.exception.SsoException;
import com.lmiky.jdp.sso.service.SsoService;
import com.lmiky.jdp.util.UUIDGenerator;
import com.lmiky.jdp.web.util.WebUtils;


/**
 * 控制器
 * @author lmiky
 * @date 2013-4-15
 */
public abstract class BaseController {
	public static final String MESSAGE_INFO_KEY = "messageInfos";
	public static final String ERROR_INFO_KEY = "errorInfos";
	public static final String REDIRECT_TO_LOGIN_REASON_KEY = "redirectToLoginReason";	//跳转到登陆页面重新登陆原因
	protected BaseService service;
	protected SessionService sessionService;
	protected SsoService ssoService;
	protected AuthorityService authorityService;
	private String viewType;
	protected String loginUrl;
	
	/**
	 * 设入提示信息
	 * @author lmiky
	 * @date 2013-4-24
	 * @param modelMap
	 * @param message
	 */
	@SuppressWarnings("unchecked")
	public void putMessage(ModelMap modelMap, String message) {
		List<String> messageInfos = (List<String>) modelMap.get(MESSAGE_INFO_KEY);
		if(messageInfos == null) {
			messageInfos = new ArrayList<String>();
			modelMap.put(MESSAGE_INFO_KEY, messageInfos);
		}
		messageInfos.add(message);
	}
	
	/**
	 * 设入错误信息
	 * @author lmiky
	 * @date 2013-5-9
	 * @param modelMap
	 * @param error
	 */
	@SuppressWarnings("unchecked")
	public void putError(ModelMap modelMap, String error) {
		List<String> errorInfos = (List<String>) modelMap.get(ERROR_INFO_KEY);
		if(errorInfos == null) {
			errorInfos = new ArrayList<String>();
			modelMap.put(ERROR_INFO_KEY, errorInfos);
		}
		errorInfos.add(error);
	}
	
	/**
	 * 异常处理
	 * @author lmiky
	 * @date 2013-4-24
	 * @param e
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String transactException(Exception e, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logException(e, modelMap, request, response);
		if(e instanceof SessionException) {
			return redirectToLogin(modelMap, request, response, "登陆超时！");
		}
		if(e instanceof SsoException) {
			sessionService.removeSessionInfo(request);	//清除SessionInfo，如果是在别处登录，即使别处已经下线，还是强制必须得重新登录
			return redirectToLogin(modelMap, request, response, "当前账号在别处登陆！");
		}
		if(e instanceof AuthorityException) {
			return redirectToLogin(modelMap, request, response, "没有权限！");
		}
		putError(modelMap, e.getMessage());
		throw e;
	}
	
	/**
	 * 记录异常日志
	 * @author lmiky
	 * @date 2013-5-6
	 * @param e
	 * @param modelMap
	 * @param request
	 * @param response
	 */
	public void logException(Exception e, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		LoggerUtils.logException(e);
	}
	
	/**
	 * 跳转到登陆页面
	 * @author lmiky
	 * @date 2013-4-24
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param reason
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String redirectToLogin(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String reason) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		String continuation = UUIDGenerator.generateString();
		HashMap parameters = (request.getParameterMap()==null) ? new HashMap() : new HashMap(request.getParameterMap());
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if(!StringUtils.isBlank(queryString)) {
			url += "?" + queryString;
		}
		session.setAttribute(continuation + Constants.SESSION_ATTR_CONTINUATION_URL_SUFFIX, url);
		session.setAttribute(continuation + Constants.SESSION_ATTR_CONTINUATION_PARAM_SUFFIX, parameters);
		if(!StringUtils.isBlank(reason)) {
			//modelMap.put(REDIRECT_TO_LOGIN_REASON_KEY, URLEncoder.encode(reason, "UTF-8"));
			modelMap.put(REDIRECT_TO_LOGIN_REASON_KEY, URLEncoder.encode(reason, "UTF-8"));
		}
		return "redirect:" + loginUrl + "?" + Constants.HTTP_PARAM_LOGIN_REDIRECT + "=" + continuation;
	}
	
	/**
	 * 获取会话信息
	 * @author lmiky
	 * @date 2013-4-24
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws SessionException 
	 */
	public SessionInfo getSessionInfo(ModelMap modelMap, HttpServletRequest request) throws SessionException {
		SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
		if(sessionInfo == null) {
			throw new SessionException(SessionException.SESSION_NULL);
		}
		return sessionInfo;
	}
	
	/**
	 * 检查单点登陆
	 * @author lmiky
	 * @date 2013-4-24
	 * @param sessionInfo
	 * @param modelMap
	 * @param request
	 * @throws SsoException
	 */
	public void checkSso(SessionInfo sessionInfo, ModelMap modelMap, HttpServletRequest request) throws SsoException {
		ssoService.checkSso(sessionInfo);
	}
	
	/**
	 * 获取模块路劲
	 * @author lmiky
	 * @date 2013-5-13
	 * @param modelMap
	 * @param request
	 * @return
	 */
	public String getModulePath(ModelMap modelMap, HttpServletRequest request) {
		String modulePath = request.getParameter(Constants.HTTP_PARAM_MODULE_PATH);
		if(modulePath == null) {
			modulePath = "";
		}
		return modulePath.trim();
	}
	
	/**
	 * 获取当前模块
	 * @author lmiky
	 * @date 2013-5-23
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public Module getModule(ModelMap modelMap, HttpServletRequest request) throws ServiceException {
		return WebUtils.getModule(service, getModulePath(modelMap, request));
	}
	
	/**
	 * 检查权限，如果没有权限，则抛出权限异常
	 * @author lmiky
	 * @date 2013-5-24
	 * @param modelMap
	 * @param request
	 * @param sessionInfo
	 * @param module
	 * @param authorityCode
	 * @throws ServiceException
	 * @throws AuthorityException
	 */
	public void checkAuthority(ModelMap modelMap, HttpServletRequest request, SessionInfo sessionInfo, Module module, String authorityCode) throws ServiceException, AuthorityException {
		if(!WebUtils.checkAuthority(service, authorityService, sessionInfo, module, authorityCode)) {
			throw new AuthorityException("no authority");
		}
	}
	
	/**
	 * 检查权限，如果没有权限，则抛出权限异常
	 * @author lmiky
	 * @date 2013-5-24
	 * @param modelMap
	 * @param request
	 * @param sessionInfo
	 * @param module
	 * @param functionId
	 * @throws ServiceException
	 * @throws AuthorityException
	 */
	public void checkAuthority(ModelMap modelMap, HttpServletRequest request, SessionInfo sessionInfo, Module module, long functionId) throws ServiceException, AuthorityException {
		if(!WebUtils.checkAuthority(authorityService, sessionInfo, module, functionId)) {
			throw new AuthorityException("no authority");
		}
	}
	
	/**
	 * 设置业务对象
	 * @author lmiky
	 * @date 2013-4-15
	 * @param service
	 */
	@Resource(name="baseService")
	public void setService(BaseService service) {
		this.service = service;
	}
	
	/**
	 * 获取业务对象
	 * @author lmiky
	 * @date 2013-4-15
	 * @return
	 */
	public BaseService getService() {
		return service;
	}
	
	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService() {
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	@Resource(name="sessionService")
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	@Resource(name="viewType")
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * @param loginUrl the loginUrl to set
	 */
	@Resource(name="loginUrl")
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	/**
	 * @return the ssoService
	 */
	public SsoService getSsoService() {
		return ssoService;
	}

	/**
	 * @param ssoService the ssoService to set
	 */
	@Resource(name="ssoService")
	public void setSsoService(SsoService ssoService) {
		this.ssoService = ssoService;
	}

	/**
	 * @return the authorityService
	 */
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	/**
	 * @param authorityService the authorityService to set
	 */
	@Resource(name="authorityService")
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
}
