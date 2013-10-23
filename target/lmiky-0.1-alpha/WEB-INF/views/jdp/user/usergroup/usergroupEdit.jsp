<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>
<script type="text/javascript">
<c:choose>
	<c:when test="${empty groupUsers}">
		var userIndex = 0;
	</c:when>
	<c:otherwise>
		var userIndex = ${fn:length(groupUsers)};
	</c:otherwise>
</c:choose>

	$(document).ready(function(){
		initMultipleSelect('groupSpan', 'user_', 'selectedUsers', userIndex);
	}); 
</script>

<span id="groupSpan" style="display: none;">
	<c:forEach items="${groupUsers }" var="groupUser" varStatus="status">
		<input type="hidden" id="user_${groupUser.id }" name="selectedUsers" value="${groupUser.id }"/>
	</c:forEach>
</span>
<tr>
	<td width="17%" align="right" class="bg02">
		<label>名称<span class="req">*</span></label>
	</td>
	<td>
		<input name="name" type="text" class="arger bian" value="${pojo.name}"/>
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>用户</label>
	</td>
	<td>
		<div style="float: left;">
			<div style="line-height: 25px;">未选中用户</div>
			<div>
				<select id="unselected" multiple="multiple" class="bian" style="height: 400px; width: 250px;">
					<c:forEach items="${noGroupUsers }" var="groupUser">
						<option value="${groupUser.id }">${groupUser.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div style="float: left; width: 50px; text-align: center;">
			<div style="height: 120px;"></div>
			<div>&nbsp;<input type="button" id="addSelect" class="btnClass2" style="width: 30px;"
				value="-->"/>&nbsp;</div>
			<div style="height: 20px;"></div>
			<div>&nbsp;<input type="button" id="addAllSelect" class="btnClass2" style="width: 30px;"
				value="=>"/>&nbsp;</div>
			<div style="height: 20px;"></div>
			<div>&nbsp;<input type="button" id="removeSelect" class="btnClass2" style="width: 30px;"
				value="<--"/>&nbsp;</div>
				<div style="height: 20px;"></div>
			<div>&nbsp;<input type="button" id="removeAllSelect" class="btnClass2" style="width: 30px;"
				value="<="/>&nbsp;</div>
		</div>
		<div style="float: left;">
			<div style="line-height: 25px;">已选中用户</div>
			<div>
				<select id="selected" multiple="multiple" class="bian" style="height: 400px; width: 250px;">
					<c:forEach items="${groupUsers }" var="groupUser">
						<option value="${groupUser.id }">${groupUser.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
	</td>
</tr>