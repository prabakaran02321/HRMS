package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.Department;
import com.hrms.repository.DepartmentRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DepartmentService {

	private DepartmentRepo departmentRepo;
	private Department department;

	public DepartmentService() {
		departmentRepo = new DepartmentRepo();
		department = new Department();
	}

	public void createDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		department.setDepartmentName(request.getParameter("departmentname"));
		department.setLocation(request.getParameter("location"));
		try {
			department.setManagerId(Integer.parseInt(request.getParameter("managerid")));
		} catch (NumberFormatException e) {
			department.setManagerId(null);
		}

		int result = departmentRepo.save(department);

		if (result > 0) {
			response.getWriter().write("Department record added successfully");
		} else {
			response.getWriter().write("Failed to add department record");
		}
	}

	public void getAllRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = departmentRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<Department> list = departmentRepo.getDeptRecords((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (Department department : list) {
				out.println("ID: " + department.getId());
				out.println("Department Name: " + department.getDepartmentName());
				out.println("Location: " + department.getLocation());
				out.println("Manager ID: " + department.getManagerId());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Department records have only " + noOfPages + " pages");
		}
	}

	public void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int departmentId;
		try {
			departmentId = Integer.parseInt(request.getParameter("departmentid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid department ID");
			return;
		}

		int result = departmentRepo.delete(departmentId);

		if (result > 0) {
			response.getWriter().write("Department deleted successfully");
		} else {
			response.getWriter().write("Failed to delete department");
		}
	}
}
