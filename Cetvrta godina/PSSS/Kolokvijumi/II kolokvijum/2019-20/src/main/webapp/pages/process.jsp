<%@ page import="jsp.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="korisnik" class="jsp.Korisnik"></jsp:useBean>
<jsp:setProperty property="*" name="korisnik" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>PSSS</title>
</head>
<body>
	<p>
		<%
			Korisnik k = korisnik.readKorisnik(korisnik.getIme(), korisnik.getSifra());
			if(k != null)
			{
				out.print(k.getUloga() + ": " + k.getIme());
				
				if(k.getUloga().equals("administrator"))
					session.setAttribute("role", k.getUloga());
				else
					session.setAttribute("role", k.getUloga());
			}
			else
			{
				out.print("Pogresno ime/lozinka!");
			}
			
			Nekretnina n = new Nekretnina();
			List<Nekretnina> listaN = n.listaNekretnina();
		%>
	</p>
	
	<br><br>
		<% 	int i;
			if(session.getAttribute("role").equals("administrator")) 
		{%>
		<table>
			<tr>
				<th>Tip</th>
				<th>Povrsina</th>
				<th>Adresa</th>
				<th>Cena</th>
				<th>Akcija</th>
			</tr>
			
			<% for(i=0; i<listaN.size(); i++){ %>
				<tr>
					<td><% out.print(listaN.get(i).getTip()); %></td>
					<td><% out.print(listaN.get(i).getPovrsina()); %></td>
					<td><% out.print(listaN.get(i).getAdresa()); %></td>
					<td><% out.print(listaN.get(i).getCena()); %></td>
					<% int _id = listaN.get(i).getId(); %>
					
					<td>
						<% out.print("<a href='obrisi.jsp?id=" + _id">Obrisi</a>"); %>
							    
					</td>
				</tr>
			<%} %>
		
		</table>
		<% } else{%>
			<table>
			<tr>
				<th>Tip</th>
				<th>Povrsina</th>
				<th>Adresa</th>
				<th>Cena</th>
			</tr>
			
			<% for(i=0; i<listaN.size(); i++){ %>
				<tr>
					<td><% out.print(listaN.get(i).getTip()); %></td>
					<td><% out.print(listaN.get(i).getPovrsina()); %></td>
					<td><% out.print(listaN.get(i).getAdresa()); %></td>
					<td><% out.print(listaN.get(i).getCena()); %></td>
				</tr>
			<%} %>
		
		</table>
		
		<%} %>
		
	
	
</body>
</html>

<!-- <input type="hidden" name="id" value="" />
						    <input type="submit" value="Obrisi" />
						</form> -->