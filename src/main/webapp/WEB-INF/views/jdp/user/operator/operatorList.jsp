<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lmiky.jdp.user.pojo.User" %>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="yesValid" value="<%=User.VALID_YES %>"/>
<c:set var="noValid" value="<%=User.VALID_NO %>"/>
<html>
	<head>
		<%@ include file="/jdp/common/header.jsp"%>
		<%@ include file="/jdp/view/header.jsp"%>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/user/list.shtml"/>" method="post">
			<input type="hidden" name="modulePath" value="${modulePath }"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" align="center" valign="top">
						<table width="98%" height="30" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="listTitle">
									&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />&nbsp;用户管理
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">
						<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="filterTd">
									<span style="width: 50px;" class="labelSpan_right">姓名：</span>
									<lhtml:propertyFilter inputType="text" compareType="LIKE" propertyName="name" styleClass="bian medium"/>
									<span style="width: 70px;" class="labelSpan_right">登陆账号：</span>
									<lhtml:propertyFilter inputType="text" compareType="LIKE" propertyName="loginName"  styleClass="bian medium"/>
									<span style="width: 50px;" class="labelSpan_right">角色：</span>
									<lhtml:propertyFilter inputType="select" compareType="EQ" propertyName="roles.id"  styleClass="bian medium">
			            				<option value="">请选择</option>
			            				<c:if test="${not empty roles }">
			            					<c:forEach items="${roles }" var="role">
			            						<option value="${role.id }">${role.name }</option>
			            					</c:forEach>
			            				</c:if>
			            			</lhtml:propertyFilter>
			            			&nbsp;
			            			是否有效：
			            			<lhtml:propertyFilter inputType="radio" compareType="EQ" propertyName="valid"  styleClass="bian" label="全部" labelStyle="width: 30px;"  labelClass="label" value=""/>&nbsp;
									<lhtml:propertyFilter inputType="radio" compareType="EQ" propertyName="valid"  styleClass="bian" label="是" labelStyle="width: 30px;" labelClass="label" value="<%=String.valueOf(User.VALID_YES) %>"/>&nbsp;
			            			<lhtml:propertyFilter inputType="radio" compareType="EQ" propertyName="valid"  styleClass="bian" label="否" labelStyle="width: 30px;" labelClass="label" value="<%=String.valueOf(User.VALID_NO) %>" />
			            			&nbsp;
									<input class="btnClass" type="submit" value="查询" style="cursor: pointer;"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left" valign="bottom" class="listMenu">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<lauthority:checkAuthority authorityCode="jdp_user_operator_add">
												<td align="center">
													<table>
														<tr>
															<td align="center" class="btn_menu btnClass_td" onClick="redirectPage('<c:url value="/operator/load.shtml?${httpParamOpenMode }=${createOpenMode }&modulePath=${modulePath }"/>', 800, 600)">添加</td>
														</tr>
													</table>
												</td>
											</lauthority:checkAuthority>
											<lauthority:checkAuthority authorityCode="jdp_user_operator_delete">
												<td align="center">
													<table>
														<tr>
															<td align="center" class="btn_menu btnClass_td" onClick="batchDelete('<c:url value="/operator/batchDelete.shtml"/>')">批量删除</td>
														</tr>
													</table>
												</td>
											</lauthority:checkAuthority>
											<td align="center">
												<jsp:include page="/jdp/include/favoriteMenu.jsp">
													<jsp:param value="jdp_user_operator_load" name="authorityCode"/>
												</jsp:include>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">
						<table class="listContent"  width="98%" cellspacing="0" cellpadding="0" rules="cols" border="0">
							<tbody>
								<tr>
									<th class="no">&nbsp;</th>
									<th class="sortable sorted_name"><a href="javascript:pageSort('name')">姓名</a></th>
									<th>登陆账号</th>
									<th>联系电话</th>
									<th class="sortable sorted_createTime"><a href="javascript:pageSort('createTime')">添加时间</a></th>
									<th>是否有效</th>
									<th>操作</th>
									<lauthority:checkAuthority authorityCode="jdp_user_operator_delete">
										<th class="simpleCheckbox">
											<input type="checkbox" name="batctSelectDelete"  id="batctSelectDelete" value="" onclick="batchSelectDelete()"/>
										</th>
									</lauthority:checkAuthority>
								</tr>
								<c:forEach var="item" items="${page.items}" varStatus="status">
									<c:set var="rowClass" value="odd"/>
									<c:if test="${(status.index % 2) == 0}">
										<c:set var="rowClass" value="even"/>
									</c:if>
									<tr class="${rowClass } hover">
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
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top">
						<%@ include file="/jdp/view/page.jsp" %>
					</td>
				</tr>
				<tr>
					<td height="13"></td>
				</tr>
			</table>
			<%@ include file="/jdp/view/sort.jsp" %>
		</form>
	</body>
</html>