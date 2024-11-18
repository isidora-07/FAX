<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@page import="jsp.*" %>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	List<Movie> listMovies = Movie.listMovies();
	int brojStranica = 1;
	String msgPrethodna = "";
	
	if(request.getParameter("prethodna") != null)
	{
		msgPrethodna = "";
		if(brojStranica == 1)
		{
			msgPrethodna = "Nema prethodne stranice";
		}
		
		
	}
	
	else if(request.getParameter("naredna") != null)
	{
		if()
	}
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Filmovi</h1>
	
	<div>
		<table cellpadding="0" width="80%">
					<tr>
						<th>Title</th>
						<th>Rating</th>
						<th>Actions</th>
					</tr>
					
					<tr>
	
						<p>
							Stranica <% out.print(i);%> od 50
							<form name="prethodna">
								<button> Prethodna </button>
							</form>
							<form>
								<button name="naredna"> Naredna </button>
							</form>
						</p>
						
						<%
							if(brojStranica <= 46)
							{
								for(int j=brojStranica; j<listMovies.size() && j<brojStranica+4; j++)
								{ %>
						<tr>
							<td><%out.print(listMovies.get(j).getTitle()); %></td>
							<td><%out.print(listMovies.get(j).getRating()); %></td>
							<td><a>Delete</a></td>
						</tr>
						
					<%			}		
							}
							else
							{
							
							}%>
				</table>
		
	</div>
	
</body>
</html>