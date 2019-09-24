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
<jsp:include page="header/header.jsp"/>
<div>
    <div class="main">
        <p><fmt:message key="Main.addBook"/></p>
        <form method="POST" action="/">
            <input type="hidden" name="command" value="addBook"/>
            <div class="col-65">
            <input name="title" type="text" placeholder="<fmt:message key="Main.title"/>" required/>
            <input name="author" type="text" placeholder="<fmt:message key="Main.author"/>" maxlength="35" required/>
            <input name="genre" type="text" placeholder="<fmt:message key="Main.genre"/>" maxlength="35" required/>
            </div>
            <div class="col-75">
                <textarea id="description" name="description" placeholder="<fmt:message key="Main.description"/>"></textarea>
            </div>
            <input type="hidden" name="page" value="${page}">
            <input type="submit" value="<fmt:message key="Main.add"/>" class="main-submit"/>
        </form>
        <h2><c:if test="${errorLoginPassMessage == true}"><fmt:message key="Registration.errorLoginPassMessage"/></c:if></h2>
        <c:forEach var="book" items="${books}">
            <c:set var="book" value="${book}" scope="request"/>
            <c:import url="/WEB-INF/jsp/book.jsp"/>
        </c:forEach>
    </div>
</div>
</body>
</html>

