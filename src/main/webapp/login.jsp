<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Library</title></head>

<h1>Login here</h1>
  <form name="loginForm" method="POST" action="/">
       <input type="hidden" name="command" value="login" />
       Login:<br/><input type="text" name="login" value=""/><br/>
       Password:<br/>
       <input type="password" name="password" value=""/>
       <br/>
       <input type="submit" value="Log in"/>
       <br/>
         ${errorLoginPassMessage}
        <br/>
  </form>

  <form action="/" method="POST">
  		<input type="hidden" name="command" value="goToRegister"/>
  		<input type="submit"  value="sign in" />
  </form>

</div>
</body>
</html>