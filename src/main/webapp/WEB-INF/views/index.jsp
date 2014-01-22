<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date,com.lmiky.jdp.util.DateUtils"%>
<%@page import="com.lmiky.jdp.constants.Constants"%>
<%@page import="com.lmiky.jdp.module.pojo.Function"%>
<%@ include file="/jdp/common/common.jsp" %>
<c:set var="frameUrl" value=""/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.lmiky.jdp.system.menu.controller.MenuController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<link type="text/css" rel="stylesheet" href="${css}/menu.css"/>
		<script type="text/javascript">
			var menuPrefix = "topMenu_";	//大菜单TR前缀
			var subMenuPrefix = "subMenu_";	//子菜单TR前缀
			var selectedSubMenu = null;	//当前选中的菜单
			
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
				
				selectedSubMenu = $(".title_open")[0];
				//var oMenu = document.getElementsByTagName("dt");
				//for(var i = 0; i < oMenu.length; i++)  {
				//	  if($(oMenu[i]).hasClass("title_open")) {
				//		  selectedSubMenu = oMenu[i];
				//		  break;
				//	  }
				//}
				//$(".top_dh a:first-child").trigger("click");
				
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
				
				selectTopMenu($("#topMenu_a_<%=MenuController.TOP_MENU_ID_MYINDEX%>"), '<%=MenuController.TOP_MENU_ID_MYINDEX%>');
			});
			
			//顶层大菜单点击
			function selectTopMenu(menuObj, menuDivId) {
				document.getElementById("leftMenu").src = '<c:url value="/sso/system/menu/listLeftMenus.shtml"/>?topMenuId=' + menuDivId;
			}
			
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
					 //var oMenu = document.getElementsByTagName("dt");
					 //for(var i = 0; i < oMenu.length; i++)  {
					//	  oMenu[i].className = "title_common title_close";
					//}
					//oThis.className = "title_common title_open";
				 }
				 document.getElementById("right").src=htmlSrc;
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
	<body scroll="no">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="2" valign="top">
								<div class="header">
								<div class="header-row1">
									<div style="float: left;">
										<h1 class="title">内容管理系统</h1>
									</div>
									<div style="float: right; font-weight: bold; color: #ffffff; margin-right: 10px; margin-top: 72px;">
										您好，${sessionScope.sessionInfo.userName }&nbsp;&nbsp;&nbsp;<img src="${images}/tb_tc.gif" border="0" align="absmiddle" onclick="logout()" style="cursor: pointer; margin-bottom: 5px;"/>&nbsp;&nbsp;&nbsp;
									</div>
								</div>
								</div>
							</td>
						</tr>
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
															<!-- 
															<li class="top_dh"><a href="http://zzjs.net/" target="_blank"><strong>两级菜单</strong></a>
																<ul>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;站长特效</a></li>
																	<li><a href="http://zzjs.net/?cat=2">&nbsp;网页特效</a></li>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;广告代码</a></li>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;rss订阅</a></li>
																</ul>
															</li>
															<li style="width: 2px;"><img src="${images}/meun_xian.gif" width="2" height="29" align="absmiddle" /></li>
															<li class="top_dh"><a href="http://zzjs.net/" target="_blank"><strong>三级菜单</strong></a>
																<ul>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;站长特效</a>
																		<ul>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;站长特效</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;整站项目</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;网站分析策划</a></li>
																			<li><a href="http://zzjs.net/?cat=2" target="_blank">&nbsp;网页设计制作</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;网站flash动画</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;网站服务器</a></li>
																		</ul>
																	</li>
																	<li><a href="http://zzjs.net/?cat=2">&nbsp;网页特效</a>
																		<ul>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;网站项目</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;平面设计</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;CAD工程图设计</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;3D建模与动画</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;音效及音乐</a></li>
																		</ul>
																	</li>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;广告代码</a>
																		<ul>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;应用软件</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;游戏开发</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;驱动程序</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;嵌入式开发</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;手机开发</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;单片机</a></li>
																			<li><a href="http://zzjs.net/?cat=2">&nbsp;数据库设计</a></li>
																			<li><a href="http://zzjs.net/?cat=1">&nbsp;代码移植</a></li>
																		</ul>
																	</li>
																	<li><a href="http://zzjs.net/?cat=1">&nbsp;rss订阅</a></li>
																</ul>
															</li>
															<li style="width: 2px;"><img src="${images}/meun_xian.gif" width="2" height="29" align="absmiddle" /></li>
															 -->
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
											<iframe src="" id="leftMenu" name="leftMenu" height="100%" frameborder="0" scrolling="auto" width="100%"></iframe>
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
											<iframe src="
												<c:if test="${not empty frameUrl}">
													<c:url value="/"/>${frameUrl }
												</c:if>
											" id="right" name="right" height="100%" frameborder="0" scrolling="auto" width="100%"></iframe>
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