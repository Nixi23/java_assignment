<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
						<br><br><br><h3>Generate New Password</h3>
						<%String msg=(String)request.getAttribute("msg");
						if(msg!=null){ %>
						<h5><%out.println(msg); %></h5>
						<%} %>
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<%String email=(String)request.getAttribute("email"); %>
							<input type="hidden" name="email" value="<%=email %>">
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="name" name="pswd"
									placeholder="Password" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Password'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="name" name="cpswd"
									placeholder="Confirm Password" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Confirm Password'">
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="change" class="primary-btn">Change</button>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>