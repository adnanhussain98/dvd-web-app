<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>

	<h1>Sign Up</h1>
	<form action="./SignupServlet" method="POST">

		<input type="text" name="title" placeholder="Title"> <input
			type="password" name="password1">  <input
			type="password" name="password2"> <input
			type="submit">

	</form>
	
</body>
</html>