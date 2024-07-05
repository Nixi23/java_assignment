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
						<br><br><br><h3>Send Message</h3>
						
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<input type="hidden" name="uid" value="<%=u.getId() %>" >
							<input type="hidden" name="from" value="<%=u.getEmail() %>">
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="name" name="to"
									placeholder="User's Email" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'User's Email'">
							</div>
							<br><br>
							<div class="col-md-12 form-group">
								<input type="textarea" class="form-control" id="name" name="msg"
									placeholder="Enter Message" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Enter Message'">
							</div>
							<br><br>
							
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="send" class="primary-btn">Send Message</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>