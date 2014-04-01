<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date,com.lmiky.jdp.util.DateUtils"%>
<%@page import="com.lmiky.jdp.constants.Constants"%>
<%@page import="com.lmiky.jdp.module.pojo.Function"%>
<%@ include file="/jdp/common/common.jsp" %>
<c:set var="frameUrl" value=""/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<link type="text/css" rel="stylesheet" href="${css}/menu.css"/>
		<script type="text/javascript">
		
			var defaultDialogWith = 800;
			var defaultDialogHeight = 550;
		
			var menuPrefix = "topMenu_";	//大菜单TR前缀
			var subMenuPrefix = "subMenu_";	//子菜单TR前缀
			var selectedSubMenu = null;	//当前选中的菜单
			
			//左边大菜单点击
			function selectMenu(menuObj, subMenuTrId) {
				$("tr[id^='" + subMenuPrefix + "']").fadeOut(500);
				$("#" + subMenuPrefix + subMenuTrId).fadeIn(500);
			}
			//左边子菜单点击事件
			function selectSubMenu(oThis, htmlSrc) {
				 if(oThis) {
					 if(selectedSubMenu != null) {
						 selectedSubMenu.className = "title_common title_close";
					 }
					 oThis.className = "title_common title_open";
					 selectedSubMenu = oThis;
				 }
				 parent.document.getElementById("contentFrame").src = htmlSrc;
			} 
			
 	   </script>
	</head>
	<body>
		<table width="100%" height="100%" valign="top" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td align="center" valign="top" bgcolor="#FFFFFF">
					<c:forEach items="${leftMenus }" var="leftMenu" varStatus="status">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="b" >
							<tr>
								<td height="2"></td>
							</tr>
							<tr>
								<td class="menuCatetory STYLE3" onClick="selectMenu(this, '${leftMenu.id }')" width="160" height="50" align="left" valign="middle">
									&nbsp;<img src="${images}/jt-1.gif" width="16" height="16" align="absmiddle" border="0"> ${leftMenu.label }
								</td>
							</tr>
							<tr id="subMenu_${leftMenu.id }"
								<c:if test="${!status.first }">
									style="display: none"
								</c:if>
							>
								<td align="center">
									<div style="margin-top: 2px; width: 170px;">
										<!--一级分类-->
										<c:forEach items="${leftMenu.subMenus }" var="subMenu">
											<div runat="server">
												<c:choose>
													<c:when test="${subMenu.type == 'link' }">
														<dt class="title_common 
														<c:choose>
															<c:when test="${empty frameUrl}">
																<c:set var="frameUrl" value="${subMenu.url }"/>
																title_open
															</c:when>
															<c:otherwise>
																title_close
															</c:otherwise>
														</c:choose>
														" onClick="selectSubMenu(this, '<c:url value="/"/>${subMenu.url }');">${subMenu.label }</dt>
													</c:when>
													<c:when test="${subMenu.type == 'dialog' }">
														<dt class="title_common title_close" 
														onClick="parent.openDialog('<c:url value="/"/>${subMenu.url }', 
														<c:choose><c:when test="${not empty subMenu.width}">${subMenu.width }</c:when><c:otherwise>defaultDialogWith</c:otherwise></c:choose>,
														<c:choose><c:when test="${not empty subMenu.height}">${subMenu.height }</c:when><c:otherwise>defaultDialogHeight</c:otherwise></c:choose>, 
														'${subMenu.label }')">${subMenu.label }</dt>
													</c:when>
													<c:when test="${subMenu.type == 'iframe' }">
														<iframe src="<c:url value="/"/>${subMenu.url }" id="menuFrame_${subMenu.id }" onload="resizeIframe('menuFrame_${subMenu.id }')" frameborder="0"  width="100%" scrolling="no" ></iframe>
													</c:when>
												</c:choose>
											</div>
										</c:forEach>
									</div>
								</td>
							</tr>
						</table>
					</c:forEach>
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		//左菜单滑过
		$("td.menuCatetory").hover(
			function(){
				$(this).removeClass("STYLE3");
				$(this).addClass("STYLE6");
			},
			function(){
				$(this).removeClass("STYLE6");
				$(this).addClass("STYLE3");
			}
		);
		//子菜单滑过
		$("dt").hover(
			function(){
				if($(this).hasClass("title_close")) {
					$(this).removeClass("title_close");
					$(this).addClass("title_hover");
				}
			},
			function(){
				if($(this).hasClass("title_hover")) {
					$(this).removeClass("title_hover");
					$(this).addClass("title_close");
				}
			}
		);
		//默认选择子菜单
		selectSubMenu($(".title_open")[0], '${frameUrl}');
	});
</script>