<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jdp/common/taglibs.jsp" %>
<table>
	<tr>
		<c:set var="isFavorited" value="${false}" />
		<favorite:inMyMenu menuId="${param.authorityCode }">
			<c:set var="isFavorited" value="${true}" />
			<td align="center" class="btn_menu btnClass_td" onClick="removeMyFavoriteMenu('${param.authorityCode }', this)">取消收藏</td>
		</favorite:inMyMenu>
		<c:if test="${!isFavorited }">
			<td align="center" class="btn_menu btnClass_td" onClick="addMyFavoriteMenu('${param.authorityCode }', this)">添加到收藏夹</td>
		</c:if>
	</tr>
</table>