<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date,com.lmiky.jdp.util.DateUtils"%>
<%@page import="com.lmiky.jdp.constants.Constants"%>
<%@page import="com.lmiky.jdp.module.pojo.Function"%>
<%@ include file="/jdp/common/common.jsp" %>
<%@ page import="com.lmiky.jdp.system.menu.controller.MenuController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<link type="text/css" rel="stylesheet" href="${css}/menu.css"/>
		<script type="text/javascript">
			var menuPrefix = "topMenu_";	//大菜单TR前缀
			
			//头部导航滑动样式
			$(document).ready(function() {
				var sfEls = document.getElementById("nav").getElementsByTagName("li"); 
				for (var i=0; i<sfEls.length; i++) {
					sfEls[i].onmouseover=function() {
					 	this.className+=" sfhover";
					   	if($(this).hasClass("top_dh")) {
					   		$(this).addClass("top_dhsfhover");
						}
					}
					sfEls[i].onmouseout=function() {
				  		this.className=this.className.replace(new RegExp(" sfhover\\b"), "");
				  		this.className=this.className.replace(new RegExp(" top_dhsfhover\\b"), "");	 
				  	}
				}
				selectTopMenu($("#topMenu_a_<%=MenuController.TOP_MENU_ID_MYINDEX%>"), '<%=MenuController.TOP_MENU_ID_MYINDEX%>');
			});
			
			//顶层大菜单点击
			function selectTopMenu(menuObj, menuDivId) {
				document.getElementById("leftMenu").src = '<c:url value="/sso/system/menu/listLeftMenus.shtml"/>?topMenuId=' + menuDivId;
			}
			
			//隐藏菜单栏
			function changImg(obj){
				if(obj.src.indexOf("${images}/shensuo-b.gif")>-1){
					obj.src="${images}/shensuo-a.gif";
					document.getElementById("left_").style.display="none";
				}else{
					obj.src="${images}/shensuo-b.gif";
					document.getElementById("left_").style.display="";
				}
			}
			
			function logout() {
				var win = window;
				while(win != win.parent) {
					win = win.parent;
				}
				win.location.href = "<c:url value="/sso/login/logout.shtml"/>";
			}
			
    </script>
	</head>
	<body scroll="no" style="background: #EFEFEF url(${images}/bg.png) repeat 0 0; overflow:hidden; height:100%;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center"  class="home_top">
			<tr>
				<td>&nbsp;</td>
				<td width="1140" style="padding-right: 20px; text-align: right;" valign="middle">
					您好，${sessionScope.sessionInfo.userName }
				</td>
				<td width="60" align="left" valign="middle">
					<img src="${images}/tb_tc.gif" border="0" align="absmiddle" onclick="logout()" style="cursor: pointer; margin-bottom: 1px;"/>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<table width="1200" height="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td colspan="3" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="31" colspan="2" align="center" background="${images}/top1.gif">
								<table width="98%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="190" class="STYLE1" id="jnkc">今天是&nbsp;<%=DateUtils.format(new Date(), "yyyy年M月d日") %></td>
										<td align="left">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td align="left">
														<ul id="nav">
															<li style="width: 2px;"><img src="${images}/meun_xian.gif" width="2" height="29" align="absmiddle" /></li>
															<li class="top_dh"><a  id="topMenu_a_<%=MenuController.TOP_MENU_ID_MYINDEX%>" href="javascript:void(0)" onclick="selectTopMenu(this, '<%=MenuController.TOP_MENU_ID_MYINDEX%>')"><strong>个人主页</strong></a></li>
															<li style="width: 2px;"><img src="${images}/meun_xian.gif" width="2" height="29" align="absmiddle" /></li>
															<c:forEach items="${menus }" var="topMenu" varStatus="status">
																<li class="top_dh"><a id="topMenu_a_${topMenu.id }"  href="javascript:void(0)" onclick="selectTopMenu(this, '${topMenu.id }')"><strong>${topMenu.label }</strong></a></li>
																<li style="width: 2px;"><img src="${images}/meun_xian.gif" width="2" height="29" align="absmiddle" /></li>
															</c:forEach>
														</ul>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="15" colspan="2" align="right" valign="bottom" background="${images}/top3.gif"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left" width="190" valign="top" id="left_">
					<table width="190" height="100%" border="0" cellpadding="0" cellspacing="0" class="bian4">
						<tr>
							<td height="100%">
								<table width="100%" height="100%" valign="top" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" class="bian1">
									<tr>
										<td>
											<iframe src="" id="leftMenu" name="leftMenu" height="100%" frameborder="0" scrolling="no" width="100%"></iframe>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td width="8" height="100%" valign="middle" bgcolor="#F5F5F5">
					<img src="${images}/shensuo-b.gif" width="8" height="126" style="cursor: pointer;" onClick="changImg(this)">
				</td>
				<td width="100%" align="center" valign="top">
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width="100%" height="100%" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top" bgcolor="#FFFFFF">
											<iframe src="" id="right" name="right" height="100%" frameborder="0" scrolling="auto" width="100%"></iframe>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>