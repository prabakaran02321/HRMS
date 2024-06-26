package com.hrms.sample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Welcome", urlPatterns = "/welcome")
public class Welcome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String uname = request.getParameter("username");
		out.println("Welcome " + uname);
		out.println();
		Object name = request.getAttribute("uname");
		out.println("Welcome " + name);
		out.println();
		Object pass = request.getAttribute("pass");
		out.println("password " + pass);
		out.println();
		request.removeAttribute("pass");
		Object rpass = request.getAttribute("pass");
		out.println("Welcome " + rpass);

	}

}
