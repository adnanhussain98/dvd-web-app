<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<div class="container text-centerth">

<title>Sign Up</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

	<h1>Sign Up</h1>
	<form action="./SignupServlet" method="POST">


	<div class="form-group row">
		<label for="createUsername" class="col-sm-2 col-form-label">Username</label>
		<div class="col-sm-10">
			<input type="text" name="title" id="createUsername">
		</div>
	</div>

	<div class="form-group row">
		<label for="createPassword1" class="col-sm-2 col-form-label">Password</label>
		<div class="col-sm-10">
			<input type="password" name="password1" id="createPassword1">
		</div>
	</div>

	<div class="form-group row">
		<label for="createPassword2" class="col-sm-2 col-form-label">Confirm Password</label>
		<div class="col-sm-10">
			<input type="password" name="password2" id="createPassword2">
		</div>
	</div>
	<input type="submit" class="btn btn-success">

	<a href="./GetDvdServlet">Back</a>
	
	</form>


</body>
</html>