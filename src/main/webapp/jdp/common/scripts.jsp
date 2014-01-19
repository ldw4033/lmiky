<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.lmiky.jdp.system.menu.controller.MenuController"%>
<script src="${script }/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${script }/common.js" type="text/javascript"></script>
<script type="text/javascript">
	var MESSAGE_FAILURE = "操作失败！";
	var MESSAGE_OPERATE_CONFIRM = "您确定要执行该操作？";
	var MESSAGE_DELETE_CONFIRM = "您确定要删除该记录？";
	var MESSAGE_BATCHDELETE_CONFIRM = "您确定要批量删除所选记录？";
	var MESSAGE_DELETE_IMG_CONFIRM = "您确定要删除该图片？";
	var MESSAGE_DELETE_IMG_UNSELECT = "请选择要删除的图片！";
	
	//添加菜单到收藏夹
	function addMyFavoriteMenu(menuId, obj) {
		var uncacheParam = new Date().getTime(); //防止页面缓存
		$.getJSON("<c:url value="/sso/system/menu/addMyFavorite.shtml"/>", { menuId: menuId, uncacheParam: uncacheParam}, function(json){
			alert(json.msg);
			if(json.code == '<%=MenuController.CODE_SUCCESS%>' && obj != null && obj != undefined) {
				if($(obj).text() != '') {
					$(obj).text("取消收藏");
				}
				if($(obj).val() != '') {
					$(obj).val("取消收藏");
				}
				$(obj).removeAttr('onclick').unbind('click').click(function(){
					removeMyFavoriteMenu(menuId, obj);
			    });
			}
		});
	}
	//移除收藏夹菜单
	function removeMyFavoriteMenu(menuId, obj) {
		var uncacheParam = new Date().getTime(); //防止页面缓存
		$.getJSON("<c:url value="/sso/system/menu/removeMyFavorite.shtml"/>", { menuId: menuId, uncacheParam: uncacheParam}, function(json){
			alert(json.msg);
			if(json.code == '<%=MenuController.CODE_SUCCESS%>' && obj != null && obj != undefined) {
				if($(obj).text() != '') {
					$(obj).text("添加到收藏夹");
				}
				if($(obj).val() != '') {
					$(obj).val("添加到收藏夹");
				}
				$(obj).removeAttr('onclick').unbind('click').click(function(){
					addMyFavoriteMenu(menuId, obj);
			    });
			}
		});
	}
	
	//页面跳转
	function redirectPage(url) {
		window.location.href = url;
	}

	//回退
	function back(url) {
		window.location.href = '<c:url value="/common/back.shtml"/>?url=' + encodeURI(url);
	}
</script>