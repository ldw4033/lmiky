<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/jdp/form/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="${css}/view.css" />
	<script type="text/javascript">
		function addCity() {
			$("[name='parentId']").val("${pojo.id}");
			actionForm('<c:url value="/city/load.shtml"/>', 'id');
		}
	</script>
</head>
<body>
	<%@ include file="/jdp/form/messages.jsp" %>
		<form:form id="mainForm" action="/province/save.shtml" method="post" commandName="pojo">
			<%@ include file="/jdp/form/field.jsp" %>
			<input type="hidden" name="parentId" value="${param.parentId}"/>
			<input type="hidden" name="countryId" value="${param.parentId}"/>
			<table class="full" height="30" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" class="listTitle">
						&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />&nbsp;省份管理
					</td>
				</tr>
				<tr>
					<td align="center" valign="top">&nbsp;</td>
				</tr>
			</table>
			<table class="full" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="bottom" class="listMenu">
						<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<c:if test="${openMode == 'edit'}">
										<lauthority:checkAuthority authorityCode="jdp_area_manage">
											<td align="center">
												<table>
													<tr>
														<td align="center" class="btn_menu btnClass_td" onClick="addCity()">添加子地市</td>
													</tr>
												</table>
											</td>
											<td align="center">
												<table>
													<tr>
														<td align="center" class="btn_menu btnClass_td" onClick="deletePojo('<c:url value="/province/delete.shtml"/>')">删除</td>
													</tr>
												</table>
											</td>
										</lauthority:checkAuthority>
									</c:if>
									<td align="center">
										<jsp:include page="/jdp/include/favoriteMenu.jsp">
											<jsp:param value="jdp_area_load" name="authorityCode"/>
										</jsp:include>
									</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="top">&nbsp;</td>
				</tr>
			</table>
			<table class="table-form full" border="0" cellspacing="0" cellpadding="0">
				<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
			</table>
		</form:form>
</body>
</html>