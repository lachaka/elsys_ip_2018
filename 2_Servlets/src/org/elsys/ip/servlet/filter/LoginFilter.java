package org.elsys.ip.servlet.filter;

import org.elsys.ip.servlet.model.PasswordFactory;
import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserService userService = null;

		try {
			userService = new UserService();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		PrintWriter out = response.getWriter();

		String username = request.getParameter("name");
		String password = request.getParameter("password");
		List<Cookie> cookies = new ArrayList<>();
		if (httpServletRequest.getCookies() != null) {
			cookies = Arrays.asList(httpServletRequest.getCookies());
			cookies = cookies.stream()
					.filter(c -> c.getName().equals("username"))
					.collect(Collectors.toList());
		}
		Cookie cookie = new Cookie("username", "");

		if (cookies.size() > 0) {
			cookie = cookies.get(0);
		}

		boolean authorized = false;

		if (username != null && password != null) {
			User user = userService.getByName(username);
			if (user != null && user.getPassword().equals(PasswordFactory.cryptPassword(password))) {
				authorized = true;
				cookie.setValue(username);
				httpServletResponse.addCookie(cookie);
			}
		}

		// check username and password (can be hardcoded, can use the userService)
		// add if the person is logged in to a cookie (Google it), so that we do not check at every page
		if (authorized || !cookie.getValue().equals("")) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
