package com.hrms.filter;

import java.io.IOException;
import com.hrms.services.AuthService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/api/*")
public class AuthFilter implements Filter {

	private AuthService authSer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.authSer = new AuthService();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		authSer.auth(request, response);
		
		chain.doFilter(request, response);

	}

}
