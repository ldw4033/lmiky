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
	<th width="20%" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</th>
	<td>
		<input name="name" type="text" class="large bian" value="${pojo.name}"/>
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>
