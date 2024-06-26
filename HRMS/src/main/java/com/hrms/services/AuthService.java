package com.hrms.services;

import java.io.IOException;
import java.util.Base64;
import com.hrms.models.Users;
import com.hrms.repository.UsersRepo;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthService {

	private UsersRepo userRepo;

	public AuthService() {
		this.userRepo = new UsersRepo();
	}

	public void auth(ServletRequest request, ServletResponse response) throws IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getServletPath();
		String method = req.getMethod();
		HttpSession session = req.getSession(false);
		String authHeader = (String) session.getAttribute("auth");

		if (authHeader != null && authHeader.startsWith("Basic ")) {

			String base64Credentials = authHeader.substring("Basic ".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials));

			final String[] values = credentials.split(":", 2);

			String username = values[0];
			String password = values[1];

			boolean isValid = userRepo.validateUsername(username);

			if (isValid) {
				Users user = userRepo.findByUsername(username);
				if (user != null && user.getPassword().equals(password)) {
					res.getWriter().write("Hello, " + username + "!");
					boolean isAuthorizedUser = userRepo.authValid(user.getRoleId(), path, method);
					if (isAuthorizedUser) {
						return;
					} else {
						res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You don't have a access to perform this action");
						return;
					}
				}
			}
		}

		res.setHeader("WWW-Authenticate", "Basic realm=\"Access to the secure site\"");
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
