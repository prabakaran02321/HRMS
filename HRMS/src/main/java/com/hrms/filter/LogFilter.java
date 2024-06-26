package com.hrms.filter;

import java.io.IOException;
import com.hrms.services.LogService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
	
	private LogService logSer;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logSer = new LogService();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logSer.logging(request, response);
		chain.doFilter(request, response);
	}

}
