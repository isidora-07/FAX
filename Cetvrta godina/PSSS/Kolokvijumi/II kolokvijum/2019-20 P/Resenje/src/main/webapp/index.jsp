<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="jsp.*" %>
    
<%
    String errorMsg = "";
    	if(request.getParameter("prijava") != null)
    	{
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    		
    		Login user = Login.LoginUser(new Login(username, password));
    		
    		if(user == null)
    		{
    	errorMsg = "Pogresno ime/lozinka.";
    		}
    		else
    		{
    	session.setAttribute("role", "all");
    	request.getRequestDispatcher("pocetna.jsp?id=" + user.getId()).forward(request, response);
    		}
    		
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PSSS</title>
</head>
<body>
	<div>
		<form method="POST" >
		
			Username: <input type="text" id="username" name="username" />
			<br>
			Password: <input type="password" id="password" name="password" />
			<%if(errorMsg != "") {%>
				<p style="color: red;"><%out.print(errorMsg); %></p>
			<%} 
			else{%>
				<p></p>
			<%} %>
			<input type="submit" value="Prijavi se" name="prijava" />
		</form>
	
	</div>

</body>
</html>