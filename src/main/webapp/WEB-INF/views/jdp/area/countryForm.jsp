<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/htmlDoctype.jsp"%>
<%@ include file="/jdp/common/common.jsp"%>
<html>
<head>
	<%@ include file="/jdp/common/header.jsp"%>
	<%@ include file="/jdp/form/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="${css}/view.css" />
	<script type="text/javascript">
		function addProvince() {
			$("[name='parentId']").val("${pojo.id}");
			actionForm('<c:url value="/province/load.shtml"/>', 'id');
		}
	</script>
</head>
<body class="alert-body">
				<div class="container-fluid">
					<div class="row-fluid">
						<button type="button" class="btn"><i class="icon-plus"></i> 添加</button>
					</div>
					<div class="row-fluid">
						<form:form id="mainForm" action="/country/save.shtml" method="post" commandName="pojo" class="form-horizontal">
							<%@ include file="/jdp/form/field.jsp" %>
							<div class="span12 nomargin">
								<div class="widget-box form-table">
									<div class="widget-title">
										<span class="icon">
											<c:choose>
							       				<c:when test="${param[httpParamOpenMode] == createOpenMode }"><i class="icon-align-justify"></i></c:when>
							       				<c:when test="${param[httpParamOpenMode] == editOpenMode }"><i class="icon-edit"></i></c:when>
							       				<c:otherwise><i class="icon-align-justify"></i></c:otherwise>
							       			</c:choose>
										</span>
										<h5>国家管理</h5>
									</div>
									<div class="nopadding">
											<%@ include file="/jdp/form/messages.jsp" %>
											<jsp:include page="<%=(String)request.getAttribute(com.lmiky.jdp.form.controller.FormController.HTTP_PARAM_FORM_SUBFORM)%>" />
									</div>
								</div>						
							</div>
						</form:form>
					</div>
				</div>
</body>
</html>