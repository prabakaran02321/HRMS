package com.hrms.services;

import java.io.IOException;
import java.io.InputStream;

import com.hrms.repository.DatabaseRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUploadService {

	private DatabaseRepo db;

	public FileUploadService() {
		this.db = new DatabaseRepo();
	}

	public void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();

		InputStream fileContent = filePart.getInputStream();
		int status = db.insertFileIntoTable("employee", "employee_image", fileContent);

		if (status > 0) {
			response.getWriter().print("File " + fileName + " uploaded successfully!");
		} else {
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "file not uploaded");
		}
	}

}
