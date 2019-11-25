<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="models.DVD"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update DVD</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<div class="container text-centerth">
	<h1>Update DVD</h1>
	<body>

		<form action="./UpdateDvdServlet" method="POST">

			<input type="hidden" name="id" value="${dvd.getId()}">

			<div class="form-group row">
				<label for="inputTitle" class="col-sm-2 col-form-label">Title</label>
				<div class="col-sm-10">
					<input type="text" name="Title" value="${dvd.getTitle()}">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputGenre" class="col-sm-2 col-form-label">Genre</label>
				<div class="col-sm-10">
					<input type="text" name="Genre" value="${dvd.getGenre()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputYear" class="col-sm-2 col-form-label">Year</label>
				<div class="col-sm-10">
					<input type="text" name="Year" value="${dvd.getYear()}">
				</div>
			</div>
			<button type="submit" class="btn btn-success">Update</button>

		</form>
		
			<a href="./GetDvdServlet">Back</a>
</div>
</body>
</html>