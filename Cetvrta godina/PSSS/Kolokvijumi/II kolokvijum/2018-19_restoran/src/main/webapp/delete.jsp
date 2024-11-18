<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="jsp.*" %>
<%@page import="java.util.*" %>

<%
	PreparedStatement prepStmt;
	int id = Integer.parseInt(request.getParameter("id"));
	Connection conn = ConnectionProvider.getCon();
	
	String query = "DELETE FROM user WHERE Id=?";
	
	prepStmt = conn.prepareStatement(query);
	prepStmt.setInt(1, id);
	
	prepStmt.executeUpdate();
	
	request.getRequestDispatcher("pocetna.jsp").forward(request, response);

	//response.sendRedirect("pocetna.jsp");
%>