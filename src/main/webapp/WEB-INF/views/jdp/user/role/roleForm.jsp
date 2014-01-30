<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<html>
	<head>
		<%@ include file="/jdp/form/header.jsp" %>
		<script src="${script }/multipleSelect.js" type="text/javascript"></script>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/role/save.shtml"/>" method="post">
			<%@ include file="/jdp/form/field.jsp" %>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top">
						<table width="98%" height="30" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="listTitle">
									&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />&nbsp;
									<c:choose>
						       			<c:when test="${param[httpParamOpenMode] == createOpenMode }">添加角色</c:when>
					       				<c:when test="${param[httpParamOpenMode] == editOpenMode }">修改角色</c:when>
					       				<c:otherwise>角色信息</c:otherwise>
				       				</c:choose>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td align="center" valign="top">
						<%@ include file="/jdp/form/messages.jsp" %>
						<table class="table-form" >
								<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>