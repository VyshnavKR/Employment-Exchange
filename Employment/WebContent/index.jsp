<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style type="text/css">
	<%@include file="./css/index.css" %>
	</style>
</head>
<body>
	<header>
		<div class="container">
	   		<div id="branding">
          		<h1><span class="highlight">Employment</span> Exchange</h1>
       		</div>
			<nav>	
				<ul>
					<li class="current"><a href="index.jsp">Home</a></li>
					<li ><a href="login.jsp">Login</a></li>
					<li><a href="signup.jsp">Signup</a></li>
				</ul>
			</nav>
		</div>
	</header>	
	<section>
		<div class="container">
			<h3>ATTENTION!!</h3>
			<p>New applicants kindly signup and register your details. We will call you when vacancies are available.</p>
			<p>Existing candidates can edit their details after loggin in</p>
			<p>NOTE: Administrator may log in using the username: admin and password: password and check all the registered candidates</p>
		</div>
	</section>
</body>
</html>