<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="modulePath" value="${modulePath }"/>
<input type="hidden" name="${httpParamOpenMode }" value="${openMode }"/>
<c:if test="${not empty pojo.id}">
	<input type="hidden" name="id" value="${pojo.id}"/>
</c:if>