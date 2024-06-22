<%@page import="Dao.UserDao"%>
<%@page import="java.util.List"%>
<%@page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="home-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<%
User u1 = null;
if(session.getAttribute("data")!=null)
{
	u1= (User) session.getAttribute("data");
}
else
{
	response.sendRedirect("login.jsp");
}
%>
<br><br><br><br><br>
<h1>Welcome : <%=u.getFname() %></h1>
<br><br><br>
<table border="2" align="center">
<tr>
<th>Id</th>
<th>Name</th>
<th>Contact</th>
<th>Address</th>
<th>Email</th>
</tr>
<%List<User> list=UserDao.getallDetails(u.getId()); %>
<%for(User u11:list) { %>
<tr>
<td><%=u11.getId() %></td>
<td><%=u11.getFname() %></td>
<td><%=u11.getContact() %></td>
<td><%=u11.getAddress() %></td>
<td><%=u11.getEmail() %></td>
</tr>
<%} %>
</table>
<div class="text-center">

</div>
</body>
</html>