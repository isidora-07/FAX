package Servlets;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Club;
import Beans.User;
import DAO.ClubDAO;
import Utilities.RouteConfig;

public class ClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClubDAO clubDAO = ClubDAO.getInstance();
	
	public ClubServlet() {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getServletPath();
			
			switch (action) {
				case "/clubs/delete":
					deleteClub(request, response);
					break;
				default:
					getAllClubs(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private User checkLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session == null)
			return null;
		
		User logged = (User)session.getAttribute("logged");
		
		return logged;
	}
	
	private void getAllClubs(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Club> clubs = clubDAO.getAll();
			clubs = Club.sortByWins(clubs);
			request.setAttribute("clubs", clubs);
			RequestDispatcher dispatcher = request.getRequestDispatcher(RouteConfig.clubList);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteClub(HttpServletRequest request, HttpServletResponse response) {
		try {
			User logged = checkLoggedUser(request);
			if(logged != null && logged.isAdmin()) {
				int id = Integer.parseInt(request.getParameter("id"));
				// clubDAO.delete(id);
			}
			response.sendRedirect("list");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
