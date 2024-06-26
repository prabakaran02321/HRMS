package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.Project;
import com.hrms.repository.ProjectRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProjectService {

	private ProjectRepo projectRepo;
	private Project project;

	public ProjectService() {
		projectRepo = new ProjectRepo();
		project = new Project();
	}

	public void createProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		project.setProjectName(request.getParameter("projectname"));
		project.setStartDate(request.getParameter("startdate"));
		project.setEndDate(request.getParameter("enddate"));
		try {
			project.setBudget(new java.math.BigDecimal(request.getParameter("budget")));
		} catch (NumberFormatException e) {
			project.setBudget(null);
		}
		try {
			project.setDepartmentId(Integer.parseInt(request.getParameter("departmentid")));
		} catch (NumberFormatException e) {
			project.setDepartmentId(null);
		}

		int result = projectRepo.save(project);

		if (result > 0) {
			response.getWriter().write("Project record added successfully");
		} else {
			response.getWriter().write("Failed to add project record");
		}
	}

	public void getAllRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = projectRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<Project> list = projectRepo.getProjectRecords((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (Project project : list) {
				out.println("ID: " + project.getId());
				out.println("Project Name: " + project.getProjectName());
				out.println("Start Date: " + project.getStartDate());
				out.println("End Date: " + project.getEndDate());
				out.println("Budget: " + project.getBudget());
				out.println("Department ID: " + project.getDepartmentId());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project records have only " + noOfPages + " pages");
		}
	}

	public void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int projectId;
		try {
			projectId = Integer.parseInt(request.getParameter("projectid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid project ID");
			return;
		}

		int result = projectRepo.delete(projectId);

		if (result > 0) {
			response.getWriter().write("Project deleted successfully");
		} else {
			response.getWriter().write("Failed to delete project");
		}
	}
}

