package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.AttendanceService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "AttendanceServlet", urlPatterns = "/api/attendance")
public class AttendanceServlet extends HttpServlet {

	private AttendanceService attendanceService;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.attendanceService = new AttendanceService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null
				&& (roleName.equals("admin") || roleName.equals("manager") || roleName.equals("team leader")
						|| roleName.equals("employee") || roleName.equals("human resources"))) {
			attendanceService.getAllRecords(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && (roleName.equals("admin"))) { // should give access for hr also
			attendanceService.createAttendance(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleName = RoleValidator.getRole(request, response);

		if (roleName != null && (roleName.equals("admin"))) { // should give access for hr also
			attendanceService.deleteAttendance(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
		}
	}
}
