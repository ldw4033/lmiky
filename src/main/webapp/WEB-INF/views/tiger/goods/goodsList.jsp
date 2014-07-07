<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/common.jsp"%>
<%@ page import="com.lmiky.jdp.user.pojo.User"%>
<c:set var="yesValid" value="<%=User.VALID_YES%>" />
<c:set var="noValid" value="<%=User.VALID_NO%>" />
<html>
	<head>
		<title>${subMenu.label }</title>
		<%@ include file="/jdp/common/header.jsp"%>
		<%@ include file="/jdp/view/header.jsp"%>
	</head>
	<body>
		<%@ include file="/jdp/page/topMenu.jsp"%>
		<div class="container_content">
			<%@ include file="/jdp/page/leftMenu.jsp"%>
			<div id="content">
				<%@ include file="/jdp/page/location.jsp"%>
				<div class="container-fluid">
					<form id="mainForm" action="<c:url value="/tiger/goods/list.shtml"/>" method="post" class="form-vertical">
					<%@ include file="/jdp/view/field.jsp"%>
					<div class="row-fluid">
						<div class="span12">
							<div class="widget-box border-radius">
								<div class="nopadding control-group">
										<label class="control-label" style="width: 80px;">商品名称：</label>
										<lhtml:propertyFilter inputType="text" compareType="LIKE" propertyName="title" styleClass="medium"/>
										&nbsp;
										<button type="submit" class="btn action-button">查询</button>
								</div>
							</div>						
						</div>
					</div>
					<div class="row-fluid">
					<table class="listContent table table-bordered table-striped with-check table-hover">
						<thead>
							<tr>
								<th class="list_index">&nbsp;</th>
								<th class="sortable sorted_title"><a href="javascript:pageSort('title')">商品名称</a></th>
								<th class="sortable sorted_salePrice"><a href="javascript:pageSort('salePrice')">销售价</a></th>
								<th>市场价</th>
								<th>优惠券折扣</th>
								<th>操作</th>
								<c:set var="colCount" value="6"/>
								<lauthority:checkAuthority authorityCode="tiger_goods_delete">
									<c:set var="colCount" value="7"/>
									<th class="simpleCheckbox">
										<input type="checkbox" name="batctSelectDelete"  id="batctSelectDelete" value="" onclick="batchSelectDelete()"/>
									</th>
								</lauthority:checkAuthority>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${page.items}" varStatus="status">
										<tr>
											<td>${status.count + (page.currentPage - 1) * page.pageSize}</td>
											<td>${item.title}</td>
											<td>${item.salePrice}</td>
											<td>${item.marketPrice}</td>
											<td>${item.couponDiscount}</td>
											<td>
												<lauthority:checkAuthority authorityCode="tiger_goods_load">
													<a href="javascript:void(0)" class="td_2" onclick="redirectPage('<c:url value="/tiger/goods/load.shtml?id=${item.id}&${httpParamOpenMode }=${readOpenMode }"/>&modulePath=${modulePath }', 800, 600)">
														查看
													</a>
												</lauthority:checkAuthority>
												<lauthority:checkAuthority authorityCode="tiger_goods_modify">
													&nbsp;
													<a href="javascript:void(0)" class="td_2" onclick="redirectPage('<c:url value="/tiger/goods/load.shtml?id=${item.id}&${httpParamOpenMode }=${editOpenMode }"/>&modulePath=${modulePath }', 800, 600)">
														修改
													</a>
												</lauthority:checkAuthority>
												<lauthority:checkAuthority authorityCode="tiger_goods_delete">
													&nbsp;
													<a href="javascript:void(0)" onclick="deletePojo('<c:url value="/tiger/goods/delete.shtml?id=${item.id}"/>')" class="td_2">
														删除
													</a>
												</lauthority:checkAuthority>
											</td>
											<lauthority:checkAuthority authorityCode="tiger_goods_delete">
												<td>
													<input type="checkbox" name="batchDeleteId" value="${item.id}" />
												</td>
											</lauthority:checkAuthority>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="${colCount }" style="background-color: #ffffff"><%@ include file="/jdp/view/page.jsp" %></td>
									</tr>
							</tbody>
						</table>
						<%@ include file="/jdp/view/sort.jsp" %>
					</div>
					</form>
				</div>
			</div>
			<%@ include file="/jdp/page/footer.jsp"%>
		</div>
		<%@ include file="/jdp/common/scripts.jsp"%>
		<%@ include file="/jdp/view/scripts.jsp"%>
	</body>
</html>