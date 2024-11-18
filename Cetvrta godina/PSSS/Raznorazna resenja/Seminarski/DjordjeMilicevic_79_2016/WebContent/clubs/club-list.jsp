<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="Beans.User" %>
<%@page import="Beans.Role" %>
<%@page import="Beans.Club" %>
<%@page import="DAO.ClubDAO" %>
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
		List<Club> clubs = (List<Club>)request.getAttribute("clubs");
	%>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Lista svih klubova</h3>
			<hr>
			<br>
			<table class="table table-bordered text-center align-items-center">
				<thead class="text-center">
					<tr>
						<th>Logo</th>
						<th>Naziv</th>
						<th>Grad</th>
						<th>Broj pobeda</th>
						<th>Broj gubitaka</th>
					</tr>
				</thead>
				<tbody>
				<% for(Club club: clubs){ %>
						<tr>
							<td><img alt="logo" src="<%= club.getLogo()%>" width="100" height="100"> </td>
							<td><%= club.getName() %></td>
							<td><%= club.getTown() %></td>
							<td><%= club.getWins() %></td>
							<td><%= club.getLosses() %></td>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
