<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/jdp/form/header.jsp" %>
		<script src="${script }/multipleSelect.js" type="text/javascript"></script>
	</head>
	<body>
		<%@ include file="/jdp/form/messages.jsp" %>
		<!-- form id="mainForm" action="<c:url value="/user/save.shtml"/>" method="post" -->
		<%@ include file="/jdp/form/field.jsp" %>
		<table class="table-form full"  height="98%">
		   	<tr>
		   		<td class="bg01 title">
		   			&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />请选择节点
		   		</td>
		   	</tr>
		   	<tr>
		   		<td height="100%">
		   			&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />请选择节点
		   		</td>
		   	</tr>
		   	<tr>
				<td align="center">
					<input type="button" class="btnClass" style="cursor: pointer;" value="选择" />
				</td>
			</tr>
		</table>
	</body>
</html>