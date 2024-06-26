package com.hrms.utils;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RoleValidator {
	
	public static String getRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            String roleName = (String) session.getAttribute("role");

            if (username != null && roleName != null) {
                return roleName;
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid session, please log in again.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session not found, please log in.");
        }
        return null;
    }

}
