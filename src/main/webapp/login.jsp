<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Library</title></head>
<style>
   <%@include file='css/login.css' %>
</style>
<body>
<div  class="login">
<h1>Login here</h1>
  <form name="loginForm" method="POST" action="/">
       <input type="hidden" name="command" value="login" />
       <table>
       		<tr>
       		<td>Login:</td>
            <td><input type="text" name="login" value=""/></td>
            </tr>
            <td>Password:</td>
            <td><input type="password" name="password" value=""/></td>
         </table>
         <input type="submit" value="Log in"/>
  </form>

  <form action="servlet" method="POST">
  		<input type="hidden" name="command" value="goToRegister"/>
  		<input type="submit"  value="sign in" />
  </form>
</div>
</body>
</html>