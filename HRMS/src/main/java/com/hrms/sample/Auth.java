package com.hrms.sample;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Auth", urlPatterns = "/demo")
public class Auth extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("username");
		req.setAttribute("uname", uname);
		
		String pass = req.getParameter("password");
		req.setAttribute("pass", pass);
		
		RequestDispatcher rd = req.getRequestDispatcher("/welcome");
		rd.forward(req, resp);
		resp.getWriter().println("Demo"); // This will skip this servlet response
		
//		RequestDispatcher rd = req.getRequestDispatcher("/welcome");
//		rd.include(req, resp);
//		resp.getWriter().println("Demo");
	}

}
