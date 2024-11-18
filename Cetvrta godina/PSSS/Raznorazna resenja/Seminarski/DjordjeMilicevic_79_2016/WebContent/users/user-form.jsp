<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="Beans.User" %>
<%@page import="Beans.Role" %>
<%@page import="DAO.RoleDAO" %>
<html>
<head>
<title>Kosarkaski savez Srbije</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/en/3/37/Kss-logo-cyr-full-color.png">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<% 
		User logged = (User)session.getAttribute("logged");
		User user = (User)request.getAttribute("user");
		RoleDAO roleDAO = RoleDAO.getInstance();
	%>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>
	
	<div class="container col-md-4 col-md-offset-4">
		
		<div class="card">
			<div class="card-body">
				
				<form action="<% out.print(user == null ? "add" : "update"); %>" method="post">

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Izmeni
            		</c:if>
						<c:if test="${user == null}">
            			Registruj se
            		</c:if>
					</h2>
				</caption>
				
				<c:if test="${error != null}">
					<p class="text-warning">${error}</p>
				</c:if>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Korisnicko ime</label> 
					<input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required"
						<c:out value='${logged.getId() == user.id ? null : "readonly"}'/>>
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label>
					<input type="email" value="<c:out value='${user.email}' />" class="form-control" name="email" required="required"
						<c:out value='${logged.getId() == user.id ? null : "readonly"}'/>>
				</fieldset>

				<fieldset class="form-group">
					<label>Lozinka</label>
					<input type="password" value="<c:out value='${user.password}' />" class="form-control" name="password"
						<c:out value='${logged.getId() == user.id ? null : "readonly"}'/>>
				</fieldset>
				
				<%if(user == null){ %>
					<fieldset class="form-group">
					<label>Ponovi lozinku</label>
						<input type="password" class="form-control" name="password1">
					</fieldset>
				<%} else if(user != null){ %>
				
					<fieldset class="form-group" <%=(logged.isAdmin() && logged.getId() != user.getId() ? "" : "hidden") %>>
							<label>Rola</label>
							<select name="role">
								<%
									for(Role role: roleDAO.getAll()){
										%>
											<option value="<%out.print(role.getId());%>" <%out.print(role.getId() == user.getRoleId() ? "selected" : "");%>><%out.print(role.getRole());%></option>
										<%
									}
								%>
							</select>
					</fieldset>
				
					<fieldset class="form-group" <%=logged.isAdmin() && logged.getId() != user.getId() ? "" : "hidden" %>>
						<div class="">
							<label>Admin</label>
							<input type="checkbox" class="form-control" name="admin" value="${!user.isAdmin}" ${user.isAdmin ? "checked" : ""} style="display: block; width: 40px;">
						</div>
					</fieldset>
					
				<%} %>
				

				<button type="submit" class="btn btn-primary">Sacuvaj</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>