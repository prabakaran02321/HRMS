package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.ProjectAssignmentService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "ProjectAssignmentServlet", urlPatterns = "/api/project_assignment")
public class ProjectAssignmentServlet extends HttpServlet {

	private ProjectAssignmentService projectAssignmentService;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.projectAssignmentService = new ProjectAssignmentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null
				&& (roleName.equals("admin") || roleName.equals("manager") || roleName.equals("team leader") || roleName.equals("human resources"))) {
			projectAssignmentService.getAllRecord(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			projectAssignmentService.createProjectAssignment(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			projectAssignmentService.deleteProjectAssignment(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}
}
