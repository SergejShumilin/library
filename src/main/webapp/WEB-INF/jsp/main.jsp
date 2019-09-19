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
<div>
<%--    <div class="main">--%>
    <div>
        <p><fmt:message key="Main.addBook"/></p>
        <form method="POST" action="/">
            <input type="hidden" name="command" value="addBook"/>
            <input name="title" type="text" placeholder="<fmt:message key="Main.title"/>" required/>
            <input name="author" type="text" placeholder="<fmt:message key="Main.author"/>" maxlength="35" required/>
            <input name="genre" type="text" placeholder="<fmt:message key="Main.genre"/>" maxlength="35" required/>
            <input name="description" type="text" placeholder="<fmt:message key="Main.description"/>" maxlength="500" required/>
            <input type="submit" value="<fmt:message key="Main.add"/>"/>
        </form>
    </div>
<%--    <div class="main">--%>
    <div>
        <c:forEach var="book" items="${books}">
            <form method="POST" action="/">
                <p><fmt:message key="Main.title"/>: ${book.title}</p>
                <p><fmt:message key="Main.author"/>: ${book.author}</p>
                <p><fmt:message key="Main.genre"/>: ${book.genre} </p>
                <hr>
                <p><fmt:message key="Main.description"/>: ${book.description}</p>
                <input type="hidden" name="title" value="${book.title}"/>
                <input type="hidden" name="command" value="delete"/>
                <input type="submit" value="<fmt:message key="Main.delete"/>"/>

                <input type="hidden" name="command" value="edit"/>
                <input type="submit" value="<fmt:message key="Main.edit"/>"/>
            </form>
        </c:forEach>

    </div>
</div>
</body>
</html>

