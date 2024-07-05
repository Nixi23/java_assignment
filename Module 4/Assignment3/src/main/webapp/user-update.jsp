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
			<div class="row">
				
				<div class="col-lg-6">
					<div class="login_form_inner">
						<br><br><br><h3>User Registration</h3>
						<%String msg=(String)request.getAttribute("msg");
						if(msg!=null){%>
						<h3><%out.println(msg); %></h3>
						<%} %>
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<input type="hidden" name="uid" value="<%=u.getId() %>">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="fname" value="<%=u.getFname() %>">
									
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="lname" value="<%=u.getLname() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="contact" name="mobile" value="<%=u.getMobile() %>" >
							<br><br>
							
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="name" name="email" value="<%=u.getEmail() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="update" class="primary-btn">Update</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>