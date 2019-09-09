<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="text"/>

<html lang="${param.lang}">
<head>
    <title><fmt:message key="Login.title"/></title>
</head>
<style>
    <%@include file="/WEB-INF/jsp/css/login.css" %>
</style>
<body>

<ul>
    <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
    <li><a href="?lang=de"><fmt:message key="label.lang.de" /></a></li>
</ul>


<div class="login">
    <h1><fmt:message key="Login.title" /></h1>
    <form name="loginForm" method="POST" action="/">
        <input type="hidden" name="command" value="login"/>

        <input type="text" name="login" placeholder="<fmt:message key="Login.login"/>"/>
        <input type="password" name="password" placeholder="<fmt:message key="Login.password"/>">

        <input type="submit" value="<fmt:message key = "Login.enter"/>"/>
    </form>
    <h2>${errorLoginPassMessage}</h2>
</div>
</body>
</html>