<%@page import="model.Message" %>
<%@page import="Dao.UserDao" %>
<%@page import="java.util.*" %>
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
<section class="login_box_area section_gap">
		<div class="container">
		<br><br>
			 <table class="table">
						<thead>
							<tr>
								<th scope="col">Message</th>
								<th scope="col">Email</th>
								
							</tr>
						</thead>
						<tbody>
						<%List<Message> list = UserDao.getAllMessageByEmail(u.getEmail()); %>
						<%for(Message p :list){ %>
							<tr>
								<td>
									<h5><%=p.getMsg() %></h5>
								</td>
								<td>
									<h5><%=p.getFrom() %></h5>
								</td>
								
								<td>
									<h5><a href="UserController?action=reply&from_email=<%=p.getFrom() %>&to_email=<%=p.getTo() %>" >
									 <span class="ti-bag"></span><p class="hover-text">Reply</p></a> 
								</td>
							</tr>
							<%} %>
						</tbody>
					</table>
	
		</div>
	</section>

</body>
</html>