package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.TrainingSession;
import com.hrms.repository.TrainingSessionRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrainingSessionService {

	private TrainingSessionRepo sessionRepo;
	private TrainingSession session;

	public TrainingSessionService() {
		sessionRepo = new TrainingSessionRepo();
		session = new TrainingSession();
	}

	public void createTrainingSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		session.setTrainingName(request.getParameter("trainingname"));
		session.setDescription(request.getParameter("description"));
		try {
			session.setTrainerId(Integer.parseInt(request.getParameter("trainerid")));
		} catch (NumberFormatException e) {
			session.setTrainerId(null);
		}
		session.setDate(request.getParameter("date"));
		session.setLocation(request.getParameter("location"));

		int result = sessionRepo.save(session);

		if (result > 0) {
			response.getWriter().write("Training session added successfully");
		} else {
			response.getWriter().write("Failed to add training session");
		}
	}

	public void getAllRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = sessionRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<TrainingSession> list = sessionRepo.getTrainingSessions((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (TrainingSession session : list) {
				out.println("Training ID: " + session.getTrainingId());
				out.println("Training Name: " + session.getTrainingName());
				out.println("Description: " + session.getDescription());
				out.println("Trainer ID: " + session.getTrainerId());
				out.println("Date: " + session.getDate());
				out.println("Location: " + session.getLocation());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Training sessions have only " + noOfPages + " pages");
		}
	}

	public void deleteTrainingSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int trainingId;
		try {
			trainingId = Integer.parseInt(request.getParameter("trainingid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid training ID");
			return;
		}

		int result = sessionRepo.delete(trainingId);

		if (result > 0) {
			response.getWriter().write("Training session deleted successfully");
		} else {
			response.getWriter().write("Failed to delete training session");
		}
	}
}
