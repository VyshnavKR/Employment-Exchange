<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add/Edit Details</title>
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
          		<h1>
					<c:if test="${profile==null}"> Create Profile</c:if>					
					<c:if test="${profile!=null}"> Edit Profile</c:if>
				</h1>
       		</div>
		</div>
	</header>	

		<div align="center">
			<c:if test="${profile==null}">
				<form action="insertProfile" method="post">
			</c:if>
			<c:if test="${profile!=null}">
				<form action="updateProfile" method="post">
			</c:if>
			<table border="1" cellpadding="5">
				<caption>
					<h2> </h2>
				</caption>
				<tr>
					<c:if test="${profile==null}"> 
						<input type="hidden" name="user_id" value="<c:out value='${user_id}'/>"></input>										
					</c:if>					
				</tr>
				<tr>
					<th>First Name:</th>
					<td>
						<input type="text" name="firstName" size="45" value='${profile.firstName}'></input>
					</td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td>
						<input type="text" name="lastName" size="45" value='${profile.lastName}'></input>
					</td>
				</tr>
				<tr>
					<th>Experience:</th>
					<td>
						<input type="text" name="experience" size="45" value='${profile.experience}'></input>
					</td>
				</tr>
				<tr>
					<th>Salary:</th>
					<td>
						<input type="text" name="salary" size="45" value='${profile.salary}'></input>
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<input type="submit" value="save"></input>
				</td>
				</tr>
			</table>
			</form>
		</div>
</body>
</html>