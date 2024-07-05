<%@page import="model.Student" %>
<%@page import="Dao.StudentDao" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<%Student s=null;
if(session.getAttribute("data")!=null)
{
	s=(Student)session.getAttribute("data");
}
else
{
	response.sendRedirect("student-login.jsp");
}
%>
Welcome:<h4><%=s.getFname() %></h4>
<br>
<h3>Student Details are:</h3>
<br><br>
<table class="table">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Mobile</th>
								<th scope="col">Gender</th>
								<th scope="col">Email</th>
								<th scope="col">Edit</th>
								<th scope="col">Delete</th>
							</tr>
						</thead>
						<tbody>

<%List<Student> l1=StudentDao.listAllStudents(); 
for(Student s1:l1)
{
%>
<tr>
<td><%=s1.getId() %></td>
<td><%=s1.getFname() %></td>
<td><%=s1.getLname() %></td>
<td><%=s1.getMobile() %></td>
<td><%=s1.getGender() %></td>
<td><%=s1.getEmail() %></td>
<td><a href="StudentController?action=edit&id=<%=s1.getId() %>"><p class="hover-text">Edit</p></a></td>
<td><a href="StudentController?action=delete&id=<%=s1.getId() %>"><p class="hover-text">Delete</p></a></td>
</tr>
<%} %>
</tbody>
</table>
</body>
</html>