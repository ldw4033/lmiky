<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty validateErrorInfos}">
	<c:forEach var="validateErrorInfo" items="${validateErrorInfos}">
		<div class="message error">
			<img src="${images}/iconWarning.gif" class="icon" />
			<script type="text/javascript">
				$(document).ready(function() {
					$("input[name='${validateErrorInfo.fieldName}']").removeClass("bian");
					$("input[name='${validateErrorInfo.fieldName}']").addClass("bian1");
				});
			</script>
			<label class="error"><c:out value="${validateErrorInfo.errorDesc}" /></label>
		</div>
	</c:forEach>
</c:if>
<%@ include file="/jdp/common/messageLabel.jsp"%>