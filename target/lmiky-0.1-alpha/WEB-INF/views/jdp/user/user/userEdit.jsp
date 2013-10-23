<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.lmiky.jdp.user.pojo.User" %>
<script type="text/javascript">
	<!--
		function beforeSubmitForm() {
			if($("input[name='name']").val() == "") {
				alert("姓名不能为空!");
				return false;
			}
			return true;
		}
	//-->
</script>

<tr>
	<th width="17%" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</th>
	<td>
		<input name="name" type="text" class="large bian" value="${pojo.name}"/>
	</td>
	<th width="17%" align="right" class="bg02">
		<label>登陆账号<span class="req">*</span></label>
	</th>
	<td>
		<input name="loginName" type="text" class="large bian" value="${pojo.loginName}"/>
	</td>
</tr>
<c:if test="${openMode == 'create'}">
	<tr>
		<th align="right" class="bg02">
			<label>密码<span class="req">*</span></label>
		</th>
		<td>
			<input name="password" type="password" class="large bian" value=""/>
		</td>
		<th align="right" class="bg02">
			<label>确认密码<span class="req">*</span></label>
		</th>
		<td>
			<input name="confirmPassword" type="password" class="large bian" value=""/>
		</td>
	</tr>
</c:if>
<tr>
	<th align="right" class="bg02">
		<label>联系电话</label>
	</th>
	<td>
		<input name="phone" type="text" class="large bian" value="${pojo.phone}"/>
	</td>
	<th align="right" class="bg02">
		<label>邮箱</label>
	</th>
	<td>
		<input name="email" type="text" class="large bian" value="${pojo.email}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>是否可用</label>
	</th>
	<td colspan="3">
		<form:radiobutton path="valid" cssClass="bian" id="yesValid" value="<%=User.VALID_YES %>"/><label for="yesValid" class="label">是</label>  
		&nbsp;
    	<form:radiobutton path="valid" cssClass="bian" id="noValid" value="<%=User.VALID_NO %>"/><label for="noValid" class="label">否</label>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>说明</label>
	</th>
	<td colspan="3">
		<textarea name="description" maxlength="512" class="full bian" rows="5">${pojo.description}</textarea>
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>
