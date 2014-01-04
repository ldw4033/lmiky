<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	<script type="text/javascript">
		var zTreeObj;
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
				onClick: zTreeOnClick
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {
			$("#resourceFrame").attr("src", "<c:url value="/authority/listOperator.shtml"/>?modulePath=" + modulePath + "&moduleType=" + moduleType);
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
			zTreeObj = $.fn.zTree.init($("#ztree"), treeSetting, zTreeNodes);
			//var nodes = zTreeObj.getNodes();
			//if (nodes.length>0) {
			//	var node = nodes[0];
			//	zTreeObj.selectNode(node);
			//	changeUserType($("input[name='userType']:checked").val());
			//}
		});
		
		function changeUserType(userType) {
			var nodes = zTreeObj.getSelectedNodes();
			if(nodes.length > 0) {
				zTreeOnClick(null, treeSetting.treeId, nodes[0]);
			}
		}
		
	</script>
</head>
<body scroll="no">
	<table class="table-form"  cellpadding="0" cellspacing="0" border="0" style="width: 100%; height:100%;">
		<tr>
			<td width="150" valign="top">
				<ul id="ztree" class="ztree" style="overflow:auto;"></ul>
			</td>
			<td valign="top">
				<iframe id="resourceFrame" style="width: 100%; height: 100%;" frameborder="0" src="" scrolling="no"/>
			</td>
		</tr>
	</table>
</body>
</html>