<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lmiky.cms.resource.pojo.CmsResource,com.lmiky.cms.resource.controller.ResourceController" %>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="state_create" value="<%=CmsResource.STATE_CREATE %>"/>
<c:set var="state_publish" value="<%=CmsResource.STATE_PUBLISH %>"/>
<c:set var="state_unpublish" value="<%=CmsResource.STATE_UNPUBLISH %>"/>
<tr>
	<th align="right" class="bg02">
		<label>标题<span class="req">*</span></label>
	</th>
	<td colspan="3">
		<input name="title" type="text" maxlength="256" class="full bian" value="${pojo.title}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>副标题</label>
	</th>
	<td colspan="3">
		<input name="subtitle" type="text" maxlength="512" class="full bian" value="${pojo.subtitle}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02" width="17%">
		<label>作者<span class="req">*</span></label>
	</th>
	<td width="33%">
		<input name="author" type="text" maxlength="256" class="full bian" value="${pojo.author}"/>
	</td>
	<th align="right" class="bg02" width="17%">
		<label>来源</label>
	</th>
	<td width="33%">
		<input name="source" type="text" maxlength="256" class="full bian" value="${pojo.source}"/>
	</td>
</tr>
<c:if test="${param[httpParamOpenMode] == editOpenMode }">
	<tr>
		<th align="right" class="bg02">
			<label>创建时间<span class="req">*</span></label>
		</th>
		<td>
			<input name="createTime" type="text" class="full bian" value="<fmt:formatDate value="${pojo.createTime}" pattern="${defaultDateTimeFormater }"/>" onFocus="WdatePicker({readOnly:true, dateFmt:'${defaultDateTimeFormater}'})"/>
		</td>
		<th align="right" class="bg02">
			<label>发布时间</label>
		</th>
		<td>
			<input name="pubTime" type="text" class="full bian" value="<fmt:formatDate value="${pojo.pubTime}" pattern="${defaultDateTimeFormater }"/>" onFocus="WdatePicker({readOnly:true, dateFmt:'${defaultDateTimeFormater}'})"/>
		</td>
	</tr>
</c:if>
<tr>
	<th align="right" class="bg02">
		<label>所属栏目</label>
	</th>
	<td colspan="3">
		<input name="directoryName" id="directoryName" type="text" maxlength="256" class="full bian" value="${directory.name}" readonly="readonly" onclick="selectTree('directoryName', 'directoryId', 'com.lmiky.cms.directory.pojo.CmsDirectory', 300, 500, '请选择目录')"/>
	</td>
</tr>
<tr>
	<td colspan="4">
		<textarea rows="30" name="content" class="full"  id="htmlContent">${pojo.content}</textarea>
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
		&nbsp;&nbsp;
		<c:choose>
			<c:when test="${pojo.state == state_create || pojo.state == state_unpublish }">
				<lauthority:checkAuthority authorityCode="cms_resource_publish">
					<input type="button" class="btnClass" style="cursor: pointer;" value="发布" onclick="actionForm('<c:url value="/cms/resource/publish.shtml"/>', '', false)"/>
				</lauthority:checkAuthority>
			</c:when>
			<c:when test="${pojo.state == state_publish }">
				<lauthority:checkAuthority authorityCode="cms_resource_publish">
					<input type="button" class="btnClass" style="cursor: pointer;" value="取消发布" onclick="actionForm('<c:url value="/cms/resource/unpublish.shtml"/>', '', false)"/>
				</lauthority:checkAuthority>
			</c:when>
		</c:choose>
		&nbsp;&nbsp;
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/cms/resource/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>