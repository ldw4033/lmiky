<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jdp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${css}/view.css" />
<script src="${script }/view.js" type="text/javascript"></script>
<%@ include file="/jdp/common/messageAlter.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var hoverColor = $("table.listContent th").css("background-color");	//跟表头同一样式
		var bgColorBak = "#ffffff";
		$("table.listContent tr.hover").hover(
			function(){
				bgColorBak = $(this).css("background-color");
				$(this).css("background-color", hoverColor);
			},
			function(){
				$(this).css("background-color", bgColorBak);
			}
		);
	});
</script>