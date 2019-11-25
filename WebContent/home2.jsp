<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="models.DVD"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<title>Adnan's DVD Store</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Adnan's DVD Shop</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>

			</ul>

			<!-- if the user is logged in, their name will be displayed with a greeting and there will be a logout button -->
			<c:choose>
				<c:when test="${sessionScope.loggedin==true}">
					<a class="btn btn-danger" href="./LogOutServlet">Logout</a>
					<div id="user" class="btn btn-success">
						<!-- object of user -->
						Hello ${username}
					</div>
					<br />
				</c:when>
				<c:otherwise>
					<a class="btn btn-success" href="./LoginServlet">Login</a>
					<a class="btn btn-primary" href="./SignupServlet">Sign Up</a>
					<br />
				</c:otherwise>
			</c:choose>

		</div>

	</nav>




	<div class="container text-centerth">

		<div class="row-text-center">
			<div class="col-12 text-center">




				<h4>All DVDs</h4>


				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Genre</th>
							<th>Year</th>
							<th></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dvds}" var="dvd">
							<tr>
								<td>${dvd.getTitle()}</td>
								<td>${dvd.getGenre()}</td>
								<td>${dvd.getYear()}</td>
								<c:if test="${sessionScope.loggedin==true }">
									<td><a class="btn btn-danger"
										href="./DeleteDvdServlet?id=${dvd.getId()}"><i
											class="fa fa-trash"></i></a> <a class="btn btn-warning"
										href="./UpdateDvdServlet?id=${dvd.getId()}"><i
											class="fa fa-pencil-square-o"></i></a>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<c:choose>
			<c:when test="${sessionScope.loggedin==true}">
				<a class="btn btn-primary" href="./insert.html"><i
					class="fa fa-plus"></i> ADD NEW</a>
				<br />
			</c:when>
		</c:choose>

	</div>
</body>
</html>