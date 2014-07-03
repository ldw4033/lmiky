<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${script }/jquery.min.js" type="text/javascript"></script>
<c:set var="selectedLeftMenuId" value=""/>
<c:if test="${fn:contains(param.menuFrom, '-')}">
	<c:set var="selectedLeftMenuId" value="${fn:substringAfter(param.menuFrom, '-')}"/>
</c:if>
<div id="sidebar">
	<ul>
		<li id="menuLabel"><a href="javascript: void(0)"><span>${topMenu.label }</span><i class="icon-step-backward" style="float: right;" onclick="extendLeftMenu()"></i></a></li>
		<c:forEach items="${topMenu.leftMenus }" var="leftMenu" varStatus="status">
			<li class="submenu" id="leftMenu_${leftMenu.id }"><a href="#"><i class="icon icon-th-list"></i><span>${leftMenu.label }</span></a>
				<ul>
					<c:forEach items="${leftMenu.subMenus }" var="subMenuV">
						<li
							<c:if test="${subMenuV.id == subMenu.id && (empty selectedLeftMenuId || selectedLeftMenuId == leftMenu.id)}">
								class="active"
								<c:set var="selectedLeftMenuId" value="${leftMenu.id }"/>
							</c:if>
						><a href="<c:url value="/"/>${subMenuV.url }&${httpParamMenuFrom}=${topMenu.id}-${leftMenu.id}">${subMenuV.label }</a></li>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#leftMenu_${selectedLeftMenuId}').addClass('open');
	}); 
	
	function extendLeftMenu() {
		$('#sidebar').hide();
		$('#content').css('margin-left', '0px');
	}
</script>
