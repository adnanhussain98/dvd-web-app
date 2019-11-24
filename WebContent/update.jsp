<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "models.DVD" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update DVD</title>
</head>
<body>

	<%
		@SuppressWarnings("unchecked")
		DVD dvd = (DVD)request.getAttribute("dvds");
	%>
	
	<form action="./UpdateDvdServlet" method="POST">

		<input type="hidden" name="id" value ="<%= dvd.getId()%>">
		<input type="text" name="title" placeholder="Title" value ="<%= dvd.getTitle()%>">
		<input type="text" name="genre" placeholder="Genre" value ="<%= dvd.getGenre()%>"> 
		<input type="number" name="year" placeholder="Year" value ="<%= dvd.getYear()%>">
			<button type="submit">Update</button>

	</form>

</body>
</html>