<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<html>
<head>
	<%@ include file="/jdp/form/header.jsp" %>
	<script src="${script }/multipleSelect.js" type="text/javascript"></script>
	<script type="text/javascript">
			<c:choose>
				<c:when test="${empty authorizedList}">
					var userIndex = 0;
				</c:when>
				<c:otherwise>
					var userIndex = ${fn:length(authorizedList)};
				</c:otherwise>
			</c:choose>
			
			$(document).ready(function(){
				initMultipleSelect('groupSpan', 'user_', 'selectedOperators', userIndex);
			}); 
			</script>
</head>
<body>
	<%@ include file="/jdp/common/messageAlter.jsp" %>
	<form id="mainForm" action="<c:url value="/authority/authorize.shtml"/>" method="post">
			<input type="hidden" name="moduleType" value="${param.moduleType }"/>
			<input type="hidden" name="modulePath" value="${param.modulePath }"/>
			<span id="groupSpan" style="display: none;">
				<c:forEach items="${authorizedList }" var="authorizedUser" varStatus="status">
					<input type="hidden" id="user_${authorizedUser.id }" name="selectedOperators" value="${authorizedUser.id }"/>
				</c:forEach>
			</span>
			<table style="width: 100%; height: 100%;">
				<tr>
					<td valign="top" align="left">
						<div style="float: left;">
							<div style="line-height: 25px;">未选中角色：</div>
							<div>
								<select id="unselected" multiple="multiple" style="height: 450px; width: 250px;" class="bian">
									<c:forEach items="${unauthorizedList }" var="unauthorizedUser">
										<option value="${unauthorizedUser.id }">${unauthorizedUser.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div style="float: left; width: 50px; text-align: center;">
							<div style="height: 150px;"></div>
							<div>&nbsp;<input type="button" id="addSelect" value="-->" class="btnClass2" style="width: 30px;"/>&nbsp;</div>
							<div style="height: 20px;"></div>
							<div>&nbsp;<input type="button" id="addAllSelect" value="=>" class="btnClass2" style="width: 30px;"/>&nbsp;</div>
							<div style="height: 20px;"></div>
							<div>&nbsp;<input type="button" id="removeSelect" value="<--" class="btnClass2" style="width: 30px;"/>&nbsp;</div>
							<div style="height: 20px;"></div>
							<div>&nbsp;<input type="button" id="removeAllSelect" value="<=" class="btnClass2" style="width: 30px;"/>&nbsp;</div>
						</div>
						<div style="float: left;">
							<div style="line-height: 25px;">已选中角色：</div>
							<div>
								<select id="selected" multiple="multiple" style="height: 450px; width: 250px;" class="bian">
									<c:forEach items="${authorizedList }" var="authorizedUser">
										<option value="${authorizedUser.id }">${authorizedUser.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</td>
				</tr>
					<tr>
						<td align="center" height="30">
							<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
						</td>
					</tr>
			</table>
		</form>
	</body>
</html>