<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>
<%@ page import="com.lmiky.jdp.area.controller.AreaController" %>
<script type="text/javascript">
	<!--
		function beforeSubmitForm() {
			if($("input[name='name']").val() == "") {
				alert("名称不能为空!");
				return false;
			}
			return true;
		}
	
		$(document).ready(function() {
			if('${flag}' == 'refresh') {
				<c:choose>
					<c:when test="${openMode == 'edit'}">
						parent.reAsyncChildNodes('${param.parentId}', '<%=AreaController.AREA_TYPE_COUNTRY%>${pojo.id}');
					</c:when>
					<c:otherwise>
					parent.reAsyncChildNodes('${param.parentId}');
					</c:otherwise>
				</c:choose>
			}
		});
	//-->
</script>
<tr>
	<th width="20%" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</th>
	<td>
		<input name="name" type="text" class="large bian" value="${pojo.name}"/>
	</td>
</tr>
<tr>
	<td colspan="4" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="<c:choose><c:when test="${openMode == 'edit'}">修改</c:when><c:otherwise>添加</c:otherwise></c:choose>" />
	</td>
</tr>