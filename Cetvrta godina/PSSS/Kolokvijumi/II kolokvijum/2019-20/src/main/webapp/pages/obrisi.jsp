<%@ page import="jsp.*" %>
<%

	int id = Integer.parseInt(request.getParameter("id").toString());
	Nekretnina n = new Nekretnina();
	n.obrisi(id);
	String ime = request.getParameter("ime");
	String sifra = request.getParameter("sifra");
	request.setAttribute("ime", ime);
	request.setAttribute("sifra", sifra);
	out.print(ime + ", " + sifra);
%>