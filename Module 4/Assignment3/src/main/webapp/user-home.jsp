<%@page import="model.User" %>
<%@page import="Dao.UserDao" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="user-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br><br><br><br>
  Welcome: <%=u.getFname() %><br><br>
  <b>Full Name:</b> <%=u.getFname() %> <%=u.getLname() %><br>
 <b> Contact:</b> <%=u.getMobile() %><br>
  <b>Email:</b> <%=u.getEmail() %> <br>
 </body>
</html>