package Servlets;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClubDAO;
import DAO.GameDAO;
import DAO.RoleDAO;
import Beans.User;
import Beans.Club;
import Beans.Game;
import Beans.Role;
import Utilities.PagingInfoViewModel;
import Utilities.RouteConfig;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameDAO gameDAO = GameDAO.getInstance();
	private ClubDAO clubDAO = ClubDAO.getInstance();
	private RoleDAO roleDAO = RoleDAO.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getServletPath();
			
			switch (action) {
				case "/games/new":
					showNewForm(request, response);
					break;
				case "/games/add":
					addGame(request, response);
					break;
				case "/games/edit":
					showEditForm(request, response);
					break;
				case "/games/update":
					updateGame(request, response);
					break;
				case "/games/delete":
					deleteGame(request, response);
					break;
				case "/games/finish":
					finishGame(request, response);
					break;
				default:
					getAllGames(request, response);
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
	
	private java.sql.Date stringToDate(String date){
		try {
			java.util.Date deadtimeUtil = (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
			long deadlineLong = deadtimeUtil.getTime();
			return new java.sql.Date(deadlineLong);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = RouteConfig.gameForm;
			User logged = checkLoggedUser(request);
			Role managmentRole = roleDAO.getManagment();
			if(logged == null || logged.getRoleId() != managmentRole.getId())
				page = RouteConfig.permissionDenied;
			else
				request.setAttribute("action", "add");

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addGame(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "list";
			User logged = checkLoggedUser(request);
			Role managmentRole = roleDAO.getManagment();
			if(logged == null || logged.getRoleId() != managmentRole.getId())
				page = RouteConfig.permissionDenied;
			else {
				int homeId = Integer.parseInt(request.getParameter("homeId"));
				int awayId = Integer.parseInt(request.getParameter("awayId"));
				Date date = stringToDate(request.getParameter("date"));
				Game game = new Game(homeId, awayId, 0, date, false);
				
				if(date.getTime() > (new java.util.Date()).getTime() + (1000 * 60 * 60 * 24))			
					gameDAO.add(game);
				else {
					page = RouteConfig.gameForm;
					request.setAttribute("game", game);
					request.setAttribute("action", "add");
					request.setAttribute("error", "Izaberite datum najmanje dva dana u napred.");
				}
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = RouteConfig.gameForm;
			User logged = checkLoggedUser(request);
			Role managmentRole = roleDAO.getManagment();
			int id = Integer.parseInt(request.getParameter("id"));
			Game game = gameDAO.get(id);
			if(logged != null && game != null && logged.getRoleId() == managmentRole.getId()) {
				request.setAttribute("game", game);
				request.setAttribute("action", "update");
			}
			else
				page = RouteConfig.permissionDenied;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateGame(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "../";
			User logged = checkLoggedUser(request);
			Role managmentRole = roleDAO.getManagment();
			Game g = gameDAO.get(Integer.parseInt(request.getParameter("id")));
			
			if(g != null && !g.isFinished() && logged != null && logged.getRoleId() == managmentRole.getId()) {
				int homeId = Integer.parseInt(request.getParameter("homeId"));
				int awayId = Integer.parseInt(request.getParameter("awayId"));
				Date date = stringToDate(request.getParameter("date"));
				Game game = new Game(g.getId(), homeId, awayId, 0, date, false);
				
				if(g.getDate().equals(game.getDate()) || (g.getDate() != game.getDate() && date.getTime() > (new java.util.Date()).getTime() + (1000 * 60 * 60 * 24))) {
					System.out.println(g.getDate() == game.getDate());
					gameDAO.update(game);
					response.sendRedirect("list");
				}
				else {
					page = RouteConfig.gameForm;
					request.setAttribute("game", game);
					request.setAttribute("action", "udapte");
					request.setAttribute("error", "Izaberite datum najmanje dva dana u napred.");
				}
			}
			else
				page = RouteConfig.permissionDenied;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteGame(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "list";
			User logged = checkLoggedUser(request);
			Role managmentRole = roleDAO.getManagment();
			Game game = gameDAO.get(Integer.parseInt(request.getParameter("id")));
			
			if(game != null && logged != null && logged.getRoleId() == managmentRole.getId())
				gameDAO.delete(game.getId());
			else
				page = RouteConfig.permissionDenied;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAllGames(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = RouteConfig.gameList;
			String message = "";
			PagingInfoViewModel pivm = null;
			User logged = checkLoggedUser(request);
			
			if(logged != null) {
				List<Game> games = null;
				games = gameDAO.getAll();
				
				if(games != null && games.size() > 0) {
					games = Game.arrangeGames(games);

					String priority = request.getParameter("priority");
					if(priority != null && !priority.isEmpty() && games != null && games.size() > 0)
						games = Game.filterByPriority(games, priority);
					
					if(games == null || games.size() == 0)
						message = "Nema rezultata pretrage";
					else {	
						String pageNum = request.getParameter("page_num");
						int currentPage = pageNum != null && !pageNum.isEmpty() ? Integer.parseInt(pageNum) : 1;
						pivm = new PagingInfoViewModel(games.size(), currentPage);
						
						int to = pivm.currentPage * pivm.itemsPerPage > games.size() ? games.size() : pivm.currentPage * pivm.itemsPerPage;
						games = games.subList((pivm.currentPage - 1) * pivm.itemsPerPage, to);
					}
				}
				else
					message = "Nema utakmica";
				
				request.setAttribute("games", games);
				request.setAttribute("paging_info", pivm);
				request.setAttribute("message", message);
			}
			else
				page = "../welcome.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void finishGame(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = "../";
			User logged = checkLoggedUser(request);
			Game game = gameDAO.get(Integer.parseInt(request.getParameter("id")));
			String winner = request.getParameter("winner");
			
			if(game != null && logged != null) {
				game.setFinished(true);
				game.setWinner(Integer.parseInt(winner));
				gameDAO.update(game);
				
				if(Integer.parseInt(winner) == 1) {
					System.out.print("POBEDNIK DOMACIN");
					Club clubW = clubDAO.get(game.getHomeId());
					clubW.setWins(clubW.getWins() + 1);
					clubDAO.update(clubW);
					
					Club clubL = clubDAO.get(game.getAwayId());
					clubL.setLosses(clubL.getLosses() + 1);
					clubDAO.update(clubL);
				} else {
					System.out.print("POBEDNIK GOST");
					Club clubW = clubDAO.get(game.getAwayId());
					clubW.setWins(clubW.getWins() + 1);
					clubDAO.update(clubW);
					
					Club clubL = clubDAO.get(game.getHomeId());
					clubL.setLosses(clubL.getLosses() + 1);
					clubDAO.update(clubL);
				}
			}
			else
				page = RouteConfig.permissionDenied;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
