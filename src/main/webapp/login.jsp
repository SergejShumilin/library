<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Library</title></head>
<style>
    <%@include file="/WEB-INF/jsp/css/login.css" %>
</style>
<body>
<div class="login">
    <h1>Login here</h1>
    <form name="loginForm" method="POST" action="/">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="Login"/>
        <input type="password" name="password" placeholder="Password" value=""/>
        <input type="submit" value="Log in"/>
    </form>
    <form action="/" method="POST">
        <input type="hidden" name="command" value="goToRegister"/>
        <input type="submit" value="sign in"/>
    </form>
   <h2>${errorLoginPassMessage}</h2>
</div>
</body>
</html>