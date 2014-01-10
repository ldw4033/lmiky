<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lmiky.jdp.tree.controller.TreeController,com.lmiky.jdp.tree.pojo.BaseTreePojo" %>
<%@ include file="/jdp/common/common.jsp"%>
<c:set var="tree_leaf_yes" value="<%=BaseTreePojo.LEAF_YES %>"/>
<c:set var="tree_leaf_no" value="<%=BaseTreePojo.LEAF_NO %>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self"/>
	<%@ include file="/jdp/form/header.jsp" %>
	<%@ include file="/jdp/common/tree.jsp" %>
	<link rel="stylesheet" type="text/css" href="${css}/view.css" />
	<script type="text/javascript">
		var zTreeObj;
		var treeId = "ztree";
		var selectedNodeId;
		var treeSetting = {
			treeId: 'directoryTree',
			view: {
				showLine: false,
				showIcon: true,
				selectedMulti: false
			},
			check: {
				enable: false
			},
			async: {
				enable: true,
				url: '<c:url value="/cms/directory/treeList.shtml"/>',
				autoParam: ["id"]
			},
			callback: {
				onClick: zTreeOnClick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {
			setSelectedNodeId(treeNode);
			$("#resourceFrame").attr("src", "<c:url value="/cms/resource/list.shtml"/>?modulePath=cms/resource&directoryId=" + treeNode.id);
		}  
		
		function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			if(selectedNodeId != null && selectedNodeId != undefined && selectedNodeId != '') {
				var node = zTreeObj.getNodeByParam("id", selectedNodeId, null);
				zTreeObj.selectNode(node);
			}
		}
		
		var zTreeNodes = [  
        	<c:forEach items="${roots}" var="node" varStatus="status">
        		{id:"${node.id}", name:"${node.name}", "modulePath":"${param.modulePath}",
        			<c:choose>
        				<c:when test="${node.leaf == tree_leaf_yes}">isParent: "false"</c:when>
        				<c:otherwise>isParent: "true"</c:otherwise>
        			</c:choose>
        		}<c:if test="${!status.last}">,</c:if>
        	</c:forEach>
        ]  
		$(document).ready(function() {
			zTreeObj = $.fn.zTree.init($("#" + treeId), treeSetting, zTreeNodes);
			var nodes = zTreeObj.getNodes();
			if (nodes.length>0) {
				var node = nodes[0];
				zTreeObj.selectNode(node);
				zTreeOnClick(event, treeId, node);
			}
		});
		
		function setSelectedNodeId(node) {
			selectedNodeId = node.id;
		}
		
		function addDirectory() {
			var parentId = '';
			if(selectedNodeId != null) {
				parentId = selectedNodeId;
			}
			openDialog('<c:url value="/cms/directory/load.shtml?${httpParamOpenMode }=${createOpenMode }&modulePath=${modulePath }&parentId=' + parentId + '"/>', 600, 400, '', reAsyncNode);
		}
		
		function updateDirectory() {
			if(selectedNodeId != null) {
				openDialog('<c:url value="/cms/directory/load.shtml?${httpParamOpenMode }=${editOpenMode }&modulePath=${modulePath }&id=' + selectedNodeId + '"/>', 600, 400, '', reAsyncParentNode);
			} else {
				alert('请选择要修改的目录');
			}
		}
		
		function deleteDirectory() {
			if(selectedNodeId != null) {
				if(confirm(MESSAGE_DELETE_CONFIRM)) {
					$("#mainForm").prop("action", deleteUrl);
					document.getElementById("mainForm").submit();
				}
			} else {
				alert('请选择要删除的目录');
			}
		}
		
		function reAsyncNode() {
			reAsyncChildNodes(selectedNodeId);
		}
		
		function reAsyncParentNode() {
			var node = null;
			if(selectedNodeId != null && selectedNodeId != undefined && selectedNodeId != '') {
				node = zTreeObj.getNodeByParam("id", selectedNodeId, null);
				var parentNode = node.getParentNode();
				var refreshId = null;
				if(parentNode != null) {
					refreshId = parentNode.id;
				}
				reAsyncChildNodes(refreshId);
			}
		}
		
		function reAsyncChildNodes(nodeId) {
			var node = null;
			if(nodeId != null && nodeId != undefined && nodeId != '') {
				node = zTreeObj.getNodeByParam("id", nodeId, null);
			}
			//如果isParent=false,则reAsyncChildNodes不会执行
			if(node != null && node.isParent == false) {
				node.isParent = true;
			}
			//parentNode = null 时，相当于从根节点 Root 进行异步加载
			zTreeObj.reAsyncChildNodes(node, "refresh", false);
		}
	</script>
</head>
<body scroll="no">
	<table class="table-form"  cellpadding="0" cellspacing="0" border="0" style="width: 100%; height:100%;">
		<tr>
			<td width="150" valign="top" rowspan="2">
				<ul id="ztree" class="ztree" style="overflow:auto;"></ul>
			</td>
			<td valign="middle" class="listTitle2">
				<lauthority:checkAuthority authorityCode="cms_directory_add">
					<input class="btnClass" type="button" value="添加" onClick="addDirectory()"/>
					&nbsp;
				</lauthority:checkAuthority>
				<lauthority:checkAuthority authorityCode="cms_directory_modify">
					<input class="btnClass" type="button" value="修改" onclick="updateDirectory()"/>
					&nbsp;
				</lauthority:checkAuthority>
				<lauthority:checkAuthority authorityCode="cms_directory_delete">
					<input class="btnClass" type="button" value="删除" />
					&nbsp;
				</lauthority:checkAuthority>
				<c:set var="isFavorited" value="${false}" />
				<favorite:inMyMenu menuId="cms_directory_load">
				<c:set var="isFavorited" value="${true}" />
					<input class="btnClass1" type="button" onClick="removeMyFavoriteMenu('cms_directory_load', this)" value="取消收藏" />
				</favorite:inMyMenu>
				<c:if test="${!isFavorited }">
					<input class="btnClass1" type="button" onClick="addMyFavoriteMenu('cms_directory_load', this)" value="添加到收藏夹" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<iframe id="resourceFrame" style="width: 100%; height: 100%;" frameborder="0" src=""/>
			</td>
		</tr>
	</table>
</body>
</html>