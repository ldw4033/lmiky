<%@page import="com.lmiky.jdp.form.controller.FormController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lmiky.jdp.area.controller.AreaController" %>
<%@ include file="/jdp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self"/>
	<%@ include file="/jdp/form/header.jsp" %>
	<link rel="stylesheet" href="${css}/plugins/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${script }/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript">
		var zTreeObj;
		var treeSetting = {
			treeId: 'areaTree',
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
				url: '<c:url value="/area/treeList.shtml"/>',
				autoParam: ["id", "areaType"]
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {
			if(treeId == 0) {
				return;
			}
			var areaType = treeNode.areaType;
			if("<%=AreaController.AREA_TYPE_COUNTRY %>" == areaType) {
				$("#contentForm").attr("src", "<c:url value="/country/load.shtml"/>?modulePath=${param.modulePath }&id=" + treeNode.realId + "&<%=FormController.HTTP_PARAM_FORM_OEPNMODE%>=<%=FormController.OPEN_MODE_EDIT%>");
			}
		}  
		var zTreeNodes = [  
        	{id:"0", name:"地区", icon:"${css}/plugins/ztree/img/diy/1_open.png", "areaType":"<%=AreaController.AREA_TYPE_ROOT%>", open:true, children: [  
        	     <c:forEach items="${countries}" var="country" varStatus="status">
        	    	 {id:"<%=AreaController.AREA_TYPE_COUNTRY%>${country.id}", name:"${country.name}", "areaType":"<%=AreaController.AREA_TYPE_COUNTRY%>", icon:"${css}/plugins/ztree/img/diy/8.png", realId: "${country.id}", isParent: "true"}<c:if test="${!status.last}">,</c:if>
        	     </c:forEach>
             ]}  
        ]  
		$(document).ready(function() {
			zTreeObj = $.fn.zTree.init($("#ztree"), treeSetting, zTreeNodes);
			var nodes = zTreeObj.getNodes();
			if (nodes.length>0) {
				var node = nodes[0];
				zTreeObj.selectNode(node);
			}
		});
		
	</script>
</head>
<body scroll="no">
	<table class="table-form"  cellpadding="0" cellspacing="0" border="0" style="width: 100%; height:100%;">
		<tr>
			<td width="30%" valign="top">
				<ul id="ztree" class="ztree" style="overflow:auto;"></ul>
			</td>
			<td valign="top" >
				<iframe id="contentForm" style="width: 100%; height: 100%;" frameborder="0" src="" scrolling="no"/>
			</td>
		</tr>
	</table>
</body>
</html>