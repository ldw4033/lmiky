<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/simpleCommon.jsp" %>
<tr>
	<th width="100" align="right" class="bg02">
		<label>名称</label>
	</th>
	<td class="large">
		${pojo.name}
	</td>
	<th width="100" align="right" class="bg02">
		<label>登陆账号</label>
	</th>
	<td class="large">
		${pojo.loginName}
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>联系电话</label>
	</th>
	<td>
		${pojo.phone}
	</td>
	<th align="right" class="bg02">
		<label>邮箱</label>
	</th>
	<td>
		${pojo.email}
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>创建时间</label>
	</th>
	<td colspan="3">
		<fmt:formatDate value="${pojo.createTime}" pattern="${defaultDateTimeFormater }"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>所属角色</label>
	</th>
	<td colspan="3">
		<c:forEach items="${userRoles }" var="userRole">
			${userRole.name }&nbsp;
		</c:forEach>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>说明</label>
	</th>
	<td colspan="3">
		${pojo.description}
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/user/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>