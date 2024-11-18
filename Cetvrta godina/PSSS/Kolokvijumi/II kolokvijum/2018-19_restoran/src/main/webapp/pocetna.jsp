<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="jsp.*" %>
<%@page import="java.util.*" %>

<%
	User user = (User)session.getAttribute("user");
	User user_role = user.getRoleIdByUserId(user.getId());
	int postoji_username = -1;
	
 	/************************ DODAVANJE ************************/
 	
	if(request.getParameter("dodaj") != null)
	{
		String name = request.getParameter("name").trim();
		int year = Integer.parseInt(request.getParameter("year"));
		String username = request.getParameter("user_name").trim();
		String password = request.getParameter("pass_word").trim();
		
		Role r = new Role();
		int idRole = r.getRoleIdByName("konobar");
		
		Connection conn = ConnectionProvider.getCon();
		
		String query = "INSERT INTO user(username, password, name, year, role_id) VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement prepStmt = conn.prepareStatement(query);
		
		boolean user_provera = user.jedinstvenUsernameProvera(username);
		if(user_provera == true)
		{
			if(year >= 18 && year <= 65)
			{
				prepStmt.setString(1, username);
				prepStmt.setString(2, password);
				prepStmt.setString(3, name);
				prepStmt.setInt(4, year);
				prepStmt.setInt(5, idRole);
				prepStmt.executeUpdate();
			}
			else
			{
				out.print("Broj godina mora da bude u granicama 18-65!!!");
			}
		}
		else
		{
			postoji_username = 1;
		}
	}
	
	/************************ IZMENA ************************/

	if(request.getParameter("izmeni") != null)
	{
		String stara_lozinka = request.getParameter("stara_lozinka").trim();
		String nova_lozinka = request.getParameter("nova_lozinka").trim();
		String ponovljena_lozika = request.getParameter("ponovljena_lozinka").trim();
		
		if(stara_lozinka.equals(user_role.getPassword()))
		{
			if(nova_lozinka.equals(ponovljena_lozika))
			{
				Connection conn = ConnectionProvider.getCon();
				
				String query = "UPDATE USER SET password=? WHERE Id=?";
				
				PreparedStatement prepStmt = conn.prepareStatement(query);
				
				prepStmt.setString(1, ponovljena_lozika);
				prepStmt.setInt(2, user_role.getId());
				
				prepStmt.executeUpdate();
			}
			else
			{
				out.print("Lozinke se ne pokrapaju..");
			}
		}
		else
		{
			out.print("Ukucajte staru lozinku ponovo!");
		}
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pocetna strana</title>
</head>
<body>
	<div>
		<%
		if(user != null)
		{
			Role r = new Role();
			Role role = r.getRoleById(user_role.getRole_id());
			
			
			if(role != null)
			{
				out.print(role.getName() + ": " + user.getName());
				
				if(role.getName().equals("sef sale"))
					session.setAttribute("role", role.getName());
				else if(role.getName().equals("konobar"))
					session.setAttribute("role", role.getName());
			}
			else
			{
				out.print("Nema role..."); // ne moze da se desi xd
			}
		}
		else
		{
			out.print("Pogresno username/password!");
		}
		%>
	
	</div>
	
	<%if(session.getAttribute("role").equals("sef sale")){ %>
		<div class="left">
			<div>
				<form method="POST" action="#">
					<p>
						<label class="form-label">Ime i prezime</label>
						<input type="text" placeholder="Ime i prezime" name="name" id="name"  />
					</p>
					
					<p>
						<label class="form-label">Godine</label>
						<input type="text" placeholder="Godine" name="year" id="year"  />
					</p>
					
					<p>
						<label class="form-label">Username</label>
						<input type="text" placeholder="Username" name="user_name" id="user_name"  />
					</p>
					
					<p>
						<label class="form-label">Password</label>
						<input type="password" placeholder="Password" name="pass_word" id="pass_word"  />
					</p>
					
					<p>
						<input type="submit" id="dodaj" value="Dodaj" name="dodaj" />
					</p>
					
					
					<%if(postoji_username == 1){ %>
						<p>Username vec postoji..</p>
					
					<%} %>
				</form>
			</div>
			
			<div>
				<table cellpadding="0" width="80%">
					<tr>
						<th>Ime i prezime</th>
						<th>Username</th>
						<th>Godine</th>
						<th>Akcija</th>
					</tr>
					<tr>
					
					<% 
						List<User> konobari = user.ListaKonobara();
						for(int i=0; i<konobari.size(); i++){
					%>
					
						<tr>
							<td><%out.print(konobari.get(i).getName()); %></td>
							<td><%out.print(konobari.get(i).getUsername()); %></td>
							<td><%out.print(konobari.get(i).getYear()); %></td>
							<%	int id_k = konobari.get(i).getId(); %>
							<td><a href="delete.jsp?id=<%=id_k%>">Obrisi</a></td>
						</tr>
					<%} %>
				</table>
			</div>
		<%}
		else{%>
			<div>
				<form method="POST" action="#">
					<p>
						<label class="form-label">Stara lozinka</label>
						<input type="password" placeholder="Stara lozinka" name="stara_lozinka" />
					</p>
					
					<p>
						<label class="form-label">Nova lozinka</label>
						<input type="password" placeholder="Nova lozinka" name="nova_lozinka" />
					</p>
					
					<p>
						<label class="form-label">Ponovi novu lozinku</label>
						<input type="password" placeholder="Ponovi lozinku" name="ponovljena_lozinka" />
					</p>
					
					<p>
						<input type="submit" value="Izmeni" name="izmeni" />
					</p>
					
				</form>
			</div>
		<%} %>
	</div>

</body>
</html>