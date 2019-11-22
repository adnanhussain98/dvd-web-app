<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="models.DVD"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="UTF-8">
<title>Adnan's DVD Store</title>
</head>
<body>

	<h4>All Dvds</h4>

	<%
		@SuppressWarnings("unchecked")
		ArrayList<DVD> dvdArray = (ArrayList<DVD>) request.getAttribute("dvds");

		/* 		for (DVD dvd : dvdArray) {
					out.print(dvd.getTitle() + "<br>");
				} */
	%>

	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Genre</th>
				<th>Year</th>

			</tr>
		</thead>
		<tbody>

			<%
				for (DVD dvd : dvdArray) {
					out.print("<tr> <td>" + dvd.getTitle() + "</td> <td>" + dvd.getGenre() + "</td> <td>" + dvd.getYear()
							+ "</tr> </td>");
				}
			%>
		</tbody>
	</table>

</body>
</html>