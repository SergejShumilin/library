<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Welcome</title></head>
<style>
    <%@include file="/WEB-INF/jsp/css/main.css" %>
    <%@include file="/WEB-INF/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="header.jsp"/>

<div>
<div class="main">
    <form method="POST" action="/">
        <p>Search the book</p>
        <input type="hidden" name="command" value="searchBook"/>
        <input name="title" type="text" placeholder="Book" required/>
        <input type="submit" value="search"/>
    </form>
</div>
<div class="main">
    <form method="POST" action="/">
        <p>Add a book</p>
        <input type="hidden" name="command" value="addBook"/>
        <input name="title" type="text" placeholder="Title" required/>
        <input name="author" type="text" placeholder="Author" maxlength="35" required/>
        <input name="genre" type="text" placeholder="Genre" maxlength="35" required/>
        <input type="submit" value="add"/>
    </form>
</div>
<div class="main">
    <c:forEach var="book" items="${books}">
        <form method="POST" action="/">
<%--            <input type="hidden" name="command" value="order"/>--%>
            <h2>Title: ${book.title} Author: ${book.author} Genre: ${book.genre}</h2>
            <input type="hidden" name="command" value="order"/>
            <input class = "order" type="submit" value="order"/>
        </form>
    </c:forEach>

</div>
</div>
</body>
</html>

