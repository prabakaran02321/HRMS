package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.TrainingSessionService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "TrainingSessionServlet", urlPatterns = "/api/training")
public class TrainingSessionServlet extends HttpServlet {

	private TrainingSessionService sessionService;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.sessionService = new TrainingSessionService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null
				&& (roleName.equals("admin") || roleName.equals("manager") || roleName.equals("human resources"))) {
			sessionService.getAllRecords(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			sessionService.createTrainingSession(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && roleName.equals("admin")) {
			sessionService.deleteTrainingSession(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}
}
