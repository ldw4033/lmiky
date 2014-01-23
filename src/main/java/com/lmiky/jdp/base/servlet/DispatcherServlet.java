package com.lmiky.jdp.base.servlet;

import java.util.Date;
import java.util.HashMap;
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
import com.lmiky.jdp.logger.util.LoggerUtils;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.session.service.SessionService;
import com.lmiky.jdp.system.menu.pojo.LatelyOperateMenu;
import com.lmiky.jdp.util.Environment;
import com.lmiky.jdp.web.model.ContinuationRequest;

/**
 * @author lmiky
 * @date 2013-4-25
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {
	private static final long serialVersionUID = 8851136668309720276L;
	private BaseService baseService;
	private SessionService sessionService;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 注册Conveter
		ConvertUtils.register(new DateConverter(Constants.CONTEXT_KEY_FORMAT_DATE_VALUE), java.util.Date.class);
		ConvertUtils.register(new DateConverter(Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE), java.util.Date.class);
		//设置环境
		ServletContext application = config.getServletContext();
		Environment.setServletContext(application);
		baseService = (BaseService)Environment.getBean("baseService");
		sessionService = (SessionService)Environment.getBean("sessionService");
		super.init(config);
	}



	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#doService(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			if(!StringUtils.isBlank(subMenuId)) {
				SessionInfo sessionInfo = sessionService.getSessionInfo(request);
				//记录最近操作
				if(sessionInfo != null && sessionInfo.getUserId() != null) {
					String latelyOperateMenuId = sessionInfo.getLatelyOperateMenuId();
					//如果跟上次操作一样，就不重复记录
					if(StringUtils.isBlank(latelyOperateMenuId) ||  !subMenuId.equals(latelyOperateMenuId)) {
						LatelyOperateMenu latelyOperateMenu = new LatelyOperateMenu();
						latelyOperateMenu.setMenuId(subMenuId);
						latelyOperateMenu.setUserId(sessionInfo.getUserId());
						latelyOperateMenu.setOpeTime(new Date());
						baseService.save(latelyOperateMenu);
						sessionInfo.setLatelyOperateMenuId(subMenuId);
					}
				}
			}
			//保存参数
			try {
				SessionInfo sessionInfo = sessionService.getSessionInfo(request);
				if(sessionInfo != null) {
					sessionInfo.AddUrlParamHistory(request.getRequestURI(), new HashMap(request.getParameterMap()));
				}
			} catch(Exception e) {
				LoggerUtils.logException(e);
			}
		}
		super.doService(request, response);
	}

}
