<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/taglibs.jsp" %>

<c:set var="pageSliderBegein" value="<%=com.lmiky.jdp.web.page.model.Page.SLIDER_BEGIN %>"/>
<c:set var="pageSliderCenter" value="<%=com.lmiky.jdp.web.page.model.Page.SLIDER_CENTER %>"/>
<c:set var="pageSliderEnd" value="<%=com.lmiky.jdp.web.page.model.Page.SLIDER_END %>"/>

<div id="pager">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="30" align="right">
				<span>共 <span class="STYLE2">${page.itemCount}</span> 条记录</span> 
				<c:choose>
					<c:when test="${page.pageCount <= pageSliderBegein + pageSliderCenter + pageSliderEnd}">
						<c:forEach var="pageIndex"  begin="1" end="${ page.pageCount}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
					</c:when>
					<c:when test="${page.currentPage <= pageSliderBegein + 2}">
						<c:forEach var="pageIndex"  begin="1" end="${ page.currentPage + 1}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
						<span class="STYLE2">..</span>
						<c:forEach var="pageIndex"  begin="${page.pageCount - pageSliderEnd + 1 }" end="${ page.pageCount}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
					</c:when>
					<c:when test="${page.currentPage >= (page.pageCount - pageSliderEnd - 1)}">
						<c:forEach var="pageIndex"  begin="1" end="${ pageSliderBegein}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
						<span class="STYLE2">..</span>
						<c:forEach var="pageIndex"  begin="${page.currentPage - 1 }" end="${ page.pageCount}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="pageIndex"  begin="1" end="${ pageSliderBegein}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
						<span class="STYLE2">..</span>
						<c:forEach var="pageIndex"  begin="${page.currentPage - 1}" end="${page.currentPage + 1 }" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
						<span class="STYLE2">..</span>
						<c:forEach var="pageIndex"  begin="${page.pageCount - pageSliderEnd + 1 }" end="${ page.pageCount}" varStatus="status">
							<a href="javascript: void(0)"  class="btnClass2 
							<c:choose>
								<c:when test="${page.currentPage ==  pageIndex}">selectedPageIndex</c:when>
								<c:otherwise> unselectedPageIndex</c:otherwise>
							</c:choose>
							" onclick="turnPage(${pageIndex })">${pageIndex }</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				转到第 <input type="text" style="height: 18px; line-height: 12px;" class="mini bian" name="<%=com.lmiky.jdp.constants.Constants.HTTP_PARAM_PAGE_CURRENTPAGE %>"  id="<%=com.lmiky.jdp.constants.Constants.HTTP_PARAM_PAGE_CURRENTPAGE %>" value="${page.currentPage}"/> 页 
				<input type="hidden" name="<%=com.lmiky.jdp.constants.Constants.HTTP_PARAM_PAGE_ACTION%>"  id="<%=com.lmiky.jdp.constants.Constants.HTTP_PARAM_PAGE_ACTION%>" value="${page.action}"/>
				<img src="${images}/go.gif" width="29" height="15" align="absmiddle" onclick="document.forms.mainForm.submit()" style="cursor: pointer;"/></span>
			</td>
			<td width="1%">&nbsp;</td>
		</tr>
	</table>
</div>
<script src="${script}/page.js"/>" type="text/javascript"></script>