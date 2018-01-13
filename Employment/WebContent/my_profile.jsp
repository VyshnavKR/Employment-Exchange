<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>My Profile</title>
	<style type="text/css">
	<%@include file="./css/index.css" %>
	</style>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-Store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		if(session.getAttribute("username")==null){
			response.sendRedirect("login.jsp");
	}
	%>
	
	<header>
		<div class="container">
	   		<div id="branding">
          		<h1>My Profile</h1>
       		</div>
			<nav>	
				<ul>
					<li><a href="edit">Edit</a></li>
					<li><a href="logout">Logout</a></li>			
				</ul>
			</nav>
		</div>
	</header>	
	<table border="1" cellpadding="5" width="500">
		<tr>
			<th>First Name</th>
			<td>
				<c:out value="${profile.firstName}"/>
			</td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td>
				<c:out value='${profile.lastName}'/>
			</td>
		</tr>
				<tr>
			<th>Experience</th>
			<td>
				<c:out value="${profile.experience}"/>
			</td>
		</tr>
		<tr>
			<th>Salary</th>
			<td>
				<c:out value='${profile.salary}'/>
			</td>
		</tr>
	</table>
</body>
</html>