<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<style>
    <%@include file="/WEB-INF/jsp/css/main.css" %>
    <%@include file="/WEB-INF/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="header.jsp"/>
<div class="main">
    <c:forEach var="book" items="${books}">
        <form method="POST" action="/">
            <p><fmt:message key="Main.title"/>: ${book.title}</p>
            <p><fmt:message key="Main.author"/>: ${book.author}</p>
            <p><fmt:message key="Main.genre"/>: ${book.genre} </p>
            <hr>
            <p><fmt:message key="Main.description"/>: ${book.description}</p>
            <input type="hidden" name="command" value="order"/>
            <input class="order" type="submit" value="<fmt:message key="Main.order"/>"/>
        </form>
    </c:forEach>
</div>
</body>
</html>


