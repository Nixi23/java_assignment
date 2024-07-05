<%@page import="model.Message" %>
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
<%
Message m=new Message();
out.println(m.getTo());
%>
<section class="login_box_area section_gap">
		<div class="container">
			<div class="row">
				
				<div class="col-lg-6">
					<div class="login_form_inner">
						<form class="row login_form"
							action="UserController"
							method="post" id="contactForm" novalidate="novalidate">
							<br>
							<input type="hidden" name="from" value="<%=u.getEmail() %>" >
							<input type="hidden" name="to" value="<%=m.getTo() %>" >
							<div class="col-md-12 form-group">
								<input type="textarea" class="form-control" id="name" name="msg"
									placeholder="Enter Message" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Enter Message'">
							</div>
							<br><br>
							
							<div class="col-md-12 form-group">
								<button type="submit" name="action" value="reply" class="primary-btn">Reply</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>