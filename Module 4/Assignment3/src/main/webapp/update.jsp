<%@page import="java.util.*" %>
<%@page import="Dao.StudentDao" %>
<%@page import="model.Student" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
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
						<br><br><br><h3>Student Registration</h3>
						<%Student s=(Student)request.getAttribute("student"); %>
						<form class="row login_form"
							action="StudentController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<input type="hidden" name="id" value="<%=s.getId() %>">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="fname"
									value="<%=s.getFname() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="lname"
									value="<%=s.getLname() %>">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="contact" name="contact"
									value="<%=s.getMobile() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="contact" name="gender"
									value="<%=s.getGender() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="name" name="email"
									value="<%=s.getEmail() %>" >
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="name" name="password"
									value="<%=s.getPassword() %>" >
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