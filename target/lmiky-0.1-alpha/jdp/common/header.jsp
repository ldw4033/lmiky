<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
	$(document).ready(function() {
		$(".btn_menu").hover(
			function(){
				$(this).addClass("btn_menu_hover");
			},
			function(){
				$(this).removeClass("btn_menu_hover");
			}
		);
	});
</script>