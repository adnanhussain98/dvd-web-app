<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Hi ${sessionScope.user.getUsername()}</h1>
<p> Your api key is ${sessionScope.user.getApi()}</p>


</body>
</html>