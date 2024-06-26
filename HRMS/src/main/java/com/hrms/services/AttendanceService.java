package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.Attendance;
import com.hrms.repository.AttendanceRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AttendanceService {

	private AttendanceRepo attendanceRepo;
	private Attendance attendance;

	public AttendanceService() {
		attendanceRepo = new AttendanceRepo();
		attendance = new Attendance();
	}

	public void createAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			attendance.setEmployeeId(Integer.parseInt(request.getParameter("employeeid")));
		} catch (NumberFormatException e) {
			attendance.setEmployeeId(null);
		}

		attendance.setDate(request.getParameter("date"));
		attendance.setStatus(request.getParameter("status"));
		attendance.setNotes(request.getParameter("notes"));

		int result = attendanceRepo.save(attendance);

		if (result > 0) {
			response.getWriter().write("Attendance record added successfully");
		} else {
			response.getWriter().write("Failed to add attendance record");
		}
	}

	public void getAllRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = attendanceRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<Attendance> list = attendanceRepo.getAttendanceRecords((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (Attendance attendance : list) {
				out.println("Attendance ID: " + attendance.getId());
				out.println("Employee ID: " + attendance.getEmployeeId());
				out.println("Date: " + attendance.getDate());
				out.println("Status: " + attendance.getStatus());
				out.println("Notes: " + attendance.getNotes());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Attendance records have only " + noOfPages + " pages");
		}
	}

	public void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int attendanceId;
		try {
			attendanceId = Integer.parseInt(request.getParameter("attendanceid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid attendance ID");
			return;
		}

		int result = attendanceRepo.delete(attendanceId);

		if (result > 0) {
			response.getWriter().write("Attendance record deleted successfully");
		} else {
			response.getWriter().write("Failed to delete attendance record");
		}
	}
}
