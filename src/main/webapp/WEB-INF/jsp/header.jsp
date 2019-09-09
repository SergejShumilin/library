
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <a href="/WEB-INF/jsp/main.jsp" class="logo">LIBRARY</a>
    <div class="header-right">
<%--        <a class="active" href="users.jsp">Users</a>--%>
<%--        <a href="login.jsp">Sign out</a>--%>
    <form action="/" method="post">
        <input type="hidden" name="command" value="logout"/>
        <input class="logout" type="submit" name="logout"  value="log out"/>
    </form>

    </div>
</div>


