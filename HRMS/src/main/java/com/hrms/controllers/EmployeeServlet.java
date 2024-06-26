package com.hrms.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.hrms.services.EmployeeService;

@WebServlet(name = "EmployeeServlet", urlPatterns  = "/api/employee/*")
public class EmployeeServlet extends HttpServlet {

	private EmployeeService employeeSer;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	private String dbName;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.employeeSer = new EmployeeService();

		ServletContext context = getServletContext();

		this.dbUrl = context.getInitParameter("dbUrl");
		this.dbUsername = context.getInitParameter("dbUsername");
		this.dbPassword = context.getInitParameter("dbPassword");

		context.setAttribute("dbName", "New Database");

		this.dbName = context.getInitParameter("dbName");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if(path.equals("/view")) {
			employeeSer.getAllRecord(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if(path.equals("/create")) {
			employeeSer.createEmployee(request, response);
		} else if(path.equals("/edit")) {
			employeeSer.editEmployee(request, response);
		}		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Database URL: " + dbUrl);
		out.println("Database Username: " + dbUsername);
		out.println("Database Password: " + dbPassword);
		out.println("Database Name: " + dbName);
	}

}
