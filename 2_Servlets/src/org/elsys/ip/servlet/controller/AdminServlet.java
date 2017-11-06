package org.elsys.ip.servlet.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elsys.ip.servlet.model.PasswordFactory;
import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() throws NoSuchAlgorithmException {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("name");
		System.out.println(name);
		if (name != null) {
			List<User> users = userService.getUsers().stream()
					.filter(u -> u.getName().equals(name))
					.collect(Collectors.toList());
			request.setAttribute("users", users);
		} else {
			request.setAttribute("users", userService.getUsers());
		}
		getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = PasswordFactory.cryptPassword(request.getParameter("password"));
		String email = request.getParameter("email");

		userService.addUser(name, password, email);

		response.setContentType("text/html");
		request.setAttribute("users", userService.getUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
				.forward(request, response);

	}
}
