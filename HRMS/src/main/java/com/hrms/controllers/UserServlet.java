package com.hrms.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hrms.services.UserServices;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserServices userService;
//	private String dbUrl;
//	private String dbUsername;
//	private String dbPassword;

	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserServices();

//		this.dbUrl = config.getInitParameter("dbUrl");
//		this.dbUsername = config.getInitParameter("dbUsername");
//		this.dbPassword = config.getInitParameter("dbPassword");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path.equals("/signUp")) {
			userService.signUp(request, response);
		} else if (path.equals("/logIn")) {
			userService.LogIn(request, response);
//			PrintWriter out = response.getWriter();
//			out.println("Database URL: " + dbUrl);
//			out.println("Database Username: " + dbUsername);
//			out.println("Database Password: " + dbPassword);
		} else if (path.equals("/logOut")) {
			userService.logOut(request, response);
		} else if (path.equals("/forgotPassword")) {
			userService.logOut(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect URL");
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
