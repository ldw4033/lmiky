<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/htmlDoctype.jsp"%>
<%@ include file="/jdp/common/common.jsp"%>
<html>
<head>
	<%@ include file="/jdp/common/header.jsp"%>
	<%@ include file="/jdp/form/header.jsp" %>
</head>
<body>
	<div id="content">
		<div class="container-fluid">
			<div class="row-fluid">
				<%@ include file="/jdp/form/messages.jsp" %>
				<form id="mainForm" action="<c:url value="/operator/modifyPassword.shtml"/>" method="post">
					<input type="hidden" name="modulePath" value="${param.modulePath }"/>
					<div class="span12">
						<div class="widget-box form-table">
							<div class="nopadding">
								<div class="control-group">
									<label class="mini-control-label">旧密码 <span class="req">*</span></label>
									<div class="controls">
										<input name="oldPassword" type="password" value="" />
									</div>
								</div>
								<div class="control-group">
									<label class="mini-control-label">新密码 <span class="req">*</span></label>
									<div class="controls">
										<input name="password" type="password" value="" />
									</div>
								</div>
								<div class="control-group">
									<label class="mini-control-label">确认密码 <span class="req">*</span></label>
									<div class="controls">
										<input name="confirmPassword" type="password" value="" />
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary">提交</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>