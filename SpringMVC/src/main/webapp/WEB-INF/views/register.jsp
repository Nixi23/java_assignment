
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Register</title>
</head>
<body>
	<%-- 
	<h2>Id : ${id }</h2>
	<h2>Name: ${name }</h2>
	<h2>List : ${list }</h2>
	<h2>By Scriplets</h2>
	<%List<String> list = (List)request.getAttribute("list"); %>
	<%for(String s:list){ %>
		<h3><%=s %></h3>
	<%} %>
	
	
		<h2>By for-each</h2>
	<c:forEach items="${list }" var="s">
		<h3>${s }</h3>
	</c:forEach> --%>
	<h1 class="text-center">Register Page</h1>
	<div class="container">
		<form action="insert" method="post">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Name </label> <input
					type="text" name="name" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Contact </label>
				<input type="text" name="contact" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email
					address</label> <input type="email" name="email" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input type="password" name="password" class="form-control"
					id="exampleInputPassword1">
			</div>
			<div class="text-center">
				<button type="submit" name="action" class="btn btn-primary">Register</button>
			</div>
		</form>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>
