package com.hrms.services;

import java.io.IOException;
import java.util.Base64;
import com.hrms.models.Employee;
import com.hrms.models.Users;
import com.hrms.repository.EmployeeRepo;
import com.hrms.repository.UsersRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServices {

	private UsersRepo userRepo;
	private EmployeeRepo empRepo;

	public UserServices() {
		this.userRepo = new UsersRepo();
		this.empRepo = new EmployeeRepo();
	}

	public void LogIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		boolean isValid = userRepo.validateUsername(request.getParameter("username"));

		String msg = null;

		if (isValid) {
			Users user = userRepo.findByUsername(request.getParameter("username"));
			if (user != null && user.getPassword().equals(request.getParameter("password"))) {
				HttpSession session = request.getSession();
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("role", userRepo.getUserRole(request.getParameter("username")));
				session.setAttribute("auth", "Basic "+getBasicAuth(user.getUsername(), user.getPassword()));
				session.setMaxInactiveInterval(30 * 60);
				
//				request.getRequestDispatcher("/api/employee").forward(request, response);
				response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
//				response.sendRedirect(request.getContextPath() + "/employee.jsp");
				return;
			} else {
				msg = "Invalid credentials!!!";
			}
		} else {
			msg = "User not found";
		}
		HttpSession session = request.getSession();
		session.setAttribute("responseMessage", msg);
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users user = new Users();
		Employee emp = new Employee();
		emp.setFirstName(request.getParameter("firstname"));
		emp.setLastName(request.getParameter("lastname"));
		emp.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setRoleId(1);

		int res = userRepo.save(user);
		if (res > 0) {
			int userId = userRepo.getUserId(user.getUsername(), user.getPassword());
			emp.setUserId(userId);
			int empRes = empRepo.save(emp);
			if (empRes > 0) {
				response.sendRedirect(request.getContextPath() + "/registrationSuccess.jsp");
			}
		} else {

		}

	}

//	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		Users user = new Users();
//		Employee emp = new Employee();
//		emp.setFirstName(request.getParameter("firstname"));
//		emp.setLastName(request.getParameter("lastname"));
//		emp.setEmail(request.getParameter("email"));
//		user.setUsername(request.getParameter("username"));
//		user.setPassword(request.getParameter("password"));
//		user.setRoleId(1);
//		
////		Map<String,Object> userResult=userRepo.save(user);
//		String userResult=userRepo.save(user);
//
//		
//		int userId = userRepo.getUserId(user.getUsername(), user.getPassword());
//		emp.setUserId(userId);
//		
//		Map<String,Object> empResult = empRepo.save(emp);
//		
////		if (userResult.containsKey("errmsg")) {
////            request.setAttribute("errorMessage", userResult.get("errmsg"));
////            request.getRequestDispatcher("/register.jsp").forward(request, response);
////        } else if(empResult.containsKey("errmsg")) {
////        	request.setAttribute("errorMessage", empResult.get("errmsg"));
////            request.getRequestDispatcher("/register.jsp").forward(request, response);
////        } else {
////            response.sendRedirect(request.getContextPath() + "/registrationSuccess.jsp");
////        }
//		String res = (String) request.getAttribute("errorMessage");
////	    response.sendRedirect(request.getContextPath() + "/registrationSuccess.jsp");
//	}

	public void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
	}

	private String getBasicAuth(String username, String password) {
		String auth = username + ":" + password;
		return Base64.getEncoder().encodeToString(auth.getBytes());
	}

}
