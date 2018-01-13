package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.RegistrationDao;
import dto.Profile;

@WebServlet("/")
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistrationDao registrationDao;

	public void init() {
		System.out.println("init");
		String url=getServletContext().getInitParameter("url");
		String username=getServletContext().getInitParameter("username");
		String password=getServletContext().getInitParameter("password");
		registrationDao = new RegistrationDao(url,username,password);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {
			case "/signup":
				signup(request,response);
				break;
			case "/login":
				login(request,response);
				break;
			case "/logout":
				logout(request,response);
				break;
			case "/updateProfile":
				updateProfile(request,response);
				break;
			case "/insertProfile":
				insertProfile(request,response);
				break;
			case "/edit":
				edit(request,response);
				break;

			}
		} catch(SQLException ex) {
			throw new ServletException (ex);
			}		
	}

	
	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("updateProfile");
		HttpSession session = request.getSession();
//		Profile profile = registrationDao.getProfile(user_id); why going back to database when you have it in session
		Profile profile = (Profile) session.getAttribute("profile");
		int user_id= (int) session.getAttribute("user_id");
		float experience = Float.parseFloat(request.getParameter("experience"));
		float salary = Float.parseFloat(request.getParameter("salary"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Profile updatedProfile = new Profile(profile.getProfile_id(), user_id, experience, salary, firstName, lastName);
		boolean updated = registrationDao.updateProfile(updatedProfile);
		if(updated) {
			System.out.println("Profile updated..");
		}else {
	    		System.out.println("Profile not updated");};
		session.setAttribute("profile", updatedProfile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("my_profile.jsp");
		dispatcher.forward(request, response);	
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit");
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_edit_profile.jsp");
		dispatcher.forward(request,response);
	}

	private void insertProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("insertProfile");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		float experience = Float.parseFloat(request.getParameter("experience"));
		float salary = Float.parseFloat(request.getParameter("salary"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");	
		Profile newProfile = new Profile(user_id, experience, salary, firstName, lastName);
		registrationDao.insertProfile(newProfile);
		Profile profile = registrationDao.getProfile(user_id);
		HttpSession session = request.getSession();
		session.setAttribute("profile", profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("my_profile.jsp");
		dispatcher.forward(request, response);	}


	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("logout");
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("user_id");
		session.removeAttribute("profile");
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request,response);
	}

	private void showAdminpage(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ServletException, IOException{
		System.out.println("showAdminpage");
		List<Profile> listProfile = registrationDao.listAllProfiles();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		request.setAttribute("listProfile", listProfile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_page.jsp");
		dispatcher.forward(request, response);
	}


	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ServletException, IOException{
		System.out.println("login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkbox1 = request.getParameter("checkbox1");
		System.out.println(checkbox1);
		if(registrationDao.checkUser(username,password)) {
			System.out.println("checkUser=true");
			if(checkbox1 != null) {
				showAdminpage(request, response);
			}else {
				int user_id = registrationDao.getUserId(username,password);
				Profile profile = registrationDao.getProfile(user_id);
				HttpSession session = request.getSession();
				session.setAttribute("user_id", user_id);
				session.setAttribute("username", username);
				session.setAttribute("profile", profile);
				RequestDispatcher dispatcher = request.getRequestDispatcher("my_profile.jsp");
				dispatcher.forward(request, response);
			}		
		} else {
			System.out.println("checkUser=false");
			System.out.println("Login failed...Username/Password doesn't exist");
		}
	}


	private void signup(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ServletException, IOException{
		System.out.println("Sign up");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(registrationDao.createUser(username,password)) {
			System.out.println("createUser=true");
			int user_id = registrationDao.getUserId(username,password);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("user_id", user_id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("add_edit_profile.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("createUser=false");
			System.out.println("Sign up failed...Username already exists");
		}
	}
}
