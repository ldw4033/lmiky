<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	<!--
		function changeUrl(url) {
			if(url != '') {
				$("#urlContent").attr("src", "<c:url value="/capture/task/urlContent.shtml"/>?url=" + url);
			}
		}
	//-->
</script>

<tr>
	<th width="100" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</th>
	<td width="500">
		<input name="name" type="text" class="large bian" value="${pojo.name}"/>
	</td>
</tr>
<tr>
	<th align="right" class="bg02">
		<label>抓取地址<span class="req">*</span></label>
	</th>
	<td>
		<input name="name" type="text" class="full bian" value="${pojo.captureUrl}" onblur="changeUrl(this.value)"/>
	</td>
</tr>
<tr>
	<td colspan="2">
		<iframe id="urlContent" src="" style="width: 100%; height: 100%;" frameborder="0" src="" scrolling="no"/></iframe>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/capture/task/list.shtml?modulePath=${modulePath }')"/>
		&nbsp;&nbsp;
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>
