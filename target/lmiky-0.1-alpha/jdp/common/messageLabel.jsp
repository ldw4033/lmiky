<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Error Messages --%>
<c:if test="${not empty errorInfos}">
    <div class="message error" id="Info">
        <c:forEach var="error" items="${errorInfos}">
            <img src="${images}/iconWarning.gif" class="icon" />
            <label class="error"><c:out value="${error}"/></label>
        </c:forEach>
    </div>
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty messageInfos}">
    <div class="message" id="successMessages">
        <c:forEach var="msg" items="${messageInfos}">
            <img src="${images}/iconInformation.gif" class="icon" />
            <label><c:out value="${msg}"/></label>
        </c:forEach>
    </div>
</c:if>
