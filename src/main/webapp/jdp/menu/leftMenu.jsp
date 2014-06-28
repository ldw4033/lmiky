<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp" %>
		<div id="sidebar">
			<ul>
				<li id="menuLabel"><a href="javascript: void(0)"><span>${topMenu.label }</span></a></li>
				<c:forEach items="${leftMenus }" var="leftMenu" varStatus="status">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>${leftMenu.label }</span></a>
					<ul>
						<c:forEach items="${leftMenu.subMenus }" var="subMenu">
							<li><a href="form-wizard.html">${subMenu.label }</a></li>
						</c:forEach>
					</ul>
				</li>
				</c:forEach>
			</ul>
		</div>	