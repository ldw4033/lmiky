<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.lmiky.jdp.web.util.WebUtils"%>
<%@page import="com.lmiky.jdp.session.model.SessionInfo"%>
<%@page import="com.lmiky.jdp.util.SpringUtils"%>
<%
	SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
	if(sessionInfo == null) {
		response.sendRedirect(request.getContextPath() + (String)SpringUtils.getBean("loginUrl"));
	} else {
		request.getRequestDispatcher("/sso/system/menu/load.shtml").forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/sso/system/menu/load.shtml");		
	}
%>