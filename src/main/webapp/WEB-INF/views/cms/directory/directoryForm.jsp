<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<html>
<head>
	<%@ include file="/jdp/form/header.jsp" %>
</head>
<body style="height: 98%">
	<%@ include file="/jdp/form/messages.jsp" %>
	<form id="mainForm" action="<c:url value="/cms/directory/save.shtml"/>" method="post">
		<%@ include file="/jdp/form/field.jsp" %>
		<input type="hidden" name="parentId" value="${parentId }"/>
		<table class="table-form full" >
	       	<tr>
	       		<td colspan="6" class="bg01 title">
	       			&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />
	       			<c:choose>
	       				<c:when test="${param[httpParamOpenMode] == createOpenMode }">添加栏目</c:when>
	       				<c:when test="${param[httpParamOpenMode] == editOpenMode }">修改栏目</c:when>
	       				<c:otherwise>栏目信息</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
			<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
		</table>
	</form>
</body>
</html>