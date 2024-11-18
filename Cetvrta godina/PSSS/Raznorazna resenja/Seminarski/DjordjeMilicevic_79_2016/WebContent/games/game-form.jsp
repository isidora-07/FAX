<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="Beans.*" %>
<%@page import="DAO.*" %>
<%@page import="java.util.Date" %>
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
		Game game = (Game)request.getAttribute("game");
		UserDAO userDAO = UserDAO.getInstance();
		ClubDAO clubDAO = ClubDAO.getInstance();
		RoleDAO roleDAO = RoleDAO.getInstance();
		Role managment = roleDAO.getManagment();
		
		String action = (String)request.getAttribute("action");
		String readonly = null;
		if(logged.getRoleId() != managment.getId() || game != null && game.isFinished()){
			readonly = "readonly";
		}
	%>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>
	
	<div class="container col-md-4 col-md-offset-4">
		
		<div class="card">
			<div class="card-body">
				
				<form action="<%= action %>" method="post">

				<caption>
					<h2>
						<c:if test="${action.equals('update')}">
            			Izmeni
            		</c:if>
						<c:if test="${action.equals('add')}">
            			Kreiraj utakmicu
            		</c:if>
					</h2>
				</caption>
				
				<c:if test="${error != null}">
					<p class="text-warning">${error}</p>
				</c:if>

				<c:if test="${game != null}">
					<input type="hidden" name="id" value="<c:out value='${game.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Domacin</label> 
					<select class="form-control" name="homeId" required="required" <%= readonly %>>
						<%
							for(Club club: clubDAO.getAll()){
								
						%>
							<option value="<%= club.getId() %>" <%= (game != null && game.getHomeId() == club.getId() ? "selected" : "") %>><%= club.getName() %></option>
						<%
								
							}
						%>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Gost</label> 
					<select class="form-control" name="awayId" required="required" <%= readonly %>>
						<%
							for(Club club: clubDAO.getAll()){
								
						%>
							<option value="<%= club.getId() %>" <%= (game != null && game.getAwayId() == club.getId() ? "selected" : "") %>><%= club.getName() %></option>
						<%
								
							}
						%>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Datum</label>
					<input type="date" value="<% out.print(game == null ? (new java.util.Date()) : game.getDate());%>" class="form-control" name="date"
						<%= readonly %> required="required">
				</fieldset>
				
				<%
					if(game != null && !game.isFinished() && game.getDate().getTime() <= (new Date()).getTime()) {
				%>
					<a href="finish?id=<%= game.getId()%>&winner=1" class="btn btn-primary">Pobedio domacin</a>
					<a href="finish?id=<%= game.getId()%>&winner=2" class="btn btn-primary">Pobedio gost</a>
				<%
					}
				%>
					
				<br>
				<br>
				
				<% if(logged.getRoleId() == managment.getId()){ %>
					<% if(game == null || game != null && !game.isFinished()){ %>
					<button type="submit" class="btn btn-primary">Sacuvaj</button>
					<%} %>
					
					<% if(game != null){ %>
					<a href="delete?id=<%= game.getId()%>" class="btn btn-warning" style="float:right;">Obrisi</a>
					<%} %>
				<%} %>
				</form>
			</div>
		</div>
	</div>
</body>
</html>