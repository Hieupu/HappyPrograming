<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<script>
    window.onload = function () {
        document.getElementById("requestForm").submit();
    };
</script>
<body>
    <form id="requestForm" action="${pageContext.request.contextPath}/mentee/listrequest" method="get">
        <c:if test="${empty menteeid}">
            <input type="hidden" name="id" value="${param.id}"/> 
        </c:if>
        <c:if test="${not empty menteeid}">
            <input type="hidden" name="id" value="${menteeid}"/> 
        </c:if>
    </form>
</body>
</html>
