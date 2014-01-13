<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/jdp/form/header.jsp" %>
	<link rel="stylesheet" href="${scriptPlugin }/ckeditor/ckeditor.js" />
</head>
<body>
	<%@ include file="/jdp/form/messages.jsp" %>
	<form id="mainForm" action="<c:url value="/cms/resource/save.shtml"/>" method="post">
		<%@ include file="/jdp/form/field.jsp" %>
		<input type="hidden" name="directoryId" value="${directory.id }"/>
		<table class="table-form full" >
	       	<tr>
	       		<td colspan="6" class="bg01 title">
	       			&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />
	       			<c:choose>
	       				<c:when test="${param[httpParamOpenMode] == createOpenMode }">添加文章</c:when>
	       				<c:when test="${param[httpParamOpenMode] == editOpenMode }">修改文章</c:when>
	       				<c:otherwise>文章信息</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
			<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
		</table>
	</form>
</body>
</html>