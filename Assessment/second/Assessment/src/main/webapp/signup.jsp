<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
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
						<br><br><br><h3>User Sign Up</h3>
						<%String msg=(String)request.getAttribute("msg");
					if(msg!=null){%>
					<h5><%out.println(msg); %></h5>
					<%} %>
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="fname"
									placeholder="First Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'First Name'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="lname" name="lname"
									placeholder="Last Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Last Name'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="name" name="email"
									placeholder="Email" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Email'">
							</div>
							<br><br>
							
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="name" name="contact"
									placeholder="Mobile" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Contact'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="address" name="address"
									placeholder="Address" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Address'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
							<label for="male">Male</label>
								<input type="radio" id="male" name="gender"
									value="Male">
							</div>
							<div class="col-md-12 form-group">
							<label for="female">Female</label>
								<input type="radio"  id="female" name="gender"
									value="Female">
							</div>
							<br><br><br>
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="name" name="password"
									placeholder="Password" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Password'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="name" name="cpswd"
									placeholder="Confirm Password" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Confirm Password'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="register" class="primary-btn">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>