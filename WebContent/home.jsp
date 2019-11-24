<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="models.DVD"%>
<%@ page import="java.util.ArrayList"%>
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

	<div class="container text-centerth">

		<div class="row-text-center">
			<div class="col-12 text-center">




				<h4>All DVDs</h4>

				<%
					@SuppressWarnings("unchecked")
					ArrayList<DVD> dvdArray = (ArrayList<DVD>) request.getAttribute("dvds");
				%>

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

						<%
							for (DVD dvd : dvdArray) {
								String buttons = "<td> <a class=\"btn btn-danger\" href=\"./DeleteDvdServlet?id=" + dvd.getId()
										+ "\"><i class=\"fa fa-trash\"></i><a/> <a class=\"btn btn-warning\" href=\"./UpdateDVDServlet?id="
										+ dvd.getId() + "\"><i class=\"fa fa-pencil-square-o\"></i><a/></td>";
								out.print("<tr><td>" + dvd.getTitle() + "</td><td>" + dvd.getGenre() + "</td><td>" + dvd.getYear());

								if ((boolean) session.getAttribute("loggedin")) {
									out.print(buttons);
								}
								out.print("</td></tr>");

								/* 					out.print("<tr><td>" + dvd.getTitle() + "</td><td>" + dvd.getGenre() + "</td><td>" + dvd.getYear()
															+ "</td><td><form method = \"Post\" action=\"./DeleteDvdServlet\"> <input type =\"hidden\" value =\"" + dvd.getId()
															+ "\" name = \"id\"><input type=\"submit\" value =\"delete\"></form></td> </tr>"); */
							}
						%>
					</tbody>
				</table>
			</div>
		</div>

		<a class="btn btn-primary" href="./insert.html"><i
			class="fa fa-plus"></i> ADD NEW</a>

	</div>
</body>
</html>