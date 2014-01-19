<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<tr>
	<th width="70" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</th>
	<td>
		<input name="name" type="text" class="larger bian" value="${pojo.name}"/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/role/list.shtml?modulePath=${modulePath }')"/>
		&nbsp;&nbsp;
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>
