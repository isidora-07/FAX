package Servlets;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.RoleDAO;
import DAO.UserDAO;
import Beans.User;
import Utilities.RouteConfig;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAO.getInstance();
	private RoleDAO roleDAO = RoleDAO.getInstance();

	public UserServlet() {

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getServletPath();
			switch (action) {
				case "/users/new":
					showNewForm(request, response);
					break;
				case "/users/add":
					addUser(request, response);
					break;
				case "/users/edit":
					showEditForm(request, response);
					break;
				case "/users/update":
					updateUser(request, response);
					break;
				case "/users/delete":
					deleteUser(request, response);
					break;
				case "/users/auth":
					showLoginForm(request, response);
					break;
				case "/users/login":
					loginUser(request, response);
					break;
				case "/users/logout":
					logoutUser(request, response);
					break;
				default:
					getAllUsers(request, response);
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

	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> users = userDAO.getAll();
			request.setAttribute("users", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher(RouteConfig.userList);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = RouteConfig.userForm;
			if(checkLoggedUser(request) != null)
				page = RouteConfig.permissionDenied;
			
			response.sendRedirect(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "list";
			if(checkLoggedUser(request) != null)
				page = RouteConfig.permissionDenied;
			
			else {
				String name = request.getParameter("name");
				User user = userDAO.get(name);
				
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String password1 = request.getParameter("password1");
				
				if(user == null && password.equals(password1)) {
					userDAO.add(new User(name, email, password, false, roleDAO.getFan().getId()));
					response.sendRedirect(page);		
				}
				else {
					if(user != null)
						request.setAttribute("error", "Korisnicko ime je zauzeto.");
					else if(password != password1)
						request.setAttribute("error", "Lozinke se ne poklapaju.");
					page = RouteConfig.userForm;
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(page);
					dispatcher.forward(request, response);
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = RouteConfig.userForm;
			if(checkLoggedUser(request) == null)
				page = RouteConfig.permissionDenied;
			else {
				int id = Integer.parseInt(request.getParameter("id"));
				User user = userDAO.get(id);
				request.setAttribute("user", user);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "list";
			User logged = checkLoggedUser(request);
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(logged != null && (logged.getId() == id || logged.isAdmin())){
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				int roleId = Integer.parseInt(request.getParameter("role"));
				String adminParam = request.getParameter("admin");
				boolean admin = false;
				if(adminParam != null)
					admin = adminParam.equals("true");
	
				userDAO.update(new User(id, name, email, "", admin, roleId));
			}
			else
				page = RouteConfig.permissionDenied;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			User logged = checkLoggedUser(request);
			if(logged != null && logged.isAdmin()) {
				int id = Integer.parseInt(request.getParameter("id"));
				userDAO.delete(id);
			}
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(RouteConfig.loginUser);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			User user = userDAO.login(name, password);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("logged", user);
				response.sendRedirect(request.getContextPath() + "/games/");
			}
			else {
				request.setAttribute("error", "Pogresno korisnicko ime ili lozinka.");
				RequestDispatcher dispatcher = request.getRequestDispatcher(RouteConfig.loginUser);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("logged");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
