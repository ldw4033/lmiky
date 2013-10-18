<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tr>
	<th width="17%" align="right" class="bg02">
		<label>名称</label>
	</th>
	<td>
		${pojo.name}
	</td>
	<th width="17%" align="right" class="bg02">
		<label>登陆账号</label>
	</th>
	<td>
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
		<label>说明</label>
	</th>
	<td colspan="3">
		${pojo.description}
	</td>
</tr>
