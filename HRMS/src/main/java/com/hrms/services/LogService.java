package com.hrms.services;

import java.io.IOException;
import java.sql.Timestamp;
import com.hrms.repository.LogRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogService {
	
	private LogRepo logRepo;

	public LogService() {
		this.logRepo = new LogRepo();
	}

	public void logging(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestUri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        String clientIp = request.getRemoteAddr();
        Timestamp requestTime = new Timestamp(System.currentTimeMillis());

       

        Timestamp responseTime = new Timestamp(System.currentTimeMillis());
        int statusCode = httpResponse.getStatus();

        logRepo.logToDatabase(requestTime, clientIp, method, requestUri, responseTime, statusCode);
	}

}
