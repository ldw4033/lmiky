<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>
<tr>
	<td width="17%" align="right" class="bg02">
		<label>名称</label>
	</td>
	<td>
		${pojo.name}
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>用户</label>
	</td>
	<td>
		<c:forEach items="${groupUsers }" var="groupUser">
			${groupUser.name }&nbsp;
		</c:forEach>
	</td>
</tr>			