package com.hrms.services;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.Employee;
import com.hrms.repository.EmployeeRepo;
import com.hrms.utils.DataValidation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeService {

	private EmployeeRepo empRepo;
	private Employee emp;

	public EmployeeService() {
		empRepo = new EmployeeRepo();
		emp = new Employee();
	}

	public void createEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		emp.setFirstName(request.getParameter("firstname"));
		emp.setLastName(request.getParameter("lastname"));
		emp.setDob(request.getParameter("dob"));
		emp.setGender(request.getParameter("gender"));
		emp.setAddress(request.getParameter("address"));
		emp.setPhone(request.getParameter("phone"));
		emp.setEmail(request.getParameter("email"));
		emp.setHireDate(request.getParameter("hiredate"));
		emp.setSalary(request.getParameter("salary"));
		emp.setPosition(request.getParameter("position"));

		int result = empRepo.save(emp);
		String resMsg = null;
		if (result > 0) {
			resMsg = "Employee Created Sucessfully";
			request.setAttribute("responseMessage", resMsg);
			request.getRequestDispatcher("/employee.jsp").forward(request, response);
		} else {
			resMsg = "Employee not created";
			response.sendRedirect("/employee.jsp");
		}
	}

	public void getAllRecord(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int page = 1;
		int recordsPerPage = 6;
		int noOfRecords = empRepo.getNoOfRecords();
//        3   =                             6         /      3        
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<Employee> list = empRepo.getEmpRecords((page - 1) * recordsPerPage, recordsPerPage);
			request.setAttribute("employeeList", list);
			request.setAttribute("recordsPerPage", recordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.getRequestDispatcher("/employee.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee record has only " + noOfPages + " pages");
		}

	}

	public void editEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    String empId = request.getParameter("id");
	    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
	    Employee employee = empRepo.findByEmpId(empId);

	    if (employee != null) {
	        try {
	            String changedFieldsParam = request.getParameter("changedFields");
	            if (changedFieldsParam != null && !changedFieldsParam.isEmpty()) {
	                String[] changedFields = changedFieldsParam.split(",");
	                for (String fieldName : changedFields) {
	                    String paramValue = request.getParameter(fieldName);
	                    switch (fieldName) {
	                        case "firstname":
	                            if (DataValidation.isValidName(paramValue)) {
	                                map.put("first_name", paramValue);
	                            } else {
	                                throw new RuntimeException("First name should contain only letters");
	                            }
	                            break;
	                        case "lastname":
	                            if (DataValidation.isValidName(paramValue)) {
	                                map.put("last_name", paramValue);
	                            } else {
	                                throw new RuntimeException("Last name should contain only letters");
	                            }
	                            break;
	                        case "dob":
	                            if (DataValidation.isValidDate(paramValue)) {
	                                map.put("dob", paramValue);
	                            } else {
	                                throw new RuntimeException("Dob format wrong");
	                            }
	                            break;
	                        case "gender":
	                        	if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("gender", paramValue);
	                            }
	                            break;
	                        case "address":
	                        	if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("address", paramValue);
	                            }
	                            break;
	                        case "phone":
	                        	if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("phone", paramValue);
	                            }
	                            break;
	                        case "position":
	                        	if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("position", paramValue);
	                            }
	                            break;
	                        case "hiredate":
	                        	if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("hire_date", paramValue);
	                            }
	                            break;
	                        case "salary":
	                            if (paramValue != null && !paramValue.isEmpty()) {
	                                map.put("salary", paramValue);
	                            }
	                            break;
	                        case "email":
	                            if (paramValue != null && !paramValue.isEmpty()) {
	                                if (DataValidation.isValidEmail(paramValue)) {
	                                    map.put("email", paramValue);
	                                } else {
	                                    throw new RuntimeException("Invalid Email!!!");
	                                }
	                            }
	                            break;
	                        case "user_id":
	                            try {
	                                int userId = Integer.parseInt(paramValue);
	                                map.put("user_id", userId);
	                            } catch (NumberFormatException e) {
	                                throw new RuntimeException("Invalid user ID");
	                            }
	                            break;
	                        default:
	                            break;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
	            return;
	        }
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
	        return;
	    }

	    int result = empRepo.updateById(empId, map);

	    if (result > 0) {
	        response.sendRedirect(request.getContextPath() + "/api/employee/view");
	    } else {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to edit employee record");
	    }
	}


}
