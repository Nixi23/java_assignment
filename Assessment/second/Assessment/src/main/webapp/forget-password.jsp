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
						<br><br><br><h3>User Login</h3>
						<%String msg=(String)request.getAttribute("msg");
						if(msg!=null){%>
						<h5><%out.println(msg); %></h5>
						<%} %>
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="name" name="email"
									placeholder="Email" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Email'">
							</div>
							
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="getotp" class="primary-btn">Get OTP</button>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>