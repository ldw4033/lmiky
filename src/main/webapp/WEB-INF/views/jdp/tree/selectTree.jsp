<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/jdp/form/header.jsp" %>
		<%@ include file="/jdp/common/tree.jsp" %>
			<script type="text/javascript">
				var zTreeObj;
				var treeId = "ztree";
				var selectedNode = null;
				var treeSetting = {
					treeId: 'selectTree',
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
						url: '<c:url value="/tree/treeList.shtml"/>',
						autoParam: ["id"]
					},
					callback: {
						onClick: zTreeOnClick
					}
				};
				function zTreeOnClick(event, treeId, treeNode) {
					setSelectedNode(treeNode);
				}  
				//如果只有一个顶层节点，则展开子节点，否则不展开
				var zTreeNodes = [  
					<c:choose>
						<c:when test="${fn:length(roots) > 1}">
							<c:forEach items="${roots}" var="node" varStatus="status">
				        		{id:"${node.id}", name:"${node.name}",
				        			<c:choose>
				        				<c:when test="${node.leaf == tree_leaf_yes}">isParent: "false"</c:when>
				        				<c:otherwise>isParent: "true"</c:otherwise>
				        			</c:choose>
				        		}<c:if test="${!status.last}">,</c:if>
				        	</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${roots}" var="node" varStatus="status">
								{id:"${node.id}", name:"${node.name}", open:true,
				        			<c:choose>
				        				<c:when test="${node.leaf == tree_leaf_yes}">isParent: "false"</c:when>
				        				<c:otherwise>
				        					isParent: "true",
				        					children: [  
				        				    	<c:forEach items="${node.children}" var="children" varStatus="childStatus">
					        				    	{id:"${children.id}", name:"${children.name}",
									        			<c:choose>
									        				<c:when test="${children.leaf == 0}">isParent: "false"</c:when>
									        				<c:otherwise>isParent: "true"</c:otherwise>
									        			</c:choose>
									        		}<c:if test="${!childStatus.last}">,</c:if>
				        				    	</c:forEach>
				        				 	]
				        				</c:otherwise>
				        			</c:choose>
				        		}<c:if test="${!status.last}">,</c:if>
				        	</c:forEach>
						</c:otherwise>
	        		</c:choose>
		        ]  
				$(document).ready(function() {
					zTreeObj = $.fn.zTree.init($("#" + treeId), treeSetting, zTreeNodes);
				});
				
				function setSelectedNode(node) {
					selectedNode = node;
				}
				
				//选择节点
				function selectNode() {
					if(selectedNode == null) {
						alert('请选择节点！');
					} else {
						var retValue = selectedNode.name + "," + selectedNode.id;
						window.returnValue = retValue;
						window.close();
					}
				}
			</script>
	</head>
	<body>
		<%@ include file="/jdp/form/messages.jsp" %>
		<!-- form id="mainForm" action="<c:url value="/user/save.shtml"/>" method="post" -->
		<%@ include file="/jdp/form/field.jsp" %>
		<table class="table-form full"  height="98%">
		   	<tr>
		   		<td class="bg01 title">
		   			&nbsp;<img src="${images }/jt-5.gif" width="16" height="16" align="absmiddle" />请选择节点
		   		</td>
		   	</tr>
		   	<tr>
		   		<td height="100%" valign="top" align="left">
		   			<ul id="ztree" class="ztree" style="overflow:auto;"></ul>
		   		</td>
		   	</tr>
		   	<tr>
				<td align="center">
					<input type="button" class="btnClass" style="cursor: pointer;" value="选择" onclick="selectNode()"/>
					&nbsp;&nbsp;
					<input type="button" class="btnClass" style="cursor: pointer;" value="关闭" onclick="window.close()"/>
				</td>
			</tr>
		</table>
	</body>
</html>