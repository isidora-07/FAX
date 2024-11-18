<%@ page import="Beans.*" %>
<%@ page import="DAO.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	User logged = (User)session.getAttribute("logged");
	RoleDAO roleDAO = RoleDAO.getInstance();
	Role managment = roleDAO.getManagment();
	Role fan = roleDAO.getFan();

	String action = "";
	if(logged != null){
		if(logged.getRoleId() == managment.getId()) {
			action = "Upravljaj utakmicama";
		}
		else if(logged.getRoleId() == fan.getId()) {
			action = "Utakmice";
		}
		else {
			action = "Utakmice";
		}
	}
%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse">

  	<div class="container-fluid">
  	
    	<div class="navbar-header">
      		<a class="navbar-brand" href="<%=request.getContextPath()%>/"> Kosarkaski savez Srbije </a>
    	</div>
    	
    	<ul class="nav navbar-nav">
	    	<% 	if(logged != null){ %>
	    		<li><a href="<%=request.getContextPath()%>/games/"><%=action%></a></li>
	    		<% if(logged.isAdmin()) { %>
	    			<li><a href="<%=request.getContextPath()%>/users/">Korisnici</a></li>
	    		<%}
	    		} %>
	    	<li><a href="<%=request.getContextPath()%>/clubs/list">Klubovi</a></li>
	    </ul>
	    
	    <ul class="nav navbar-nav navbar-right">
		    <% if(logged == null){ %>
		      	<li><a href="<%=request.getContextPath()%>/users/new"><span class="glyphicon glyphicon-user"></span> Registruj se </a></li>
		      	<li><a href="<%=request.getContextPath()%>/users/auth"><span class="glyphicon glyphicon-log-in"></span> Uloguj se </a></li>
		    <%} else{ %>
		      	<li><a href="<%=request.getContextPath()%>/users/logout"><span class="glyphicon glyphicon-log-in"></span> Izloguj se </a></li>
		    <%} %>
	    </ul>
	    
  	</div>
  	
</nav>