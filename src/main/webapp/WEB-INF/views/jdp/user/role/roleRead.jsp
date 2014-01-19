<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tr>
	<th width="70" align="right" class="bg02">
		<label>名称</label>
	</th>
	<td class="larger">
		${pojo.name}
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/role/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>
