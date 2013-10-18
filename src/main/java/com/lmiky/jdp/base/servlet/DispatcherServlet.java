package com.lmiky.jdp.base.servlet;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.lmiky.jdp.base.converter.DateConverter;
import com.lmiky.jdp.constants.Constants;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.session.service.SessionService;
import com.lmiky.jdp.system.menu.pojo.LatelyOperateMenu;
import com.lmiky.jdp.util.PropertiesUtils;
import com.lmiky.jdp.util.SpringUtils;
import com.lmiky.jdp.web.model.ContinuationRequest;

/**
 * @author lmiky
 * @date 2013-4-25
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {
	private static final long serialVersionUID = -4059059731809837012L;
	private BaseService baseService;
	private SessionService sessionService;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 注册Conveter
		ConvertUtils.register(new DateConverter(PropertiesUtils.getStringContextValue(Constants.CONTEXT_KEY_FORMAT_DATE)), java.util.Date.class);
		ConvertUtils.register(new DateConverter(PropertiesUtils.getStringContextValue(Constants.CONTEXT_KEY_FORMAT_DATETIME)), java.util.Date.class);
		//设置环境
		ServletContext application = config.getServletContext();
		application.setAttribute(Constants.CONTEXT_KEY_FORMAT_DATE, PropertiesUtils.getStringContextValue(Constants.CONTEXT_KEY_FORMAT_DATE));
		application.setAttribute(Constants.CONTEXT_KEY_FORMAT_DATETIME, PropertiesUtils.getStringContextValue(Constants.CONTEXT_KEY_FORMAT_DATETIME));
		SpringUtils.setServletContext(application);
		baseService = (BaseService)SpringUtils.getBean("baseService");
		sessionService = (SessionService)SpringUtils.getBean("sessionService");
		super.init(config);
	}



	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#doService(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String continuationId = request.getParameter(Constants.HTTP_PARAM_LOGIN_CONTINUATION);
		if (!StringUtils.isBlank(continuationId) && !(request instanceof ContinuationRequest)) {
			HttpSession session = request.getSession();
			Map parameters = (Map)session.getAttribute(continuationId + Constants.SESSION_ATTR_CONTINUATION_PARAM_SUFFIX);
			if(parameters!=null) {
				session.removeAttribute(continuationId + Constants.SESSION_ATTR_CONTINUATION_PARAM_SUFFIX);
				request = new ContinuationRequest(request, parameters); //构造新的请求
			}
		} else {	//非继续登录之前的操作，防止重复记录
			String subMenuId = request.getParameter(Constants.HTTP_PARAM_SUBMENU_ID);
			SessionInfo sessionInfo = sessionService.getSessionInfo(request);
			//记录最近操作
			if(!StringUtils.isBlank(subMenuId) && sessionInfo != null && sessionInfo.getUserId() != null) {
				LatelyOperateMenu latelyOperateMenu = new LatelyOperateMenu();
				latelyOperateMenu.setMenuId(subMenuId);
				latelyOperateMenu.setUserId(sessionInfo.getUserId());
				latelyOperateMenu.setOpeTime(new Date());
				baseService.save(latelyOperateMenu);
			}
		}
		super.doService(request, response);
	}

}
