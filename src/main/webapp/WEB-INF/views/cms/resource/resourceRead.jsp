<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<td colspan="4" valign="top">
		${pojo.content}
	</td>
</tr>