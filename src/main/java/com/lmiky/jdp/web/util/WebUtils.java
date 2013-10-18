package com.lmiky.jdp.web.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.lmiky.jdp.authority.service.AuthorityService;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.session.service.SessionService;
import com.lmiky.jdp.util.SpringUtils;

/**
 * web工具
 * @author lmiky
 * @date 2013-6-6
 */
public class WebUtils {
	
	/**
	 * 获取模块
	 * @author lmiky
	 * @date 2013-6-6
	 * @param baseService
	 * @param modulePath
	 * @return
	 * @throws ServiceException
	 */
	public static Module getModule(BaseService baseService, String modulePath) throws ServiceException {
		// 先从上下文环境中取
		ServletContext application = SpringUtils.getServletContext();
		String key = "modulePath_" + modulePath;
		Module module = (Module) application.getAttribute(key);
		if (module != null) {
			return module;
		}
		module = baseService.find(Module.class, new PropertyFilter("path", modulePath, PropertyCompareType.EQ, Module.class));
		if (module != null) {
			application.setAttribute(key, module);
		}
		return module;
	}
	
	/**
	 * 检查权限
	 * @author lmiky
	 * @date 2013-6-6
	 * @param baseService
	 * @param authorityService
	 * @param sessionInfo
	 * @param module
	 * @param authorityCode
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkAuthority(BaseService baseService, AuthorityService authorityService, SessionInfo sessionInfo, Module module, String authorityCode) throws ServiceException {
		//先从session缓存中找
		String authorityKey = module.getId() + "_" + authorityCode;
		Boolean hasAuthority = sessionInfo.isAuthority(authorityKey);
		if(hasAuthority == null) {
			Function function = baseService.find(Function.class, new PropertyFilter("authorityCode", authorityCode, PropertyCompareType.EQ, Function.class));
			if(function == null) {
				throw new ServiceException(String.format("function[authorityCode:%s] is null", authorityCode));
			}
			hasAuthority = authorityService.checkAuthority(module.getGroup().getId(), module.getId(), function.getId(), sessionInfo.getUserId());
		}
		if(!hasAuthority) {
			sessionInfo.setAuthority(authorityKey, false);
		} else {
			sessionInfo.setAuthority(authorityKey, true);
		}
		return hasAuthority;
	}
	
	/**
	 * 检查权限
	 * @author lmiky
	 * @date 2013-6-6
	 * @param authorityService
	 * @param sessionInfo
	 * @param module
	 * @param functionId
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkAuthority(AuthorityService authorityService, SessionInfo sessionInfo, Module module, long functionId) throws ServiceException {
		//先从session缓存中找
		String authorityKey = module.getId() + "_" + functionId;
		Boolean hasAuthority = sessionInfo.isAuthority(authorityKey);
		if(hasAuthority == null) {
			hasAuthority = authorityService.checkAuthority(module.getGroup().getId(), module.getId(), functionId, sessionInfo.getUserId());
		}
		if(!hasAuthority) {
			sessionInfo.setAuthority(authorityKey, false);
		} else {
			sessionInfo.setAuthority(authorityKey, true);
		}
		return hasAuthority;
	}
	
	/**
	 * 获取SessionInfo
	 * @author lmiky
	 * @date 2013-6-18
	 * @param request
	 * @return
	 */
	public static SessionInfo getSessionInfo(HttpServletRequest request) {
		SessionService sessionService = (SessionService)SpringUtils.getBean("sessionService");
		return sessionService.getSessionInfo(request);
	}
	
}
