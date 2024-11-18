<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="Beans.User" %>
<%@page import="Beans.Role" %>
<%@page import="DAO.RoleDAO" %>
<html>
<head>
	<title>Kosarkaski savez Srbije</title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/en/3/37/Kss-logo-cyr-full-color.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<% 
		User logged = (User)session.getAttribute("logged");
		List<User> users = (List<User>)request.getAttribute("users");
		RoleDAO roleDAO = RoleDAO.getInstance();
	%>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Lista svih korisnika</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Korisnicko ime</th>
						<th>Email</th>
						<% if(logged != null){ %>
							<th>Rola</th>
							<th>Akcije</th>
						<%}%>
					</tr>
				</thead>
				<tbody>
				<% for(User user: users){ %>
						<tr>
							<td><%= user.getId() %></td>
							<td><%= user.getName() %></td>
							<td><%= user.getEmail() %></td>
							<% if(logged != null) {%>
							<td><%= roleDAO.get(user.getRoleId()).getRole() %></td>
							<td>
								<% if(user.getId() == logged.getId() || logged.isAdmin()) {%>
									<a href="edit?id=<%= user.getId() %>">Izmeni</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
								<%} 
									if(logged.isAdmin() && user.getId() != logged.getId()){
								%>
									<a href="delete?id=<%= user.getId() %>" class="text-danger">Obrisi</a>
								<%} %>
							</td>
							<% } %>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
