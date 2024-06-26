package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.ProjectService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "ProjectServlet", urlPatterns = "/api/project")
public class ProjectServlet extends HttpServlet {

	private ProjectService projectService;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null
				&& (roleName.equals("admin") || roleName.equals("manager") || roleName.equals("team leader")
						|| roleName.equals("employee") || roleName.equals("human resources"))) {
			projectService.getAllRecord(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			projectService.createProject(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			projectService.deleteProject(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}
}
