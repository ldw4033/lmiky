<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lmiky.cms.resource.pojo.CmsResource,com.lmiky.cms.resource.controller.ResourceController" %>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="state_create" value="<%=CmsResource.STATE_CREATE %>"/>
<c:set var="state_publish" value="<%=CmsResource.STATE_PUBLISH %>"/>
<c:set var="state_unpublish" value="<%=CmsResource.STATE_UNPUBLISH %>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/jdp/form/header.jsp" %>
		<link rel="stylesheet" href="${scriptPlugin }/kindeditor/themes/default/default.css" />
		<%@ include file="/jdp/common/date.jsp"%>
		<%@ include file="/jdp/common/htmlEditor.jsp"%>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/cms/resource/save.shtml"/>" method="post">
			<%@ include file="/jdp/form/field.jsp" %>
			<input type="hidden" id="directoryId" name="directoryId" value="${directory.id }"/>
			<input type="hidden" name="opeFrom" value="<%=ResourceController.PUBLISH_OPE_FORM_FORM%>"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top">
						<table width="98%" height="30" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="listTitle">
									&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />&nbsp;
									<c:choose>
					       				<c:when test="${param[httpParamOpenMode] == createOpenMode }">添加文章</c:when>
					       				<c:when test="${param[httpParamOpenMode] == editOpenMode }">修改文章</c:when>
					       				<c:otherwise>文章信息</c:otherwise>
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
						<table class="table-form" width="98%" >
								<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
						</table>
					</td>
				</tr>
			</table>
		</form>	
	</body>
</html>