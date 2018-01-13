<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Up</title>
	<style type="text/css">
	<%@include file="./css/login.css" %>
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
					<li ><a href="index.jsp">Home</a></li>
					<li ><a href="login.jsp">Login</a></li>
					<li class="current"><a href="signup.jsp">Signup</a></li>
				</ul>
			</nav>
		</div>
	</header>	
	<div class="loginbox">
		<img src="./images/avatar.png" class="avatar">
		<h1>Sign Up Here</h1>
		<form action="signup" method="post">
			<p>Username</p>
			<input type="text" name="username" placeholder="Enter Username"/>
			<p>Password</p>
			<input type="password" name="password" placeholder="Enter Password"/>
			<input type="submit" value="Signup"/>
		</form>
	</div>
</body>
</html>