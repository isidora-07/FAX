<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Kosarkaski savez Srbije</title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/en/3/37/Kss-logo-cyr-full-color.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<jsp:include page="../navigation-bar.jsp"></jsp:include>
	
	<div class="container col-md-4 col-md-offset-4">
		<div class="card">
			<div class="card-body">
				<form action="login" method="post">

				<caption>
					<h2>
						Login
					</h2>
				</caption>
				
				<c:if test="${error != null}">
					<p class="text-warning">${error}</p>
				</c:if>
				
				<fieldset class="form-group">
					<label>Korisnicko ime</label> 
					<input type="text" class="form-control" name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Lozinka</label> 
					<input type="password" class="form-control" name="password" required="required">
				</fieldset>

				<button type="submit" class="btn btn-primary">Sacuvaj</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>