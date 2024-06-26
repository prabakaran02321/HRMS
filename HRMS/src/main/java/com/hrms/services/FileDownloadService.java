package com.hrms.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileDownloadService {

	public void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {

		File file = new File(filePath);

		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, " Path: " + filePath);
			return;
		}

		String fileName = file.getName();
		response.setContentType("application/pdf");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		try (FileInputStream fis = new FileInputStream(file); ServletOutputStream sos = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				sos.write(buffer, 0, bytesRead);
			}
		}
	}

}
