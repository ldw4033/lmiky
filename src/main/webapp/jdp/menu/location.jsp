<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp"%>
<%@ page import="com.lmiky.jdp.system.menu.controller.MenuController" %>
<div id="breadcrumb">
	<a href="<c:url value="/"/>" ><i class="icon-home"></i><%=MenuController.TOP_MENU_LABEL_MYINDEX %></a>
	<a href="<c:url value="/"/>${subMenu.url }&${httpParamTopMenuId}=${subMenu.leftMenu.topMenu.id}" ></i>${subMenu.leftMenu.topMenu.label }</a>
	<a href="javascript: void(0)" class="current">${subMenu.label }</a>
</div>