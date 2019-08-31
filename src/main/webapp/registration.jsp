<html>
<head>
<title>LIBRARY</title>
</head>
<style>
   <%@include file='/css/login.css' %>
</style>
<body>
<div class="login">
<h1>Registration</h1>
		<form class="registration" action="/" method="get">
			<table>
				<tr>
					<td>Name:</td>
					<td><input name="name" type="text"
					size="35" required /></td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input name="password" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
				<tr>
					<td>confirm your password:</td>
					<td><input name="passwordConfirm" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
			</table>
				    <input type="hidden" name="command" value="register"/>
					<input type="submit" value="submit" /></td>
		</form>
		<form name="logout" method="get" action="/">
                                <input type="hidden" name="command" value="logout">
                                <input type="submit" value="logout">
	</div>
</body>
</html>