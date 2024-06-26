package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.DepartmentService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/api/department")
public class DepartmentServlet extends HttpServlet {

	private DepartmentService departmentService;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.departmentService = new DepartmentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null
				&& (roleName.equals("admin") || roleName.equals("manager") || roleName.equals("human resources"))) {
			departmentService.getAllRecord(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			departmentService.createDepartment(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			departmentService.deleteDepartment(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}
}

