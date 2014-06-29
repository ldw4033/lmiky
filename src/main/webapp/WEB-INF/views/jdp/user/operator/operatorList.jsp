<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp"%>
<%@ page import="com.lmiky.jdp.user.pojo.User"%>
<c:set var="yesValid" value="<%=User.VALID_YES%>" />
<c:set var="noValid" value="<%=User.VALID_NO%>" />
<!DOCTYPE html>
<html>
<head>
<title>${subMenu.label }</title>
<%@ include file="/jdp/common/header.jsp"%>
<%@ include file="/jdp/view/header.jsp"%>
<style>
</style>
</head>
<body>
	<%@ include file="/jdp/menu/topMenu.jsp"%>
	<div
		style="width: 1200px; margin-left: auto; margin-right: auto; margin-top: 72px; background-color: #444444;">
		<%@ include file="/jdp/menu/leftMenu.jsp"%>
		<div id="content">
			<%@ include file="/jdp/menu/location.jsp"%>
			<div class="container-fluid">
				<div class="row-fluid">
				<table class="listContent table table-bordered table-striped with-check table-hover">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th class="sortable sorted_name"><a
								href="javascript:pageSort('name')">姓名</a></th>
							<th>登陆账号</th>
							<th>联系电话</th>
							<th class="sortable sorted_createTime"><a
								href="javascript:pageSort('createTime')">添加时间</a></th>
							<th>是否有效</th>
							<th>操作</th>
							<c:set var="colCount" value="7"/>
							<lauthority:checkAuthority
								authorityCode="jdp_user_operator_delete">
								<c:set var="colCount" value="8"/>
								<th class="simpleCheckbox"><input type="checkbox"
									name="batctSelectDelete" id="batctSelectDelete" value=""
									onclick="batchSelectDelete()" /></th>
							</lauthority:checkAuthority>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${page.items}" varStatus="status">
									<tr>
										<td>${status.count + (page.currentPage - 1) * page.pageSize}</td>
										<td>${item.name}</td>
										<td>${item.loginName}</td>
										<td>${item.phone}</td>
										<td>
											<fmt:formatDate value="${item.createTime}" pattern="${defaultDateTimeFormater }"/>
										</td>
										<td>
											<c:choose>
												<c:when test="${item.valid == yesValid }">是</c:when>
												<c:otherwise>否</c:otherwise>
											</c:choose>
										</td>
										<td>
											<lauthority:checkAuthority authorityCode="jdp_user_operator_load">
												<a href="javascript:void(0)" class="td_2"
														onclick="redirectPage('<c:url value="/operator/load.shtml?id=${item.id}&${httpParamOpenMode }=${readOpenMode }"/>&modulePath=${modulePath }', 800, 600)">
													查看
												</a>
											</lauthority:checkAuthority>
											<lauthority:checkAuthority authorityCode="jdp_user_operator_modify">
												&nbsp;
												<a href="javascript:void(0)" class="td_2"
														onclick="redirectPage('<c:url value="/operator/load.shtml?id=${item.id}&${httpParamOpenMode }=${editOpenMode }&modulePath=${modulePath }"/>', 800, 600)">
													修改
												</a>
											</lauthority:checkAuthority>
											<lauthority:checkAuthority authorityCode="jdp_user_operator_delete">
												&nbsp;
												<a href="javascript:void(0)" class="td_2"
														onclick="deletePojo('<c:url value="/operator/delete.shtml?id=${item.id}"/>')">
													删除
												</a>
											</lauthority:checkAuthority>
										</td>
										<lauthority:checkAuthority authorityCode="jdp_user_operator_delete">
											<td>
												<input type="checkbox" name="batchDeleteId" value="${item.id}" />
											</td>
										</lauthority:checkAuthority>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="${colCount }" style="background-color: #ffffff">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="${colCount }" style="background-color: #ffffff"><%@ include file="/jdp/view/page.jsp" %></td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/jdp/common/scripts.jsp"%>
	<%@ include file="/jdp/view/scripts.jsp"%>
</body>
</html>