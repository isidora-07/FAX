<%@page import="org.apache.tomcat.websocket.WsSession"%>
<%@page import="jsp.*" %>
<%@page import="java.util.*" %>

<jsp:useBean id="user" class="jsp.User"></jsp:useBean>
<jsp:setProperty property="*" name="user" />

<%
	User u = user.Login(user.getUsername(), user.getPassword());

	if(u == null)
	{
		request.setAttribute("pogresnaLozinka", "x");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	else
	{
		session.setAttribute("user", u);
		request.getRequestDispatcher("pocetna.jsp").forward(request, response);
	}

%>
