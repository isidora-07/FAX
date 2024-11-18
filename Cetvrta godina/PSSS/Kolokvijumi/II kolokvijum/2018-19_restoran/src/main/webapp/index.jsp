<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="POST" action="loginprocess.jsp">			
			<p>
				<label class="form-label">Username</label>
				<input type="text" placeholder="Username" name="username" id="username"  />
			</p>
			
			<p>
				<label class="form-label">Password</label>
				<input type="password" placeholder="Password" name="password" id="password"  />
			</p>
			
			<p>
				<input type="submit" id="login" value="Login" name="login" />
			</p>
			<%
				if(request.getAttribute("pogresnaLozinka") != null)
				{
					%>
					<span style="color: red;">Pogresna lozinka</span>
					<%
				}
			%>
			
		</form>
	</div>
</body>
</html>