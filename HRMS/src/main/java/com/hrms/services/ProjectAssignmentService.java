package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.ProjectAssignment;
import com.hrms.repository.ProjectAssignmentRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProjectAssignmentService {

	private ProjectAssignmentRepo projectAssignmentRepo;
	private ProjectAssignment projectAssignment;

	public ProjectAssignmentService() {
		projectAssignmentRepo = new ProjectAssignmentRepo();
		projectAssignment = new ProjectAssignment();
	}

	public void createProjectAssignment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			projectAssignment.setProjectId(Integer.parseInt(request.getParameter("projectid")));
		} catch (NumberFormatException e) {
			projectAssignment.setProjectId(null);
		}

		try {
			projectAssignment.setEmployeeId(Integer.parseInt(request.getParameter("employeeid")));
		} catch (NumberFormatException e) {
			projectAssignment.setEmployeeId(null);
		}

		projectAssignment.setRole(request.getParameter("role"));
		projectAssignment.setStartDate(request.getParameter("startdate"));
		projectAssignment.setEndDate(request.getParameter("enddate"));

		int result = projectAssignmentRepo.save(projectAssignment);

		if (result > 0) {
			response.getWriter().write("Project assignment added successfully");
		} else {
			response.getWriter().write("Failed to add project assignment");
		}
	}

	public void getAllRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = projectAssignmentRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<ProjectAssignment> list = projectAssignmentRepo.getProjectAssignmentRecords((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (ProjectAssignment projectAssignment : list) {
				out.println("Assignment ID: " + projectAssignment.getId());
				out.println("Project ID: " + projectAssignment.getProjectId());
				out.println("Employee ID: " + projectAssignment.getEmployeeId());
				out.println("Role: " + projectAssignment.getRole());
				out.println("Start Date: " + projectAssignment.getStartDate());
				out.println("End Date: " + projectAssignment.getEndDate());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project assignment records have only " + noOfPages + " pages");
		}
	}

	public void deleteProjectAssignment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int assignmentId;
		try {
			assignmentId = Integer.parseInt(request.getParameter("assignmentid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid assignment ID");
			return;
		}

		int result = projectAssignmentRepo.delete(assignmentId);

		if (result > 0) {
			response.getWriter().write("Project assignment deleted successfully");
		} else {
			response.getWriter().write("Failed to delete project assignment");
		}
	}
}
