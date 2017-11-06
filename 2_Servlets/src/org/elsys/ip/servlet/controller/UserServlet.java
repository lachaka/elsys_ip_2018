package org.elsys.ip.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() throws NoSuchAlgorithmException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		User user = userService.getByName(name);

		if (user == null) {
			out.println("No matches :(");
		} else {
			request.setAttribute("user", user);
			response.setContentType("text/html");
			getServletContext().getRequestDispatcher("/WEB-INF/user.jsp")
				.forward(request, response);
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String newName = request.getParameter("newName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		userService.updateUser(name, newName, password, email);
		request.setAttribute("user", userService.getByName(newName));
		getServletContext().getRequestDispatcher("/WEB-INF/user.jsp")
				.forward(request, response);
	}
}
