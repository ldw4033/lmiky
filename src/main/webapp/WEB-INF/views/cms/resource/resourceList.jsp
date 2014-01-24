<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lmiky.cms.resource.pojo.CmsResource,com.lmiky.cms.resource.controller.ResourceController" %>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="state_create" value="<%=CmsResource.STATE_CREATE %>"/>
<c:set var="state_publish" value="<%=CmsResource.STATE_PUBLISH %>"/>
<c:set var="state_unpublish" value="<%=CmsResource.STATE_UNPUBLISH %>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/jdp/common/header.jsp"%>
		<%@ include file="/jdp/view/header.jsp"%>
	</head>
	<body>
		<form id="mainForm" action="<c:url value="/cms/resource/list.shtml"/>" method="post">
			<input type="hidden" name="modulePath" value="${modulePath }"/>
			<input type="hidden" name="directoryId" value="${directory.id }"/>
			<input type="hidden" name="opeFrom" value="<%=ResourceController.PUBLISH_OPE_FORM_VIEW%>"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" align="center" valign="top">
						<table width="98%" height="30" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="listTitle">
									&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />${directory.name }&nbsp;文章管理
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
								<td>
									标题：<lhtml:propertyFilter inputType="text" compareType="LIKE" propertyName="title" styleClass="bian medium"/>
									&nbsp;
									<input class="btnClass" type="submit" value="查询" />
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
											<lauthority:checkAuthority authorityCode="cms_resource_add">
												<td align="center">
														<table>
															<tr>
																<td align="center" class="btn_menu btnClass_td" onClick="redirectPage('<c:url value="/cms/resource/load.shtml?${httpParamOpenMode }=${createOpenMode }&modulePath=${modulePath }&directoryId=${directory.id }"/>', 1000, 600)">添加</td>
															</tr>
														</table>
												</td>
											</lauthority:checkAuthority>
											<lauthority:checkAuthority authorityCode="cms_resource_delete">
												<td align="center">
													<table>
														<tr>
															<td align="center" class="btn_menu btnClass_td" onClick="batchDelete('<c:url value="/cms/resource/batchDelete.shtml"/>')">批量删除</td>
														</tr>
													</table>
												</td>
											</lauthority:checkAuthority>
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
									<th class="sortable sorted_title"><a href="javascript:pageSort('title')">标题</a></th>
									<th class="sortable sorted_salePrice"><a href="javascript:pageSort('salePrice')">作者</a></th>
									<th>创建时间</th>
									<th>发布时间</th>
									<th>操作</th>
									<lauthority:checkAuthority authorityCode="cms_resource_delete">
										<th>
											<input type="checkbox" name="batctSelectDelete"  id="batctSelectDelete" class="bian" value="" onclick="batchSelectDelete()"/>
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
										<td>${item.title}</td>
										<td>${item.author}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="${defaultDateTimeFormater }"/></td>
										<td><fmt:formatDate value="${item.pubTime}" pattern="${defaultDateTimeFormater }"/></td>
										<td>
											<lauthority:checkAuthority authorityCode="cms_resource_load">
												<a href="javascript:void(0)" class="td_2" onclick="redirectPage('<c:url value="/cms/resource/load.shtml?id=${item.id}&${httpParamOpenMode }=${readOpenMode }"/>&modulePath=${modulePath }&directoryId=${directory.id }', 1000, 600)">
													查看
												</a>
											</lauthority:checkAuthority>
											<lauthority:checkAuthority authorityCode="cms_resource_modify">
												&nbsp;
												<a href="javascript:void(0)" class="td_2" onclick="redirectPage('<c:url value="/cms/resource/load.shtml?id=${item.id}&${httpParamOpenMode }=${editOpenMode }"/>&modulePath=${modulePath }&directoryId=${directory.id }', 1000, 600)">
													修改
												</a>
											</lauthority:checkAuthority>
											<c:choose>
												<c:when test="${item.state == state_create || item.state == state_unpublish }">
													<lauthority:checkAuthority authorityCode="cms_resource_publish">
														<a href="javascript:void(0)" class="td_2" onclick="executeAction('<c:url value="/cms/resource/publish.shtml"/>?id=${item.id}&directoryId=${directory.id }')">
															&nbsp;&nbsp;发布&nbsp;&nbsp;
														</a>
													</lauthority:checkAuthority>
												</c:when>
												<c:when test="${item.state == state_publish }">
													<lauthority:checkAuthority authorityCode="cms_resource_publish">
														<a href="javascript:void(0)" class="td_2" onclick="executeAction('<c:url value="/cms/resource/unpublish.shtml"/>?id=${item.id}&directoryId=${directory.id }')">
															取消发布
														</a>
													</lauthority:checkAuthority>
												</c:when>
											</c:choose>
											<lauthority:checkAuthority authorityCode="cms_resource_delete">
												&nbsp;
												<a href="javascript:void(0)" onclick="deletePojo('<c:url value="/cms/resource/delete.shtml?id=${item.id}&directoryId=${directory.id }"/>')" class="td_2">
													删除
												</a>
											</lauthority:checkAuthority>
										</td>
										<lauthority:checkAuthority authorityCode="cms_resource_delete">
											<td>
												<input type="checkbox" name="batchDeleteId" class="bian" value="${item.id}" />
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