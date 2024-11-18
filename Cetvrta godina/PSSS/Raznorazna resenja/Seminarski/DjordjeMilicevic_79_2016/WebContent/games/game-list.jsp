<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Beans.*"%>
<%@page import="DAO.*"%>
<%@page import="Utilities.*"%>
<html>
<head>
	<title>Kosarkaski savez Srbije</title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/en/3/37/Kss-logo-cyr-full-color.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/dfcefd8659.js" crossorigin="anonymous"></script>
</head>
<body>

	<%
		User logged = (User) session.getAttribute("logged");
		UserDAO userDAO = UserDAO.getInstance();
		ClubDAO clubDAO = ClubDAO.getInstance();
		RoleDAO roleDAO = RoleDAO.getInstance();
		Role managment = roleDAO.getManagment();
		Role fan = roleDAO.getFan();
	
		String search = request.getParameter("search");
		String priority = request.getParameter("priority");
	
		List<Game> games = (List<Game>) request.getAttribute("games");
		PagingInfoViewModel pivm = (PagingInfoViewModel) request.getAttribute("paging_info");
		String message = (String) request.getAttribute("message");

		if (logged == null)
			response.sendRedirect("permission-denied.jsp");
	%>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Lista svih utakmica</h3>
			<hr>

			<%
				if (logged != null && logged.getRoleId() == managment.getId()) {
			%>
			<div class="container text-left col-md-4">
				<a href="<%=request.getContextPath()%>/games/new"
					class="btn btn-primary">Kreiraj utakmicu</a>
			</div>
			<%
				}
			%>

			<br>
			<%
				if (search != null && !search.isEmpty()) {
			%>
			<div class="container text-center">
				<a href="?" class="btn btn-info" style="border-radius: 15px;">
					<%= search %> <i class="fas fa-times-circle"></i>
				</a>
			</div>

			<%
				}
			%>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Domacin</th>
						<th>Gost</th>
						<th>Datum</th>
						<th>Pobednik</th>
						<% if(logged != null && logged.getRoleId() == managment.getId()) { %>
							<th>Akcije</th>
						<%}%>
					</tr>
				</thead>
				<tbody>
					<%
						if (games == null) {
					%>
					<tr>
						<td colspan="4" align="center">
							<%= message %>
						</td>
					</tr>
					<%
						} else {
				
							
					for (Game game : games) {
					%>
					<tr class="<% out.print(game.giveBsProperty());%>">
						<td>
							<%= clubDAO.get(game.getHomeId()).getName() %>
						</td>
						<td>
							<%= clubDAO.get(game.getAwayId()).getName() %>
						</td>
						<td>
							<%= game.getDate().toString() %>
						</td>
						<td>
							<% 
								if(game.getWinner() == 1) {
									out.print(clubDAO.get(game.getHomeId()).getName());
								} else if (game.getWinner() == 2) {
									out.print(clubDAO.get(game.getAwayId()).getName());
								} else {
									out.print("Utakmica nije zavrsena");
								}
							%>
						</td>
						<% if(logged != null && logged.getRoleId() == managment.getId()) {%>
							<td>
								<a href="edit?id=<%= game.getId() %>">Izmeni</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="delete?id=<%= game.getId() %>" class="text-danger">Obrisi</a>
							</td>
						<% } %>
					</tr>
					<%	}
					}
					%>
				</tbody>
			</table>

			<% if(pivm != null){ %>
				<ul class="pagination nav navbar-nav navbar-right">
					<%
						for (int i = 1; i <= pivm.totalPages; i++) {
					%>
					<li
						class="page-item <%= (i == pivm.currentPage ? "active" : "")%>">
						<a href="<%=request.getContextPath()%>/games/?page_num=<%out.print(i); out.print(search != null ? "&search=" + search : ""); out.print(priority != null ? "&priority=" + priority : "");%>" class="page-link">
							<%
								out.print(i);
							%>
						</a>
					</li>
					<%
						}
					%>
				</ul>
			<%} %>

			<ul class="list-group col-md-4 col-sm-4">
				<li class="list-group-item">
					<div class="bg-danger col-sm-1" style="margin-right: 10px;">&nbsp;</div>
					<a
					href="?priority=now">Utakmice u toku</a>
				</li>
				<li class="list-group-item">
					<div class="bg-warning col-sm-1" style="margin-right: 10px;">&nbsp;</div>
					<a
					href="?priority=soon">Manje od 2 dana do utakmice</a>
				</li>
				<li class="list-group-item">
					<div class="bg-info col-sm-1" style="margin-right: 10px;">&nbsp;</div>
					<a
					href="?priority=latter">Vise od 2 dana do utakmice</a>
				</li>
				<li class="list-group-item">
					<div class="col-sm-1"
						style="border: 1px solid black; margin-right: 10px;">&nbsp;</div>
					<a
					href="?priority=finished">Zavrsene utakmice</a>
				</li>
				<li class="list-group-item"><a
					href="?">Sve utakmice</a></li>
			</ul>
		</div>
	</div>
</body>
</html>