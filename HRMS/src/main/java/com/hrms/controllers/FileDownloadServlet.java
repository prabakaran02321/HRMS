package com.hrms.controllers;

import java.io.IOException;
import com.hrms.services.FileDownloadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FileDownloadServlet", urlPatterns = "/download")
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private FileDownloadService fileDownSer;

	private final String FILE_NAME = "C:\\Spring Tool\\Spring Workspace\\HRMS_Project\\HRMS\\src\\main\\webapp\\file\\sample.pdf";

	@Override
	public void init() throws ServletException {
		this.fileDownSer = new FileDownloadService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		fileDownSer.download(request, response, FILE_NAME);
	}
}
