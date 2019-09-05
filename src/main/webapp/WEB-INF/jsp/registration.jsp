<html>
<head>
    <title>LIBRARY</title>
</head>
<body>
<h1>Registration</h1>
<form class="registration" method="POST" action="/">
	<input type="hidden" name="command" value="register"/>
    <br/>Name:<br/>
    <input name="name" type="text" size="35" required/>
    <br/>Password:<br/>
    <input name="password" type="password" size="35" maxlength="35" required/>
    <br/>Confirm your password:
    <br/><input name="passwordConfirm" type="password" size="35" maxlength="35" required/>
    <br/>
    <input type="submit" value="submit"/></td>
</form>

</body>
</html>