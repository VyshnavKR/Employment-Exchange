<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Page</title>
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
          		<h1><span class="highlight">Infosys</span> IT Service</h1>
       		</div>
			<nav>	
				<ul>
					<li><a href="logout">Logout</a></li>
				</ul>
			</nav>
		</div>
	</header>	
	
	<h1 align ="center">Applicants sorted in descending order based on (experience)*(salary)</h1>
	<table border="1" cellpadding="5" align="center">
		<tr>
			<th>Name</th>
			<th>Experience</th>
			<th>Salary</th>
		</tr>	
		<c:forEach items="${listProfile}" var="profile">
		<tr>
			<td> <c:out value="${profile.firstName}"/></td>
			<td><c:out value="${profile.experience }"/></td>
			<td><c:out value="${profile.salary}"/></td>
		</tr>
		</c:forEach>
	</table>	
</body>
</html>