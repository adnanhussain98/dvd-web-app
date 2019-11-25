<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
		<div class="container text-centerth">
<h1>Log In</h1>
<h5>Enter your username and password</h5>
<body>

	<form method="POST" action="./LoginServlet">


			<div class="form-group row">
				<label for="staticUsername" class="col-sm-2 col-form-label">Username</label>
				<div class="col-sm-10">
					<input type="text" name="username" id="staticUsername">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
				<div class="col-sm-10">
					<input type="password" name="password" id="inputPassword">
				</div>
			</div>
			<input type="submit" class="btn btn-success">
			
			<a href="./GetDvdServlet">Back</a>
		</div>
		
		
</body>
</html>