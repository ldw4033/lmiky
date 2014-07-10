<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="redirect" value="<%=com.lmiky.jdp.constants.Constants.HTTP_PARAM_LOGIN_REDIRECT%>" />
<html>
	<head>
		<title></title>
		<style type="text/css">
			.dl_dx {
				background-color:#E5E5E5;
				BORDER-RIGHT: #DEDEDE 0px solid; 
				BORDER-TOP: #ffffff 5px solid;
				BORDER-LEFT: #DEDEDE 0px solid;
				BORDER-BOTTOM: #E51515 2px solid;
				height: 50px;
			}
		</style>
		<script src="${script }/md5.js" " type="text/javascript"></script>
		<script type="text/javascript">
			function login() {
				$("#mainForm").submit();
			}
			
			function beforeLogin() {
				var passwordInput = document.getElementsByName("password")[0];
				var password = passwordInput.value;
				if (password && password != "") {
					password = hex_md5(password).toUpperCase();
					passwordInput.value = password;
				}
				return true;
			}
			
			$(document).ready(function() {
				var loginName = $("[name='loginName']").val();
				if(loginName == '') {
					$("[name='loginName']").focus();
				} else {
					$("[name='password']").focus();
				}
			});
		</script>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/sso/login/login.shtml"/>" method="post" onsubmit="return beforeLogin()" style="height: 100%">
			<input type="hidden" name="${redirect }" value="${param[redirect] }"/>
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" valign="middle">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="180" align="right" valign="bottom">
									&nbsp;
								</td>
								<td colspan="3" rowspan="2" align="center" valign="middle">
									<table width="516" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td height="328" align="center" background="${images}/login.jpg">
												<table width="85%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="70" colspan="3" align="right" valign="bottom">
															<strong class="STYLE5">内容管理</strong><strong class="STYLE4">系统</strong>
														</td>
													</tr>
													<tr>
														<td height="20" colspan="3">&nbsp;</td>
													</tr>
													<tr>
														<td width="243" height="30">&nbsp;</td>
														<td width="60" align="right">用&nbsp;户&nbsp;名：</td>
														<td width="136" align="left"><input type="text" name="loginName" class="bian medium" style="padding-left: 3px;" value="${loginName }"/></td>
													</tr>
													<tr>
														<td height="30">&nbsp;</td>
														<td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
														<td align="left"><input type="password" name="password" class="bian medium" style="padding-left: 3px;" value=""/></td>
													</tr>
													<tr>
														<td height="30">&nbsp;</td>
														<td align="right">记住账号：</td>
														<td align="left"><input type="checkbox" name="rememberLoginName" class="bian" value="true" <c:if test="${ rememberLoginName}">checked="checked"</c:if>/></td>
													</tr>
													<tr>
														<td height="30">&nbsp;</td>
														<td colspan="2" class="error">
															<c:if test="${not empty errorInfos }">
																<c:forEach items="${errorInfos }" var="errorInfo" varStatus="status">
																	<c:if test="${!status.first }"><br/></c:if>
																	* ${errorInfo }
																</c:forEach>
															</c:if>
														</td>
													</tr>
													<tr>
														<td height="30">&nbsp;</td>
														<td colspan="2" align="center">
															<a href="#" id="submitBtn" onClick="login()"><img src="${images}/dl-dl.gif" width="69" height="22" border="0"></a>
															&nbsp;
															<a href="#" onClick="reset()"><img src="${images}/dl-cs.gif" width="69" height="22" border="0"></a>
														</td>
													</tr>
													<tr>
														<td height="25">&nbsp;</td>
														<td colspan="2" align="center"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center" valign="top">
												<img src="${images}/login-di1.gif" width="516" height="45">
											</td>
										</tr>
									</table>
								</td>
								<td align="left" valign="middle">&nbsp;</td>
							</tr>
							<tr>
								<td width="50%" height="190" align="right" valign="top" >
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center" class="dl_dx">&nbsp;</td>
										</tr>
									</table>
								</td>
								<td width="50%" align="left" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center" class="dl_dx">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center">&nbsp;</td>
								<td colspan="3" align="center">
									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="572" height="1" bgcolor="#ffffff"></td>
										</tr>
										<tr>
											<td align="center">林明清 2013 版权所有</td>
										</tr>
									</table>
								</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script src="${script }/jquery.min.js" type="text/javascript"></script>
</html>