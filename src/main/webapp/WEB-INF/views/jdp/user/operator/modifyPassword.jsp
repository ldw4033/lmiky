<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<html>
<head>
	<%@ include file="/jdp/form/header.jsp" %>
</head>
<body style="height: 98%;">
	<%@ include file="/jdp/form/messages.jsp" %>
	<form id="mainForm" action="<c:url value="/operator/modifyPassword.shtml"/>" method="post">
		<input type="hidden" name="modulePath" value="${param.modulePath }"/>
		<table class="table-form full" style="width: 100%;">
	       	<tr>
	       		<td colspan="2" class="bg01 title">
	       			修改密码
	       		</td>
	       	</tr>
			<tr>
				<td width="70" align="right" class="bg02">
					<label>旧密码<span class="req">*</span></label>
				</td>
				<td>
					<input name="oldPassword" type="password" class="normal bian" value=""/>
				</td>
			</tr>
			<tr>
				<td align="right" class="bg02">
					<label>新密码<span class="req">*</span></label>
				</td>
				<td>
					<input name="password" type="password" class="normal bian" value=""/>
				</td>
			</tr>
			<tr>
				<td align="right" class="bg02">
					<label>确认密码<span class="req">*</span></label>
				</td>
				<td>
					<input name="confirmPassword" type="password" class="normal bian" value=""/>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>