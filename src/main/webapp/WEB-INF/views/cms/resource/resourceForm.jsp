<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/jdp/form/header.jsp" %>
		<link rel="stylesheet" href="${scriptPlugin }/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="${scriptPlugin }/kindeditor/kindeditor-min.js" ></script>
		<script charset="utf-8" src="${scriptPlugin }/kindeditor/lang/zh_CN.js"></script>
		<script >
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor.isEmpty());
				});
				K('input[name=getText]').click(function(e) {
					alert(editor.text());
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor.selectedHtml());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
				K('input[name=setText]').click(function(e) {
					editor.text('<h3>Hello KindEditor</h3>');
				});
				K('input[name=insertHtml]').click(function(e) {
					editor.insertHtml('<strong>插入HTML</strong>');
				});
				K('input[name=appendHtml]').click(function(e) {
					editor.appendHtml('<strong>添加HTML</strong>');
				});
				K('input[name=clear]').click(function(e) {
					editor.html('');
				});
			});
		</script>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/cms/resource/save.shtml"/>" method="post">
			<%@ include file="/jdp/form/field.jsp" %>
			<input type="hidden" name="directoryId" value="${directory.id }"/>
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