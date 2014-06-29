<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp"%>
<script src="${script }/jquery.min.js" type="text/javascript"></script>
<c:set var="selectedLeftMenuId" value=""/>
<div id="sidebar">
	<ul>
		<li id="menuLabel"><a href="javascript: void(0)"><span>${topMenu.label }</span></a></li>
		<c:forEach items="${topMenu.leftMenus }" var="leftMenu" varStatus="status">
			<c:if test="${status.first }">
				<c:set var="selectedLeftMenuId" value="${leftMenu.id }"/>
			</c:if>
			<li class="submenu" id="leftMenu_${leftMenu.id }"><a href="#"><i class="icon icon-th-list"></i><span>${leftMenu.label }</span></a>
				<ul>
					<c:forEach items="${leftMenu.subMenus }" var="subMenuV">
						<li
							<c:if test="${subMenuV.id == subMenu.id }">
								class="active"
								<c:set var="selectedLeftMenuId" value="${leftMenu.id }"/>
							</c:if>
						><a href="<c:url value="/"/>${subMenuV.url }&${httpParamTopMenuId}=${topMenu.id}">${subMenuV.label }</a></li>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#leftMenu_${selectedLeftMenuId}').addClass('open');
	}); 
</script>
