<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<tr>
	<th align="right" class="bg02" width="17%">
		<label>标题</label>
	</th>
	<td colspan="3">
		${pojo.title}
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>副标题</label>
	</th>
	<td colspan="3">
		${pojo.subtitle}
	</td>
</tr>
<tr>
	<th align="right" class="bg02" width="17%">
		<label>作者</label>
	</th>
	<td width="33%">
		${pojo.author}
	</td>
	<th align="right" class="bg02" width="17%">
		<label>来源</label>
	</th>
	<td width="33%">
		${pojo.source}
	</td>
</tr>
<tr>
	<th align="right" class="bg02" width="17%">
		<label>创建时间</label>
	</th>
	<td>
		<fmt:formatDate value="${pojo.createTime}" pattern="${defaultDateTimeFormater }"/>
	</td>
	<th align="right" class="bg02" width="17%">
		<label>发布时间</label>
	</th>
	<td>
		<fmt:formatDate value="${pojo.pubTime}" pattern="${defaultDateTimeFormater }"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>所属栏目</label>
	</th>
	<td colspan="3">
		${pojo.directory.name}
	</td>
</tr>
<tr>
	<td colspan="4" valign="top" height="300">
		${pojo.content}
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/cms/resource/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>