<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tr>
	<th align="right" class="bg02">
		<label>标题<span class="req">*</span></label>
	</th>
	<td>
		<input name="title" type="text" maxlength="256" class="full bian" value="${pojo.title}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>副标题</label>
	</th>
	<td>
		<input name="subtitle" type="text" maxlength="512" class="full bian" value="${pojo.subtitle}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>作者 <span class="req">*</span></label>
	</th>
	<td>
		<input name="author" type="text" maxlength="256" class="large bian" value="${pojo.author}"/>
	</td>
</tr>
<tr>
	<th width="17%" align="right" class="bg02">
		<label>内容 <span class="req">*</span></label>
	</th>
	<td>
		<textarea rows="10" class="full" name="content">${pojo.content}</textarea>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>