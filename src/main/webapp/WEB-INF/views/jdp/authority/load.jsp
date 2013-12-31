<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lmiky.jdp.module.controller.ModuleController,com.lmiky.jdp.authority.pojo.Authority,com.lmiky.jdp.module.pojo.Function,com.lmiky.jdp.module.pojo.Module" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self"/>
	<%@ include file="/jdp/form/header.jsp" %>
	<%@ include file="/jdp/common/tree.jsp" %>
	<script type="text/javascript">
		var zTreeObj;
		var treeSetting = {
			treeId: 'authorityTree',
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
				url: '<c:url value="/module/treeList.shtml"/>',
				autoParam: ["id", "moduleType"]
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {
			var moduleType = treeNode.moduleType;
			var modulePath = treeNode.modulePath;
			$("#userFrame").attr("src", "<c:url value="/authority/listOperator.shtml"/>?modulePath=" + modulePath + "&moduleType=" + moduleType);
		}  
		var zTreeNodes = [  
        	{id:"<%=ModuleController.TREE_LIST_ID_PREFIX_SYSTEM + 0%>", name:"系统", icon:"${css}/plugins/ztree/img/diy/1_open.png", "moduleType":"<%=Module.MODULE_TYPE_SYSTEM%>", open:true, children: [  
        	     <c:forEach items="${moduleGroups}" var="moduleGroup" varStatus="status">
        	    	 {id:"<%=ModuleController.TREE_LIST_ID_PREFIX_GROUP%>${moduleGroup.id}", name:"${moduleGroup.name}", "moduleType":"<%=Module.MODULE_TYPE_GROUP%>", "modulePah":"${moduleGroup.path}", icon:"${css}/plugins/ztree/img/diy/8.png", isParent: "true"}<c:if test="${!status.last}">,</c:if>
        	     </c:forEach>
             ]}  
        ]  
		$(document).ready(function() {
			zTreeObj = $.fn.zTree.init($("#ztree"), treeSetting, zTreeNodes);
			var nodes = zTreeObj.getNodes();
			if (nodes.length>0) {
				var node = nodes[0];
				zTreeObj.selectNode(node);
				changeUserType($("input[name='userType']:checked").val());
			}
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
			<td width="25%" valign="top">
				<ul id="ztree" class="ztree" style="overflow:auto;"></ul>
			</td>
			<td valign="top" >
				<iframe id="userFrame" style="width: 100%; height: 100%;" frameborder="0" src="" scrolling="no"/>
			</td>
		</tr>
	</table>
</body>
</html>